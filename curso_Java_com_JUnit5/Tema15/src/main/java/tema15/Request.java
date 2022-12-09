package tema15;

public class Request {
    private String message;
    private RequestType requestType;

    public Request(String message, RequestType requestType) {
        this.message = message;
        this.requestType = requestType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
}
