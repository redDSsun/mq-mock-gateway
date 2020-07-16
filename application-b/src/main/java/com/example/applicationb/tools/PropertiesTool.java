package com.example.applicationb.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:applicationb.properties")
public class PropertiesTool {

    @Value("${mq.hostname}")
    public String mq_hostname;

    @Value("${mq.port}")
    public int mq_port;



}