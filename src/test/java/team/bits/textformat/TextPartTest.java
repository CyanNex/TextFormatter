package team.bits.textformat;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextPartTest {

    @Test
    public void testTextPart() {
        TextPart part = new TextPart("test");
        part.color(ChatColor.YELLOW);
        part.style(ChatColor.BOLD);
        part.onHover(new MessageEvent(MessageAction.SHOW_TEXT, "test"));
        part.onClick(new MessageEvent(MessageAction.RUN_COMMAND, "test"));

        JsonObject json = part.toJSON();

        assertEquals("test", json.get("text").getAsString());
        assertEquals("yellow", json.get("color").getAsString());
        assertTrue(json.get("bold").getAsBoolean());

        JsonObject click = json.getAsJsonObject("clickEvent");
        assertNotNull(click);
        assertEquals("run_command", click.get("action").getAsString());
        assertEquals("test", click.get("value").getAsString());

        JsonObject hover = json.getAsJsonObject("hoverEvent");
        assertNotNull(hover);
        assertEquals("show_text", hover.get("action").getAsString());
        assertEquals("test", hover.get("value").getAsString());
    }
}