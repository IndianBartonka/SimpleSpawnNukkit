package me.indian.pl.simplespawn.utils;

public class ChatColor {
    public static String replaceColorCode(String msg){
        String repl = msg.replace("&","§");

        return repl;
    }
}
