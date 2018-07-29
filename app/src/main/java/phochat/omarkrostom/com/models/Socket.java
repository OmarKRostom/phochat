package phochat.omarkrostom.com.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import okhttp3.WebSocket;

public class Socket {
    @SerializedName("webSocket")
    private WebSocket webSocket;

    @SerializedName("channels")
    private List<Channel> channels = new ArrayList<>();

    @SerializedName("isConnected")
    private boolean isConnected;

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public void addChannel(Channel channel) {
        this.channels.add(channel);
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

}
