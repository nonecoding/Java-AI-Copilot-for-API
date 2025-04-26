package org.ai.aicopilotforapi.common.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class I18nConfig {
  @Bean
  public MessageSource messageSource(){
    var src = new ReloadableResourceBundleMessageSource();
    src.setBasename("classpath:messages/messages");
    src.setDefaultEncoding("UTF-8");
    return src;
  }
}