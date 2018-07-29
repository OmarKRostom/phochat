package phochat.omarkrostom.com.models;

import com.google.gson.annotations.SerializedName;

public class ChatText extends ChatObject {
    @SerializedName("metadata")
    private BaseMetaData metaData;

    public BaseMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(BaseMetaData metaData) {
        this.metaData = metaData;
    }
}
