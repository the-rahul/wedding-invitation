package com.rk.wedding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.rk.utils.file.properties.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class WeddingInvitationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeddingInvitationApplication.class, args);
	}

}
