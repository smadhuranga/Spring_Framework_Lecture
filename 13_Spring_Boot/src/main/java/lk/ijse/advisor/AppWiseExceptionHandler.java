package lk.ijse.advisor;

import lk.ijse.util.ResponseUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // This annotation is used to handle exceptions globally
@CrossOrigin
public class AppWiseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseUtil exceptionHandler(Exception ex) {

        return new ResponseUtil(500, ex.getMessage(), null);
    }
}
