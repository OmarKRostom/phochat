package phochat.omarkrostom.com.managers;

import android.util.Log;

import com.annimon.stream.Stream;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Hashtable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import phochat.omarkrostom.com.GeneralConstants;
import phochat.omarkrostom.com.MalFormattedSocketException;
import phochat.omarkrostom.com.models.Channel;
import phochat.omarkrostom.com.models.ChatCarousel;
import phochat.omarkrostom.com.models.ChatImage;
import phochat.omarkrostom.com.models.ChatLink;
import phochat.omarkrostom.com.models.ChatList;
import phochat.omarkrostom.com.models.ChatObject;
import phochat.omarkrostom.com.models.ChatQuickReply;
import phochat.omarkrostom.com.models.ChatText;
import phochat.omarkrostom.com.models.ChatVideo;
import phochat.omarkrostom.com.models.Envelope;
import phochat.omarkrostom.com.models.Payload;
import phochat.omarkrostom.com.models.Socket;

import static phochat.omarkrostom.com.GeneralConstants.ALREADY_CONNECTED;
import static phochat.omarkrostom.com.GeneralConstants.CHANNEL_NOT_FOUND;
import static phochat.omarkrostom.com.GeneralConstants.MALFORMED_SOCKET;
import static phochat.omarkrostom.com.GeneralConstants.PHEONIX_PARAMS;
import static phochat.omarkrostom.com.GeneralConstants.SECURE_SOCKET_INITIALS;
import static phochat.omarkrostom.com.GeneralConstants.SOCKET_INITIALS;
import static phochat.omarkrostom.com.GeneralConstants.SOCKET_NOT_INITIALIZED;
import static phochat.omarkrostom.com.GeneralConstants.TOKEN;
import static phochat.omarkrostom.com.GeneralConstants.message_new;

public class SocketManager extends WebSocketListener {
    private int messageRefIndex = 0;

    private Socket mSocket;
    private Request mRequest;
    private SocketListener mSocketListener;

    private static SocketManager sInstance;
    private static String TAG = "SOCKET_MANAGER says: ";

    private SocketManager() {
    }

    public static SocketManager getInstance() {
        if (sInstance == null) sInstance = new SocketManager();
        return sInstance;
    }

    /**
     * Method to initialize a secure socket connection.
     *
     * @param socketUrl
     * @param token
     * @param listener
     */
    public void initializeSocketConnection(String socketUrl, String token, SocketListener listener) {
        if (mSocket == null) {
            try {
                mRequest = new Request.Builder()
                        .url(parseSocketUrl(socketUrl, token))
                        .build();
            } catch (MalFormattedSocketException e) {
                Log.d(TAG, MALFORMED_SOCKET);
            }
            OkHttpClient client = new OkHttpClient();
            WebSocket webSocket = client.newWebSocket(mRequest, this);
            mSocket = new Socket();
            mSocket.setConnected(true);
            mSocket.setWebSocket(webSocket);
            mSocketListener = listener;
        } else {
            Log.d(TAG, ALREADY_CONNECTED);
        }
    }


    /**
     * Method to subscribe to given channel using a channel topic.
     *
     * @param channelTopic
     */
    public void subscribeToChannel(String channelTopic) {
        Channel channel = generateChannel(channelTopic);
        mSocket.addChannel(channel);
        sendViaChannel(channel);
    }

    /**
     * Method to listen to events using a subscribed channel topic.
     *
     * @param myDesiredChannel
     * @param payloadMessage
     */
    public void fire(String myDesiredChannel, String payloadMessage) {
        Channel targetChannel = Stream.of(mSocket.getChannels())
                .filter(channel -> channel.getTopic().equals(myDesiredChannel))
                .single();
        if (targetChannel != null) {
            targetChannel.setEnvelope(generateEnvelope(
                    messageRefIndex,
                    targetChannel.getTopic(),
                    message_new,
                    payloadMessage
            ));
            sendViaChannel(targetChannel);
        } else {
            Log.d(TAG, CHANNEL_NOT_FOUND);
        }
    }

    /**
     * Method to parse ws url with student token
     *
     * @param socketUrl
     * @param token
     * @return
     * @throws MalFormattedSocketException
     */
    private String parseSocketUrl(String socketUrl, String token) throws MalFormattedSocketException {
        if (!socketUrl.startsWith(SOCKET_INITIALS) || !socketUrl.startsWith(SECURE_SOCKET_INITIALS))
            throw new MalFormattedSocketException();
        return socketUrl + PHEONIX_PARAMS + TOKEN + token;
    }

