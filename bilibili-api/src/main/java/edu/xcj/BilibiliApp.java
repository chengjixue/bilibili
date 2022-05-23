package edu.xcj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/22 19:21
 */
@SpringBootApplication
public class BilibiliApp {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(BilibiliApp.class, args);
    }
}
