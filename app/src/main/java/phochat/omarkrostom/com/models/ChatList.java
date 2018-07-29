package phochat.omarkrostom.com.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChatList extends ChatObject {
    @SerializedName("metadata")
    private MetaData metaData;

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    private class MetaData extends BaseMetaData {
        @SerializedName("elements")
        private ArrayList<ChatListItem> chatListItems;

        public ArrayList<ChatListItem> getChatListItems() {
            return chatListItems;
        }

        public void setChatListItems(ArrayList<ChatListItem> chatListItems) {
            this.chatListItems = chatListItems;
        }
    }

    private class ChatListItem {
        @SerializedName("type")
        private String type;

        @SerializedName("title")
        private String title;

        @SerializedName("payload")
        private String payload;

        @SerializedName("url")
        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
