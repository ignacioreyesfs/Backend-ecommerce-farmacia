package StockNotification.Notification;

public enum NotifierType {
    EMAIL{
        @Override
        public Notifier getNotifier(){
            return new GmailSender();
        }
    };
    abstract public Notifier getNotifier();
}
