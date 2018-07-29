# Phochat
### This library is still under development, and may face constant major changes.
A java library for phoenix chat.

## Purpose of this library
For purpose of native kai implementation, this library eases the connection with native kai pheonix server, and binds replies automatically to the different models respectively.

## Quick brief
A SocketManager class handles all the traffic in and out from the application. All connections and subscriptions are authenticated via the SocketManager, and in response, also the SocketManager parses back all the down coming messages.

## Usage
1. Initialize the socket connection:
```java
SocketManager socketManager = SocketManager.getInstance();
socketManager.initializeSocketConnection(
        BASE_URL_GOES_HERE,
        AUTH_TOKEN_GOES_HERE,
        REFERENCE_TO_SOCKET_LISTENER_GOES_HERE
);
```

2. Subscribe to a channel
```java
socketManager.subscribeToChannel(CHANNEL_TOPIC_GOES_HERE);
```

3. Fire a message through a subscribed channel 
(Remember if you pass in an unsubscribed channel topic, your message will not be sent)
```java
socketManager.fire(CHANNEL_TOPIC_GOES_HERE, CHANNEL_MESSAGE_GOES_HERE);
```

4. Listen to events through implementing the following interface
```java
public interface SocketListener {
    void onConnectionOpened(Response response);

    void onChatBubbleReceived(ChatObject chatObject);

    void onMessageReceived(String string);

    void onConnectionClosed(int code, String reason);

    void onConnectionFailure(Throwable throwable, Response response);
}
```

# What is ChatObject ?
ChatObject is essentialy the base concrete for all the different chat items sent by the pheonix servers, 
all different chat models are implemented and could be find under the models package, as ChatText, ChatImage, ChatVideo, ChatList, ChatLink, ChatCoursel and ChatQuickReply.

For more detailed explaination please visit this [DOC](https://docs.google.com/document/d/1Mrv8X8MfCNF--xRCEAFxZNA6UgfqUERvuhufDlFtQYE/edit?pli=1) or refer to the above mentioned models.
