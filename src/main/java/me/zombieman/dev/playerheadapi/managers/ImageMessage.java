package me.zombieman.dev.playerheadapi.managers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImageMessage {
    private static final char TRANSPARENT_CHAR = '#';
    private Component[] lines;

    public ImageMessage(BufferedImage image, int height, char imgChar) {
        String[][] rgbColors = this.toRGBArray(image, height);
        this.lines = this.toImgMessage(rgbColors, imgChar);
    }

    public ImageMessage(String[][] rgbColors, char imgChar) {
        this.lines = this.toImgMessage(rgbColors, imgChar);
    }

    public ImageMessage(Component... imgLines) {
        this.lines = imgLines;
    }

    public ImageMessage appendText(Component... text) {
        Component[] newLines = new Component[Math.max(this.lines.length, text.length)];
        for (int y = 0; y < newLines.length; ++y) {
            Component imageLine = y < this.lines.length ? this.lines[y] : Component.empty();
            Component textLine = y < text.length ? text[y] : Component.empty();
            newLines[y] = imageLine.append(Component.text(" ")).append(textLine);
        }
        this.lines = newLines;
        return this;
    }

    private String[][] toRGBArray(BufferedImage image, int height) {
        double ratio = (double) image.getHeight() / (double) image.getWidth();
        int width = (int) ((double) height / ratio);

        BufferedImage resized = this.resizeImage(image, width, height);
        String[][] rgbImg = new String[resized.getWidth()][resized.getHeight()];

        for (int x = 0; x < resized.getWidth(); ++x) {
            for (int y = 0; y < resized.getHeight(); ++y) {
                int rgb = resized.getRGB(x, y);
                Color color = new Color(rgb, true);
                rgbImg[x][y] = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
            }
        }

        return rgbImg;
    }

    private Component[] toImgMessage(String[][] colors, char imgchar) {
        Component[] lines = new Component[colors[0].length];
        MiniMessage miniMessage = MiniMessage.miniMessage();

        for (int y = 0; y < colors[0].length; ++y) {
            StringBuilder line = new StringBuilder();

            for (int x = 0; x < colors.length; ++x) {
                String color = colors[x][y];
                if (color != null) {
                    line.append("<color:").append(color).append(">").append(imgchar);
                } else {
                    line.append("<color:#000000>").append(TRANSPARENT_CHAR);
                }
            }

            lines[y] = miniMessage.deserialize(line.toString());
        }
        return lines;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        AffineTransform af = new AffineTransform();
        af.scale((double) width / (double) originalImage.getWidth(), (double) height / (double) originalImage.getHeight());
        AffineTransformOp operation = new AffineTransformOp(af, AffineTransformOp.TYPE_BILINEAR);
        return operation.filter(originalImage, null);
    }

    public Component[] getLines() {
        return this.lines;
    }

    public void broadcast() {
        for (Component line : this.lines) {
            Bukkit.broadcast(line);
        }
    }

    public void sendPlayer(Player p) {
        for (Component line : this.lines) {
            p.sendMessage(line);
        }
    }

    public Component get() {
        for (Component line : this.lines) {
            return line;
        }
        return null;
    }
}