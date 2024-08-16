[![Latest Version](https://img.shields.io/github/v/tag/zombieman33/PlayerHeadAPI?label=version&style=for-the-badge)](https://jitpack.io/#zombieman33/PlayerHeadAPI)

# PlayerHeadAPI

PlayerHeadAPI is a Minecraft plugin designed to send player heads as images in chat. It leverages Minecraft avatars and displays them in the chat using various shades or characters, allowing for a highly customizable in-game experience.

[![GitHub Repository](https://img.shields.io/badge/GitHub-Repository-blue?style=for-the-badge&logo=github)](https://github.com/zombieman33/PlayerHeadAPI)
[![Discord](https://img.shields.io/badge/Discord-Join%20Server-blue?style=for-the-badge&logo=discord)](https://discord.gg/SuypvRBa4c)

## Features

- Retrieve and display Minecraft player heads as images in chat.
- Customizable image characters for different visual effects.
- Simple integration into any Minecraft plugin.

## Requirements

- Java 8 or later
- Minecraft server (Spigot/Bukkit)

## Installation

### Maven
```
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
  
  <dependency>
      <groupId>com.github.zombieman33</groupId>
      <artifactId>PlayerHeadAPI</artifactId>
      <version>version</version>
  </dependency>
```

## Gradle
```
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.zombieman33:PlayerHeadAPI:version'
}
```

# Usage
## Displaying Player Heads in Chat
Here's a simple example of how you can use PlayerHeadAPI to display a Minecraft player's head in chat using custom characters:

```
import me.zombieman.dev.playerheadapi.PlayerHeadAPI;
import me.zombieman.dev.playerheadapi.managers.ImageChar;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Player player = ...; // Get the player instance
        String playerName = "Notch"; // Example player name
        int size = 8; // Size of the head image
        char imgChar = ImageChar.BLOCK.getChar(); // Use the block character for the image

        Component[] messageLines = new Component[]{
            PlayerHeadAPI.coloredString("<yellow>Here is Notch's head!</yellow>"),
            PlayerHeadAPI.coloredString("<green>Isn't it cool?</green>")
        };

        // Send the player's head image and message in chat
        PlayerHeadAPI.sendPlayerHeadWithMessage(playerName, size, player, imgChar, messageLines);
    }
}
```

# How It Works
sendPlayerHeadWithMessage(String name, int size, Player see, char imgChar, Component[] messageLines):
This method retrieves the player head from the specified name, resizes it according to size, and sends it to the player see in chat, appending any additional text from messageLines.

ImageChar Enum: 
This enum provides different characters for rendering the image in chat, such as BLOCK, DARK_SHADE, MEDIUM_SHADE, and LIGHT_SHADE.

# Example Output
The following example shows how a player's head can be rendered in chat using the PlayerHeadAPI.
The example uses a combination of different characters and colors to produce a visually appealing representation of the player's head.
In this example, the player's head image is displayed using a grid of colored blocks or shades, followed by a custom message.

# Contributing
Contributions are welcome! Please submit pull requests to the repository.

## Issues

If you encounter any issues or have any questions, please [open a new issue](https://github.com/zombieman33/PlayerHeadAPI/issues/new?title=Issue%20Title&body=Describe%20your%20issue%20here).

[![GitHub Repository](https://img.shields.io/badge/GitHub-Repository-blue?style=for-the-badge&logo=github)](https://github.com/zombieman33/PlayerHeadAPI)


# Discord
[![Discord](https://img.shields.io/badge/Discord-Join%20Server-blue?style=for-the-badge&logo=discord)](https://discord.gg/SuypvRBa4c)


