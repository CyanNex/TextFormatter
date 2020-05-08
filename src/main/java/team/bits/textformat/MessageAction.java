package team.bits.textformat;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

public enum MessageAction {

    SHOW_TEXT(false, String.class, String::valueOf),
    SHOW_ITEM(false, ItemStack.class, object -> {
        throw new UnsupportedOperationException();
    }),
    SHOW_ENTITY(false, Entity.class, object -> {
        throw new UnsupportedOperationException();
    }),

    OPEN_URL(true, String.class, String::valueOf),
    OPEN_FILE(true, String.class, String::valueOf),
    RUN_COMMAND(true, String.class, String::valueOf),
    SUGGEST_COMMAND(true, String.class, String::valueOf),
    CHANGE_PAGE(true, Integer.class, String::valueOf),
    COPY_TO_CLIPBOARD(true, String.class, String::valueOf);

    public final boolean isClickEvent;

    private final Class<?> valueType;
    private final Function<Object, String> converter;

    MessageAction(boolean isClickEvent, @NotNull Class<?> valueType, @NotNull Function<Object, String> converter) {
        this.isClickEvent = isClickEvent;
        this.valueType = Objects.requireNonNull(valueType);
        this.converter = Objects.requireNonNull(converter);
    }

    public @NotNull String getValue(@NotNull Object value) {
        Objects.requireNonNull(value);
        return Objects.requireNonNull(this.converter.apply(this.valueType.cast(value)));
    }
}
