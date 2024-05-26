package com.myworks.mywork.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseError  {
    private boolean isSuccess;
    private String code;
    private String message;
    public  static BaseError of(String code,String message){
        return  new BaseError(false,code,message);
    }
}
