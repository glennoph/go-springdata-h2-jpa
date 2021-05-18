package go.springboot.controller.advice;

import go.springboot.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ProductAdvice {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus
    public MessageDTO processNPE(NullPointerException ex) {
        log.error(ex.toString());
        return MessageDTO.builder()
                .message("NPE error found in request")
                .type("ERROR")
                .details(ex.toString())
                .build();

    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus
    public MessageDTO processIAE(IllegalArgumentException ex) {
        log.error(ex.toString());
        return MessageDTO.builder()
                .message("Illegal Argument Exception error found in request")
                .type("ERROR")
                .details(ex.toString())
                .build();

    }



}
