package phochat.omarkrostom.com.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChatQuickReply extends ChatObject {
    @SerializedName("metadata")
    private MetaData metaData;

    private class MetaData extends BaseMetaData {
        @SerializedName("elements")
        private ArrayList<ChatQuickReplyItem> chatQuickReplyItems;

        public ArrayList<ChatQuickReplyItem> getChatQuickReplyItems() {
            return chatQuickReplyItems;
        }

        public void setChatQuickReplyItems(ArrayList<ChatQuickReplyItem> chatQuickReplyItems) {
            this.chatQuickReplyItems = chatQuickReplyItems;
        }
    }

    private class ChatQuickReplyItem {
        @SerializedName("type")
        private String type;

        @SerializedName("title")
        private String title;

        @SerializedName("payload")
        private String payload;

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
    }
}
