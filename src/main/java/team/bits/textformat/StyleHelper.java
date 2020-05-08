package team.bits.textformat;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

public final class StyleHelper {

    public static final Map<ChatColor, String> STYLE_NAMES = new EnumMap<>(ChatColor.class);

    static {
        for (final ChatColor style : ChatColor.values()) {
            if (style.isFormat()) {
                String name;
                switch (style) {
                    case MAGIC:
                        name = "obfuscated";
                        break;
                    case UNDERLINE:
                        name = "underlined";
                        break;
                    default:
                        name = style.name().toLowerCase();
                        break;
                }
                STYLE_NAMES.put(style, name);
            }
        }
    }

    public static @NotNull String getStyleName(@NotNull ChatColor style) {
        if (style.isFormat()) {
            if (!STYLE_NAMES.containsKey(style)) {
                throw new IllegalArgumentException(String.format("Cannot find a name for style %s", style.name()));
            }
            return STYLE_NAMES.get(style);
        } else {
            return style.name().toLowerCase();
        }
    }
}
