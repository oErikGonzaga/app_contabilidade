package com.egti.app_contabilidade.config;
//
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
//
@Configuration
@EnableFeignClients({"com.egti.app_contabilidade.client"})
public class AppConfig {
}