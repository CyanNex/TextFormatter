package team.bits.textformat;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MessageEvent {

    private final MessageAction action;
    private final String value;

    public MessageEvent(@NotNull MessageAction action, @NotNull Object value) {
        this.action = Objects.requireNonNull(action);
        this.value = action.getValue(Objects.requireNonNull(value));
    }

    public @NotNull JsonObject toJSON() {
        JsonObject json = new JsonObject();
        json.addProperty("action", this.action.name().toLowerCase());
        json.addProperty("value", this.value);
        return json;
    }

    public boolean isClickEvent() {
        return this.action.isClickEvent;
    }
}
