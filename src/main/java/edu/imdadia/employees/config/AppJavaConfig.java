/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.imdadia.employees.config;

import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@RequiredArgsConstructor
public class AppJavaConfig {

  private final SpringFXMLLoader springFXMLLoader;

  @Bean
  @Lazy() //Stage will be created only after Spring context bootstrap
  public StageManager stageManager(final Stage stage) {
    return new StageManager(springFXMLLoader, stage);
  }

}
