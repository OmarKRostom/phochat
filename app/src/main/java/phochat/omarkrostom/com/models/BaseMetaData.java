package phochat.omarkrostom.com.models;

import com.google.gson.annotations.SerializedName;

public class BaseMetaData {
    @SerializedName("type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}