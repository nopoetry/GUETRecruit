package qdu.java.recruit.configuration;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import qdu.java.recruit.common.AlertException;
import qdu.java.recruit.common.Result;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(AlertException.class)
    public Result<String> alertExceptionHandler(AlertException alertException){
        return alertException.fail();
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<String> runtimeExceptionHandler(RuntimeException runtimeException) {
        runtimeException.printStackTrace();
        Result<String> result = new Result<>();
        result.setSuccess(false);
        result.setCode(500);
        result.setMessage("程序运行错误");
        result.setQueryData(runtimeException.getMessage());
        return result;
    }

    // 参数校验错误处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Map<String, String>> argumentNotValidExceptionHandler(MethodArgumentNotValidException validException) {
        validException.printStackTrace();
        BindingResult exceptionBindingResult = validException.getBindingResult();
        Map<String, String> map = new HashMap<>();
        // 获取校验结果，遍历获取捕获到的每个校验结果
        exceptionBindingResult.getFieldErrors().forEach(item ->{
            // 存储得到的校验结果
            map.put(item.getField(), item.getDefaultMessage());
        });
        Result<Map<String, String>> result = new Result<>();
        result.setCode(5001);
        result.setSuccess(false);
        result.setMessage("参数校验不合法");
        result.setQueryData(map);
        return result;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> constraintValidExceptionHandler(ConstraintViolationException validException) {
        validException.printStackTrace();
        Result<String> result = new Result<>();
        result.setCode(5001);
        result.setSuccess(false);
        result.setMessage("参数校验不合法");
        result.setQueryData(validException.getMessage());
        return result;
    }

    @ExceptionHandler(BindException.class)
    public Result<Map<String, String>> constraintValidExceptionHandler(BindException bindException) {
        bindException.printStackTrace();
        BindingResult bindingResult = bindException.getBindingResult();
        Map<String, String> map = new HashMap<>();
        // 获取校验结果，遍历获取捕获到的每个校验结果
        bindingResult.getFieldErrors().forEach(item ->{
            // 存储得到的校验结果
            map.put(item.getField(), item.getDefaultMessage());
        });
        Result<Map<String, String>> result = new Result<>();
        result.setCode(5001);
        result.setSuccess(false);
        result.setMessage("参数校验不合法");
        result.setQueryData(map);
        return result;
    }
}
