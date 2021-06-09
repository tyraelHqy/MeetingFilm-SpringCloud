package com.mooc.meetingfilm.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共的业务逻辑错误
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonServiceException extends Exception{
    private Integer code;
    private String message;
}
