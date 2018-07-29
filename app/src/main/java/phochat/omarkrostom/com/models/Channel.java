package phochat.omarkrostom.com.models;

import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("socket")
    private Socket socket;

    @SerializedName("envelope")
    private Envelope envelope;

    @SerializedName("ref")
    private int ref;

    @SerializedName("topic")
    private String topic;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Envelope getEnvelope() {
        return envelope;
    }

    public void setEnvelope(Envelope envelope) {
        this.envelope = envelope;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public void makeRef() {
        this.ref++;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
