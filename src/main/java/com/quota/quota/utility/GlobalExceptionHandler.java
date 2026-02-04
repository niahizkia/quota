package com.quota.quota.utility;

import com.quota.quota.dto.ExceptionDTO;
import com.quota.quota.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) //Annotation untuk method
    public ResponseEntity<ResponseDTO> handleRuntimeException(Exception exception){
        var firstStack = exception.getStackTrace()[0];

        var dto = new ResponseDTO(
                firstStack.getClassName(),
                firstStack.getMethodName(),
                exception.getMessage()
        );
        return ResponseEntity.status(500).body(dto);
    }
}
