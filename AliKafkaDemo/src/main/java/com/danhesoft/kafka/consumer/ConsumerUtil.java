package com.danhesoft.kafka.consumer;

import com.danhesoft.config.KafkaConfig;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.ws.WebEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by caowei on 2018/4/23.
 */
@Configuration
public class ConsumerUtil implements ApplicationRunner {

    @Autowired
    private KafkaConfig kafkaConfig;

    @Bean
    public Properties getProperties(){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrap_servers());
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaConfig.getSsl_truststore_location());
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "KafkaOnsClient");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "ONS");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 25000);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 30);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfig.getGroup_id());
        // 请求的最长等待时间
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 30 * 1000);
        return props;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(getProperties());
        List<String> subscribedTopics =  new ArrayList<String>();
        subscribedTopics.add(kafkaConfig.getTopic());
        consumer.subscribe(subscribedTopics);
        //循环消费消息
        while (true) {
            try {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                //必须在下次poll之前消费完这些数据, 且总耗时不得超过SESSION_TIMEOUT_MS_CONFIG
                //建议开一个单独的线程池来消费消息，然后异步返回结果
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());
                    System.out.println(String.format("Consume partition:%d offset:%d", record.partition(), record.offset()));
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Throwable ignore) {

                }
                e.printStackTrace();
            }
        }
    }
}
