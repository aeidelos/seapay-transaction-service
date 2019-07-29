package io.sea.transaction.template;

public class ErrorTemplate {
    private int code;
    private String message;
    private String title;
    private String severity;

    public ErrorTemplate(int code, String message, String title) {
        this.code = code;
        this.message = message;
        this.severity = "";
        this.title = "error";
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public String getSeverity() {
        return severity;
    }

    public static ErrorTemplate blankErrorTemplate() {
        return new ErrorTemplate(200, "", "");
    }

}
