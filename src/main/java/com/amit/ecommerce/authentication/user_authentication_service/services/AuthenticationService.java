package com.amit.ecommerce.authentication.user_authentication_service.services;

import com.amit.ecommerce.authentication.user_authentication_service.models.Session;
import com.amit.ecommerce.authentication.user_authentication_service.models.SessionState;
import com.amit.ecommerce.authentication.user_authentication_service.models.User;
import com.amit.ecommerce.authentication.user_authentication_service.repositories.SessionRepository;
import com.amit.ecommerce.authentication.user_authentication_service.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SecretKey secretKey;

    public User signUp(String email, String password) {
        Optional<User> userFound = userRepository.findByEmail(email);
        if(userFound.isPresent()){
            return null;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public Pair<User, MultiValueMap<String,String>> login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()) {
            return null;
        }

        if(!bCryptPasswordEncoder.matches(password,
                optionalUser.get().getPassword())) {
            return null;
        }

        //TOKEN GENERATION

        Map<String,Object> claims = new HashMap<>();
        claims.put("email",optionalUser.get().getEmail());
        claims.put("roles",optionalUser.get().getRoles());
        long nowInMillis = System.currentTimeMillis();
        claims.put("iat",nowInMillis);
        claims.put("exp",nowInMillis+100000000L);  //10000 sec

        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        //Storing Session for validation purpose
        Session session = new Session();
        session.setUser(optionalUser.get());
        session.setSessionState(SessionState.ACTIVE);
        session.setToken(token);
        sessionRepository.save(session);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE,token);

        return new Pair<>(optionalUser.get(), headers);
    }

    /**
     * Token validation.
     * @param token
     * @param userId
     * @return
     */
    public Boolean validateToken(String token,Long userId) {
        /*If you are getting email as input
        Go to user table and do findByEmail and
        from User , get Id and use below*/

        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token,userId);

        if(optionalSession.isEmpty()) {
            System.out.println("User or Token not found");
            return false;
        }

        // Decoding the token
        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();

        long tokenExpiry = (Long)claims.get("exp");
        long nowInMillis = System.currentTimeMillis();

        if(nowInMillis > tokenExpiry) {
            System.out.println("Token expiry time: " + tokenExpiry);
            System.out.println("Current time: " + nowInMillis);
            System.out.println("Token Expired");
            return false;
        }

        // email validation is optional.
        Optional<User> optionalUser  = userRepository.findById(userId);
        String userEmail = optionalUser.get().getEmail();

        if(!userEmail.equals(claims.get("email"))) {
            System.out.println(userEmail);
            System.out.println(claims.get("email"));
            System.out.println("Emails didn't match");
            return false;
        }

        return true;
    }
}
