/*This class is responsible to run this project*/

package com.sc;

import javax.persistence.Entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jooq.SpringTransactionProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@SpringBootApplication
@ComponentScan("com")
@EnableJpaRepositories("com.repo")
@EntityScan("com.entity")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
