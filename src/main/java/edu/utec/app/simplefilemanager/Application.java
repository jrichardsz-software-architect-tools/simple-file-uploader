/*
 * Copyright (c) 2015 SK C&C Co., Ltd. All rights reserved.
 *
 * This software is the confidential and proprietary information of SK C&C. You shall not disclose
 * such Confidential Information and shall use it only in accordance wih the terms of the license
 * agreement you entered into with SK C&C.
 */
package edu.utec.app.simplefilemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "edu.utec.app.simplefilemanager")
@SpringBootApplication
public class Application extends SpringBootServletInitializer {


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
