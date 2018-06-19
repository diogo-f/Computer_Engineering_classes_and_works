package commonInfo;

public class MessageException extends Exception {

    private Message msg;

    public MessageException(String errorMessage, Message msg) {
        super(errorMessage);
        this.msg = msg;
    }

    public Message getMessageVal() {
        return (msg);
    }
}
