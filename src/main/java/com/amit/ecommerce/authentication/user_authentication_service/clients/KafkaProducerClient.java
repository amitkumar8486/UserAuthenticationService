package com.amit.ecommerce.authentication.user_authentication_service.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/*UserAuthenticationService is producer.
* It drops message into kafka queue*/
@Component
public class KafkaProducerClient {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message){
        kafkaTemplate.send(topic, message);
    }
}
