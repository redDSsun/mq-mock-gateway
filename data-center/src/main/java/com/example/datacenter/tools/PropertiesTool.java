package com.example.datacenter.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesTool {

    @Value("${mq.hostname}")
    public String mq_hostname;

    @Value("${mq.port}")
    public int mq_port;



}
