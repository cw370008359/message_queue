package com.danhesoft.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by caowei on 2018/4/23.
 */
@Component
@ConfigurationProperties(prefix = "alikafka")
public class KafkaConfig {

    private String bootstrap_servers;
    private String topic;
    private String group_id;
    private String ssl_truststore_location;

    public String getJava_security_auth_login_config() {
        return java_security_auth_login_config;
    }

    public void setJava_security_auth_login_config(String java_security_auth_login_config) {
        this.java_security_auth_login_config = java_security_auth_login_config;
    }

    private String java_security_auth_login_config;

    public String getBootstrap_servers() {
        return bootstrap_servers;
    }

    public void setBootstrap_servers(String bootstrap_servers) {
        this.bootstrap_servers = bootstrap_servers;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getSsl_truststore_location() {
        return ssl_truststore_location;
    }

    public void setSsl_truststore_location(String ssl_truststore_location) {
        this.ssl_truststore_location = ssl_truststore_location;
    }
}
