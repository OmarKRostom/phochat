package phochat.omarkrostom.com.models;

import com.google.gson.annotations.SerializedName;

public class Envelope {
    @SerializedName("topic")
    private String topic;

    @SerializedName("payload")
    private Payload payload;

    @SerializedName("event")
    private String event;

    @SerializedName("ref")
    private int ref;

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }
}