    /**
     * WebsocketListener that calls appropriate custom socket listener
     *
     * @param webSocket
     * @param response
     */
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        mSocketListener.onConnectionOpened(response);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        mSocketListener.onMessageReceived(bytes.toString());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ChatObject.class, new ChatCustomDeserializer());
        Gson gson = gsonBuilder.create();
        ChatObject chatObject = gson.fromJson(text, ChatObject.class);
        mSocketListener.onChatBubbleReceived(chatObject);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        mSocketListener.onConnectionClosed(code, reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        mSocketListener.onConnectionFailure(t, response);
    }

    /**
     * Method to generate channels locally needed for communication.
     *
     * @param channelTopic
     * @return
     */
    private Channel generateChannel(String channelTopic) {
        Channel channel = new Channel();
        channel.setTopic(channelTopic);
        channel.setEnvelope(generateEnvelope(messageRefIndex,
                channelTopic,
                GeneralConstants.CHANNEL_EVENTS.phx_join.toString(),
                GeneralConstants.SUBSCRIPTION_MESSAGE));
        channel.setSocket(mSocket);
        return channel;
    }

    /**
     * Method needed to process any request, and fire events through.
     *
     * @param channel
     */
    private void sendViaChannel(Channel channel) {
        if (mSocket != null && mSocket.isConnected()) {
            Gson gson = new Gson();
            channel.getSocket().getWebSocket().send(gson.toJson(channel.getEnvelope()));
            messageRefIndex++;
        } else {
            Log.d(TAG, SOCKET_NOT_INITIALIZED);
        }
    }

    /**
     * Envelope is the main point of data sent through a channel.
     *
     * @param ref
     * @param channelTopic
     * @param channelEvent
     * @param subscriptionMessage
     * @return
     */
    private Envelope generateEnvelope(int ref,
                                      String channelTopic,
                                      String channelEvent,
                                      String subscriptionMessage) {
        Envelope envelope = new Envelope();
        envelope.setRef(ref);
        envelope.setTopic(channelTopic);
        envelope.setEvent(channelEvent);
        envelope.setPayload(new Payload(subscriptionMessage));
        return envelope;
    }

    /**
     * Custom socket listener
     */
    public interface SocketListener {
        void onConnectionOpened(Response response);

        void onChatBubbleReceived(ChatObject chatObject);

        void onMessageReceived(String string);

        void onConnectionClosed(int code, String reason);

        void onConnectionFailure(Throwable throwable, Response response);
    }

    /**
     * Custom JSON Deserializer for chat objects
     */
    class ChatCustomDeserializer implements JsonDeserializer<ChatObject> {

        @Override
        public ChatObject deserialize(JsonElement json,
                                      Type typeOfT,
                                      JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = (JsonObject) json;
            String chatType = jsonObject.getAsJsonObject(GeneralConstants.CHAT_METADATA)
                    .get(GeneralConstants.CHAT_TYPE)
                    .getAsString();

            Hashtable<String, Type> deserializeHashTable = getChatDeserializeHashTable();

            return context.deserialize(json, deserializeHashTable.get(chatType));
        }

        /**
         * Add chat types into a hash table
         * @return
         */
        private Hashtable<String, Type> getChatDeserializeHashTable() {
            Hashtable<String, Type> deserializeHashTable = new Hashtable<>();
            deserializeHashTable.put(GeneralConstants.CHAT_TYPES.plain.toString(),
                    ChatText.class);
            deserializeHashTable.put(GeneralConstants.CHAT_TYPES.image.toString(),
                    ChatImage.class);
            deserializeHashTable.put(GeneralConstants.CHAT_TYPES.video.toString(),
                    ChatVideo.class);
            deserializeHashTable.put(GeneralConstants.CHAT_TYPES.link.toString(),
                    ChatLink.class);
            deserializeHashTable.put(GeneralConstants.CHAT_TYPES.carousel.toString(),
                    ChatCarousel.class);
            deserializeHashTable.put(GeneralConstants.CHAT_TYPES.quick_reply.toString(),
                    ChatQuickReply.class);
            deserializeHashTable.put(GeneralConstants.CHAT_TYPES.list.toString(),
                    ChatList.class);
            return deserializeHashTable;
        }

    }
}