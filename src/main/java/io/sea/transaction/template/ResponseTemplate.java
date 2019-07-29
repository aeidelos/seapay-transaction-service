package io.sea.transaction.template;

public class ResponseTemplate<T> {
    private T data;
    private boolean success;
    private ErrorTemplate error;

    public ResponseTemplate(T data, boolean success, ErrorTemplate error) {
        this.data = data;
        this.success = success;
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public ErrorTemplate getError() {
        return error;
    }
}
