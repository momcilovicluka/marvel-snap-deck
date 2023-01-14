package com.example.marvelSnapDeck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("model")
@SpringBootApplication
public class MarvelSnapDeckWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarvelSnapDeckWebApplication.class, args);
	}

}
