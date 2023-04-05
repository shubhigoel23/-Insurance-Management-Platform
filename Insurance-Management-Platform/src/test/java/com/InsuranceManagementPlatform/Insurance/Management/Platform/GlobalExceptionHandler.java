package com.InsuranceManagementPlatform.Insurance.Management.Platform.*;
import com.InsuranceManagementPlatform.Insurance.Management.Platform.Model.Client;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(Client.ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleClientNotFound(Client.ClientNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }
}
