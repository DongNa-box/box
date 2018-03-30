
 /**
 * Project Name:PdoneCI-framework
 * File Name:Result.java
 * Package Name:com.pdone.framework.pojo
 * Date:2017年5月15日上午11:46:01
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.pojo;

/**
 * ClassName:Result
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月15日 上午11:46:01
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Result {
    /**
     * 执行结果
     */
    private boolean success;

    /**
     * 结果集
     */
    private Object data;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回状态码
     */
    private String code;

    public Result() {

        this.success = true;
    }

    public Result(boolean success) {

        this.success = success;
    }

    public Result(boolean success, Object data) {

        this.success = success;
        this.data = data;
    }
    
    public Result(boolean success, Object data, String code) {

        this.success = success;
        this.data = data;
        this.code = code;
    }

    public Result(boolean success, Object data, String message, String code) {

        this.success = success;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public Result(boolean success, String code) {

        this.success = success;
        this.code = code;
    }

    public Result(String code, String message) {

        this.code = code;
        this.message = message;
    }

    public Result(ResultCode rc) {

        this.code = rc.getCode();
        this.message = rc.getMessage();
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public boolean isSuccess() {

        return success;
    }

    public void setSuccess(boolean success) {

        this.success = success;
    }

    public Object getData() {

        return data;
    }

    public void setData(Object data) {

        this.data = data;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }
}

