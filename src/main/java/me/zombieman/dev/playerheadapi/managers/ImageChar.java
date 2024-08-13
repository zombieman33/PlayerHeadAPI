package me.zombieman.dev.playerheadapi.managers;

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
