package com.loya.springtesting;

//import com.loya.springtesting.utils.CORSFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringTestingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringTestingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }


}
