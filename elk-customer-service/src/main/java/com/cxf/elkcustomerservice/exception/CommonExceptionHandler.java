package com.cxf.elkcustomerservice.exception;

import com.cxf.common.dto.RespDTO;
import com.cxf.common.exception.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author always_on_the_way
 * @date 2019-07-23
 */
@ControllerAdvice
@ResponseBody
public class CommonExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<RespDTO> handleException(Exception e){
        RespDTO respDTO = new RespDTO();
        CommonException commonException = (CommonException) e;
        respDTO.code = commonException.getCode();
        respDTO.error = commonException.getMsg();
        return new ResponseEntity<>(respDTO,HttpStatus.OK);

    }


}
