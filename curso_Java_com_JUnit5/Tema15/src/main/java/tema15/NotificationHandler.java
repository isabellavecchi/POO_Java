package tema15;

import java.util.Objects;

public abstract class NotificationHandler {
    private NotificationHandler next;

    public NotificationHandler(NotificationHandler notificationHandler) {
        this.next = notificationHandler;
    }

    public abstract void handleRequest (Request request);

    protected void dispatchToNext(Request request) {
        if(Objects.nonNull(this.next)) {
            this.next.handleRequest(request);
        }
    }
}
