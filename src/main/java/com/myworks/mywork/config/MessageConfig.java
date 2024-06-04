package com.myworks.mywork.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
public class MessageConfig  {

    private static final String DEFAULT_BUNDLE_PATH = "classpath:messages/messages";
    private static final String DEFAULTS_PATH = "classpath:messages/defaults";

    @Bean
    public static ReloadableResourceBundleMessageSource messageSource() {
        var messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasenames(DEFAULT_BUNDLE_PATH, DEFAULTS_PATH);
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}