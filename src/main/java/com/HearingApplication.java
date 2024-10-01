package com;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.laterya.hearing.mapper")
@SpringBootApplication
public class HearingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HearingApplication.class, args);
    }

}
