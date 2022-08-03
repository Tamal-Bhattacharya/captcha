package com.bhattacharya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bhattacharya.captcha.CaptchaGenerator;

@SpringBootApplication(scanBasePackages = "com.bhattacharya")
public class AppCap
{
    public static void main( String[] args )
    {
        SpringApplication.run(AppCap.class, args);
        System.out.println( "Hello World!" );
    }

    @Bean
	public CaptchaGenerator getCaptchaGenerator() {
		return new CaptchaGenerator();
	}
}
