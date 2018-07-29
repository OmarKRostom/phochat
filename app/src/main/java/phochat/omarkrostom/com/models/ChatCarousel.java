package phochat.omarkrostom.com.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChatCarousel extends ChatObject {
    @SerializedName("metadata")
    private MetaData metaData;

    private class MetaData extends BaseMetaData {
        @SerializedName("url")
        private String url;

        @SerializedName("elements")
        private ArrayList<ChatCarouselItem> chatCarouselItems;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    private class ChatCarouselItem {
        @SerializedName("title")
        private String title;

        @SerializedName("subTitle")
        private String subTitle;

        @SerializedName("image_url")
        private String image_url;

        @SerializedName("default_action")
        private DefaultAction defaultAction;

        @SerializedName("buttons")
        private ArrayList<Button> buttons;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getImageUrl() {
            return image_url;
        }

        public void setImageUrl(String image_url) {
            this.image_url = image_url;
        }

        public DefaultAction getDefaultAction() {
            return defaultAction;
        }

        public void setDefaultAction(DefaultAction defaultAction) {
            this.defaultAction = defaultAction;
        }

        public ArrayList<Button> getButtons() {
            return buttons;
        }

        public void setButtons(ArrayList<Button> buttons) {
            this.buttons = buttons;
        }
    }

    private class DefaultAction {
        @SerializedName("type")
        private String type;

        @SerializedName("payload")
        private String payload;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }
    }

    private class Button {
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
