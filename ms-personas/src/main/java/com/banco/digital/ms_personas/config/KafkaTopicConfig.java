package com.banco.digital.ms_personas.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic() {
        String nameTopic = "alta-usuario";
        return TopicBuilder.name(nameTopic).partitions(1).replicas(1).build();
    }
}
