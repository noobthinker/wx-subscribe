package com.xkorey.subscribe;

import com.xkorey.subscribe.exception.BackException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class ExceptionInterceptor {

    @ExceptionHandler(value = Exception.class)
    public String handleCustomException(Exception ex, Model model) {
        String errorMessage;
        if( ex instanceof BindException){
            log.error("bean校验失败",ex);
            BindingResult bingResult = ((BindException)ex).getBindingResult();
            // 前端返code 500 直接服务器内部异常写死了，需要在包装一层
            errorMessage = bingResult.getAllErrors().get(0).getDefaultMessage();
        }
        else if(ex instanceof BackException){
            errorMessage= ex.getMessage();
        }
        else {
            //系统异常
            log.error(ex.getMessage(), ex);
            errorMessage="系统异常";
        }
        model.addAttribute("message",errorMessage);
        return "error";
    }
}
