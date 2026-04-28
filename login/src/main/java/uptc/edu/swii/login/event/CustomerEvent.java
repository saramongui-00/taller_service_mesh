package uptc.edu.swii.login.event;

public class CustomerEvent {

    private String eventType;
    private String data;

    public CustomerEvent(){}

    public CustomerEvent(String eventType, String data) {
        this.eventType = eventType;
        this.data = data;
    }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}