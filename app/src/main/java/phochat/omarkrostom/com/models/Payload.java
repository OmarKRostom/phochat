package phochat.omarkrostom.com.models;

import com.google.gson.annotations.SerializedName;

public class Payload {
    @SerializedName("body")
    private String body;

    public Payload() {}

    public Payload(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
