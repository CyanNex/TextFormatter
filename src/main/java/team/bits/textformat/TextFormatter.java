package team.bits.textformat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;

public class TextFormatter {

    private final LinkedList<TextPart> parts = new LinkedList<>();

    public TextFormatter(@NotNull String text, Object... args) {
        this.parts.add(new TextPart(String.format(text, args)));
    }

    public TextFormatter() {
        this("");
    }

    public @NotNull TextFormatter then(@NotNull String text, Object... args) {
        this.parts.add(new TextPart(String.format(text, args)));
        return this;
    }

    public @NotNull TextFormatter color(@NotNull ChatColor color) {
        this.parts.getLast().color(color);
        return this;
    }

    public @NotNull TextFormatter color(@NotNull Color color) {
        this.parts.getLast().color(color);
        return this;
    }

    public @NotNull TextFormatter style(@NotNull ChatColor... styles) {
        for (final ChatColor style : styles) {
            this.parts.getLast().style(style);
        }
        return this;
    }

    public @NotNull TextFormatter onClick(@NotNull MessageAction action, @NotNull Object value) {
        this.parts.getLast().onClick(new MessageEvent(action, value));
        return this;
    }

    public @NotNull TextFormatter onHover(@NotNull MessageAction action, @NotNull Object value) {
        this.parts.getLast().onHover(new MessageEvent(action, value));
        return this;
    }

    public @NotNull TextFormatter tooltip(@NotNull String tooltip) {
        this.onHover(MessageAction.SHOW_TEXT, tooltip);
        return this;
    }

    public @NotNull TextFormatter command(@NotNull String command) {
        this.onClick(MessageAction.RUN_COMMAND, command);
        return this;
    }

    public @NotNull TextFormatter link(@NotNull String link) {
        this.onClick(MessageAction.OPEN_URL, link);
        return this;
    }

    public @NotNull TextFormatter newLine() {
        this.then("\n");
        return this;
    }

    public @NotNull JsonObject toJSON() {
        JsonObject json = this.parts.get(0).toJSON();

        if (this.parts.size() > 1) {
            JsonArray extra = new JsonArray();
            for (int i = 1; i < this.parts.size(); i++) {
                extra.add(this.parts.get(i).toJSON());
            }

            json.add("extra", extra);
        }

        return json;
    }

    public @NotNull String toLegacy() {
        StringBuilder result = new StringBuilder();
        for (final TextPart part : this.parts) {
            result.append(part.toLegacy());
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return this.toJSON().toString();
    }

    public void send(@NotNull Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format("tellraw %s %s", player.getName(), this));
    }

    public void send(@NotNull Collection<Player> players) {
        for (Player player : players) {
            this.send(player);
        }
    }

    public @NotNull TextFormatter replace(@NotNull String template, @NotNull String replacement) {
        TextFormatter clone = new TextFormatter();
        clone.parts.clone();
        this.parts.forEach(part -> clone.parts.add(part.replace(template, replacement)));
        return clone;
    }
}
