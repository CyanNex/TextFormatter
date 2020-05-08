package team.bits.textformat;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StyleHelperTest {

    private final ChatColor style;
    private final String expected;

    public StyleHelperTest(ChatColor style, String expected) {
        this.style = style;
        this.expected = expected;
    }

    @Test
    public void getStyleName() {
        assertEquals(this.expected, StyleHelper.getStyleName(this.style));
    }

    @Parameterized.Parameters
    public static @NotNull List<Object[]> parameters() {
        return Arrays.asList(
                new Object[]{ChatColor.BOLD, "bold"},
                new Object[]{ChatColor.STRIKETHROUGH, "strikethrough"},
                new Object[]{ChatColor.MAGIC, "obfuscated"},
                new Object[]{ChatColor.UNDERLINE, "underlined"}
        );
    }
}