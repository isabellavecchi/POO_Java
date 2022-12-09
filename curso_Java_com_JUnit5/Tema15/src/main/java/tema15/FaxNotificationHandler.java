package tema15;

public class FaxNotificationHandler extends NotificationHandler {

    public FaxNotificationHandler(NotificationHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request request) {
        if(RequestType.FAX.equals(request.getRequestType())) {
            System.out.println("Sending Fax:\n" + request.getMessage());
        } else {
            dispatchToNext(request);
        }
    }
}
