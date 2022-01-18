package learningSpring.demo.exceptions;

public class ResponseError {
    private final String error;

    public ResponseError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}