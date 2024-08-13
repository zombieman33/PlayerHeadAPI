package me.zombieman.dev.playerheadapi;

import me.zombieman.dev.playerheadapi.managers.ImageMessage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public final class PlayerHeadAPI extends JavaPlugin {

    private static PlayerHeadAPI instance;
    private static ImageMessage imageMessageInstance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getLogger().info("PlayerHeadAPI has been enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("PlayerHeadAPI has been disabled.");
    }

    public static PlayerHeadAPI getInstance() {
        return instance;
    }
    public static ImageMessage getImageMessageInstance() {
        return imageMessageInstance;
    }

    public static Component coloredString(String message) {
        return MiniMessage.miniMessage().deserialize(message);
    }

    public static void sendPlayerHeadWithMessage(String name, int size, Player see, char imgChar, Component[] messageLines) {
        try {
            see.sendMessage("");
            URL url = new URL("https://mc-heads.net/avatar/" + name + "/" + size + ".png");
            BufferedImage bi = ImageIO.read(url);

            // Convert the BufferedImage to an image message
            ImageMessage imageMessage = new ImageMessage(bi, size, imgChar);
            imageMessage.appendText(messageLines);
            imageMessage.sendPlayer(see);
            see.sendMessage("");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error by sending Player Head");
        }
    }
    public enum ImageChar {
        BLOCK('⬛'),
        DARK_SHADE('▓'),
        MEDIUM_SHADE('▒'),
        LIGHT_SHADE('░');

        private char c;

        ImageChar(char Char) {
            this.c = Char;
        }

        public char getChar() {
            return this.c;
        }
    }
}
