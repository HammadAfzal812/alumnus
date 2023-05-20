package edu.imdadia.employees.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

@Configuration
@RequiredArgsConstructor
public class CommonConfig {

  private static final Logger LOG = LoggerFactory.getLogger(CommonConfig.class);

  @Bean
  public FormattingConversionService conversionService() {
    final DefaultFormattingConversionService conversionService =
        new DefaultFormattingConversionService(false);

    final DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
    registrar.setDateFormatter(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    registrar.registerFormatters(conversionService);

    // other desired formatters

    return conversionService;
  }

  @Bean
  public ObjectMapper getObjectMapper() {
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JodaModule());
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    return objectMapper;
  }

  @Bean
  public ResourceBundle resourceBundle() {
    return ResourceBundle.getBundle("Bundle");
  }
}
