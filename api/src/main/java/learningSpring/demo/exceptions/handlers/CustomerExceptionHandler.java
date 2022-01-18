package learningSpring.demo.exceptions.handlers;

import learningSpring.demo.exceptions.ResponseError;
import learningSpring.demo.exceptions.customer.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseError handleCustomerNotFound(CustomerNotFoundException exception) {
        return new ResponseError(exception.getMessage());
    }
}
