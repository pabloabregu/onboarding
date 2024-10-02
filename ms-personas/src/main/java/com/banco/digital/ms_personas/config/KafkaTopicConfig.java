package com.banco.digital.ms_personas.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka-topic.nuevo-usuario}")
    private String nameTopic;

    @Value("${kafka-topic.nuevo-usuario.partitions}")
    private int partitions;

    @Value("${kafka-topic.nuevo-usuario.replicas}")
    private int replicas;

    @Bean
    public NewTopic generateTopic() {
        return TopicBuilder.name(nameTopic)
                .partitions(partitions)
                .replicas(replicas).build();
    }
}
