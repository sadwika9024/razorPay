package com.pabbasadwika.razorPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RazorPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RazorPayApplication.class, args);
		System.out.println("DB_USER=" + System.getenv("DB_USER"));
		System.out.println("DB_PASS=" + System.getenv("DB_PASS"));
	}

}
