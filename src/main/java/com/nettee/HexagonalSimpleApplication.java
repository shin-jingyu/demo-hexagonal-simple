package com.nettee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static com.nettee.common.constant.Constants.ROOT_PACKAGE;

@SpringBootApplication(scanBasePackages = ROOT_PACKAGE)
public class HexagonalSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HexagonalSimpleApplication.class, args);
    }

}
