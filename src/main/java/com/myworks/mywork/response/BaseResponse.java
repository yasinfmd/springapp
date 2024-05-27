package com.myworks.mywork.response;

import com.myworks.mywork.consts.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private T data;
    private  boolean isSuccess;
    private String message;
    private LocalDateTime timestamp=LocalDateTime.now();


    public  static BaseResponse success(){
        return  new BaseResponse(null,true, Messages.SUCCESS.getValue(),LocalDateTime.now());
    }
    public  static BaseResponse success(Object data){
        return  new BaseResponse(data,true, Messages.SUCCESS.getValue(),LocalDateTime.now());
    }
}
