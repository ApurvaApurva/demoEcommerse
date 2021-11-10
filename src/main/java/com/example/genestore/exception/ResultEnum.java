package com.example.genestore.exception;
import lombok.Getter;

@Getter
public enum ResultEnum  {

    VALID_ERROR(50, "Wrong information"),
    USER_NOT_FOUNT(40,"User is not found!")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

	String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	Integer getCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
