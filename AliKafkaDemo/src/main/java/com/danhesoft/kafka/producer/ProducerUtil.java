package com.danhesoft.kafka.producer;

import com.danhesoft.config.KafkaConfig;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.Future;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

/**
 * Created by caowei on 2018/4/23.
 */
@Component
public class ProducerUtil {

    @Autowired
    private KafkaConfig kafkaConfig;

    private Properties getProperties(){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrap_servers());
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaConfig.getSsl_truststore_location());
        System.out.println(kafkaConfig.getJava_security_auth_login_config());
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "KafkaOnsClient");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "ONS");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // 请求的最长等待时间
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 30 * 1000);
        return props;
    }

    public void produceMsg(String msgs){
        System.setProperty("java.security.auth.login.config", "D:/config/kafka_client_jaas.conf");
        Properties properties = getProperties();
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        String topic = kafkaConfig.getTopic();
        ProducerRecord<String, String> kafkaMessage =  new ProducerRecord<String, String>(topic, msgs);
        try {
            Future<RecordMetadata> metadataFuture = producer.send(kafkaMessage);
            RecordMetadata recordMetadata = metadataFuture.get();
            System.out.println("Produce ok:" + recordMetadata.toString());
        }catch (Exception e){
            //要考虑重试
            System.out.println("error occurred");
            e.printStackTrace();
        }
    }

}
