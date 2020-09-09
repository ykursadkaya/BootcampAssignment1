package notification;

public interface Notification
{
	void send(String recipient);
	void sendAll();
}
