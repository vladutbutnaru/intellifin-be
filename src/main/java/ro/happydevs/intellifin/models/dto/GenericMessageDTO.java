package ro.happydevs.intellifin.models.dto;

public class GenericMessageDTO {

    private int code;
    private String message;
    private boolean success;

    public GenericMessageDTO(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public GenericMessageDTO() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
