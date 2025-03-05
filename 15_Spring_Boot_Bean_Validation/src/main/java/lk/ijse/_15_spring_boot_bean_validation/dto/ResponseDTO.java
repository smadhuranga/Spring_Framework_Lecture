package lk.ijse._15_spring_boot_bean_validation.dto;

public class ResponseDTO {
    private String message;
    private int statusCode;
    private Object data;

    public ResponseDTO(){

    }

    public ResponseDTO(String message, int statusCode, Object data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", data=" + data +
                '}';
    }
}
