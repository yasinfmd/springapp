package com.myworks.mywork.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseError  {
    private boolean isSuccess;
    private String code;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    public  static BaseError of(String code,String message,String path){
        return  new BaseError(false,code,message,LocalDateTime.now(),path);
    }
}
