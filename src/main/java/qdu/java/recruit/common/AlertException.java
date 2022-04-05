package qdu.java.recruit.common;

import lombok.Data;
import qdu.java.recruit.constant.ResultCode;

@Data
public class AlertException extends RuntimeException{

    private ResultCode resultCode;

    public AlertException(ResultCode resultCode){
        this.resultCode = resultCode;
    }

    public AlertException(ResultCode resultCode, String message){
        this.resultCode = resultCode;
        resultCode.changerMessage(message);
    }


    public Result<String> fail(){
        return Result.failure(this.resultCode,getMessage());
    }
}
