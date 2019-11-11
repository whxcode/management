package com.cn.scitc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import com.spring4all.swagger.EnableSwagger2Doc;
@ServletComponentScan
@RestController
@EnableSwagger2Doc
@EnableTransactionManagement
@SpringBootApplication(exclude = {
SecurityAutoConfiguration.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
