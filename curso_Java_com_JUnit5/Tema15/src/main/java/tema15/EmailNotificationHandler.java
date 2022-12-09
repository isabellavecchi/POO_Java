package tema15;

public class EmailNotificationHandler extends NotificationHandler {

    public EmailNotificationHandler(NotificationHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request request) {
        if(RequestType.EMAIL.equals(request.getRequestType())) {
            System.out.println("Sending Email:\n" + request.getMessage());
        } else {
            dispatchToNext(request);
        }
    }
}
