package app.foodpanda.dto;

public class MessageDTO {

    private boolean success;
    private String message;

    public MessageDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof MessageDTO)) {
            return false;
        }

        MessageDTO m = (MessageDTO) o;

        return m.success == success && (message.compareTo(m.message) == 0);
    }
}
