package tema15;

public class Main {
    public static void main(String[] args) {
        NotificationHandler smsHandler = new SMSNotificationHandler(null);
        NotificationHandler emailHandler = new EmailNotificationHandler(smsHandler);
        NotificationHandler faxHandler = new FaxNotificationHandler(emailHandler);

        Request request = new Request("Hello World", RequestType.SMS);
        faxHandler.handleRequest(request);

        request.setRequestType(RequestType.EMAIL);
        faxHandler.handleRequest(request);

        request.setRequestType(RequestType.FAX);
        faxHandler.handleRequest(request);
    }
}
