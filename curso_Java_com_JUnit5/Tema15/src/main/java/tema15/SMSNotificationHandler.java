package tema15;

public class SMSNotificationHandler extends NotificationHandler {

    public SMSNotificationHandler(NotificationHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request request) {
        if(RequestType.SMS.equals(request.getRequestType())) {
            System.out.println("Sending SMS:\n" + request.getMessage());
        } else {
            dispatchToNext(request);
        }
    }
}
