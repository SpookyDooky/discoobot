package core.managers;

import net.dv8tion.jda.api.entities.TextChannel;

public class BotChatManager {

    public static void sendMessage(String message, TextChannel channel){
        if(message.length() >= 2000){
            //Todo - Write algorithm to split up message over multiple smaller messages
            String cutMessage = "";
            channel.sendMessage("Oops that dumbass forgot to implement me: BotChatManager long message script").queue();
        } else {
            channel.sendMessage(message).queue();
        }
    }
}
