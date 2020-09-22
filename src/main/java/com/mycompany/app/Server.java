package com.mycompany.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Server {

  @RequestMapping("/")
  public String home() {
    return "Hello cdk8s!";
  }

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

}
