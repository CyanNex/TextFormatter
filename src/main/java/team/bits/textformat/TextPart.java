package team.bits.textformat;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

public class TextPart {

    private final Collection<ChatColor> styles = new LinkedList<>();

    private String text;

    private ChatColor color;
    private Color hexColor;
    private MessageEvent clickEvent;
    private MessageEvent hoverEvent;

    protected TextPart(@NotNull String text) {
        this.text = Objects.requireNonNull(text);
        this.color = ChatColor.WHITE;
    }

    public void color(@NotNull ChatColor color) {
        Objects.requireNonNull(color);
        if (!color.isColor()) {
            throw new IllegalArgumentException(String.format("%s is not a color", color));
        }
        this.color = color;
    }

    public void color(@NotNull Color color) {
        Objects.requireNonNull(color);
        this.hexColor = color;
    }

    public void style(@NotNull ChatColor style) {
        Objects.requireNonNull(style);
        if (!style.isFormat()) {
            throw new IllegalArgumentException(String.format("%s is not a style", style));
        }
        if (this.styles.contains(style)) {
            throw new IllegalArgumentException("Cannot add the same style twice");
        }
        this.styles.add(style);
    }

    public void onClick(@NotNull MessageEvent event) {
        Objects.requireNonNull(event);
        if (!event.isClickEvent()) {
            throw new IllegalArgumentException("Given event is not a click event");
        }
        this.clickEvent = event;
    }

    public void onHover(@NotNull MessageEvent event) {
        Objects.requireNonNull(event);
        if (event.isClickEvent()) {
            throw new IllegalArgumentException("Given event is not a hover event");
        }
        this.hoverEvent = event;
    }

    public @NotNull JsonObject toJSON() {
        JsonObject json = new JsonObject();

        json.addProperty("text", this.text);
        if (this.hexColor != null) {
            Color color = this.hexColor;
            String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
            json.addProperty("color", hex.toUpperCase());
        } else {
            json.addProperty("color", StyleHelper.getStyleName(this.color));
        }

        for (final ChatColor style : this.styles) {
            json.addProperty(StyleHelper.getStyleName(style), true);
        }

        if (this.clickEvent != null) {
            json.add("clickEvent", this.clickEvent.toJSON());
        }
        if (this.hoverEvent != null) {
            json.add("hoverEvent", this.hoverEvent.toJSON());
        }

        return json;
    }

    public @NotNull String toLegacy() {
        StringBuilder result = new StringBuilder();
        result.append(this.color.toString());
        for (final ChatColor style : this.styles) {
            result.append(style.toString());
        }
        result.append(this.text);
        return result.toString();
    }

    public void replace(@NotNull String template, @NotNull String replacement) {
        this.text = this.text.replace(template, replacement);
    }
}
