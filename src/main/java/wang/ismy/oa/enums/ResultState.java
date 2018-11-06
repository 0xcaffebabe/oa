package wang.ismy.oa.enums;

public enum  ResultState {

    SUCCESS(1,"success"),ERROR(0,"ERROR");

    private int code;

    private String message;


     ResultState(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
