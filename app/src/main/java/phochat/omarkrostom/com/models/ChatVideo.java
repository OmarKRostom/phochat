package phochat.omarkrostom.com.models;

import com.google.gson.annotations.SerializedName;

public class ChatVideo extends ChatObject {
    @SerializedName("metadata")
    private MetaData metaData;

    private class MetaData extends BaseMetaData {
        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
