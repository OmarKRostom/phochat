package phochat.omarkrostom.com;

public class GeneralConstants {

    /* Events needed by pheonix */
    public enum CHANNEL_EVENTS {
        phx_close,
        phx_open,
        phx_reply,
        phx_join,
        phx_leave
    }
    public static String message_new = "message:new";

    /* Chat bubble types */
    public enum CHAT_TYPES {
        plain,
        image,
        video,
        link,
        carousel,
        quick_reply,
        list
    }
    public static String CHAT_TYPE = "type";
    public static String CHAT_METADATA = "metadata";

    /* Websocket builder tokens */
    public static String SOCKET_INITIALS = "ws";
    public static String SECURE_SOCKET_INITIALS = "ws";
    public static String PHEONIX_PARAMS = "/socket/websocket";
    public static String TOKEN = "?token=";

    /* Log Messages */
    public static String ALREADY_CONNECTED = "Socket already initialized !";
    public static String MALFORMED_SOCKET = "Malformatted socket URL !";
    public static String SOCKET_NOT_INITIALIZED = "Please initialize socket first !";
    public static String CHANNEL_NOT_FOUND = "Please initialize channel first !";

    /* Default Data Messages */
    public static String SUBSCRIPTION_MESSAGE = "SUBSCRIPTION_MESSAGE";

}
