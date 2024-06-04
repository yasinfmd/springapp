package com.myworks.mywork.utils;

import com.myworks.mywork.config.MessageConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Component
public class MessageHelper {


    public static String getMessage(String message, String... dynamicValues) {
        return MessageConfig.messageSource().getMessage(message, dynamicValues, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String message, Locale locale, String... dynamicValues) {
        return MessageConfig.messageSource().getMessage(message, dynamicValues, locale);
    }
}