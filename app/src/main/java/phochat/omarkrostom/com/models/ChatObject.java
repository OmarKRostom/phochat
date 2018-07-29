package phochat.omarkrostom.com.models;

import com.google.gson.annotations.SerializedName;

public class ChatObject {
    @SerializedName("body")
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
