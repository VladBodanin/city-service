package com.resliv.place;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.resliv.place.*")
public class CityApplication {
  public static void main(String[] args) {
    SpringApplication.run(CityApplication.class, args);
  }
}
