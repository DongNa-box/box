package com.box.framework.pojo;
/**
 * ClassName:ResultCode
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月10日 上午9:07:24
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class ResultCode {

    private String code;

    private String message;
    public ResultCode(){

    }

    public ResultCode(String code, String message) {

        this.code = code;
        this.message = message;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }
}

