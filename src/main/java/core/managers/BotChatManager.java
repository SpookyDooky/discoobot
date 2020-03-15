package core.managers;

import net.dv8tion.jda.api.entities.TextChannel;

public class BotChatManager {

    /**
     * In progress
     * 
     * @param message
     * @param channel
     */
    public static void sendMessage(String message, TextChannel channel){
        if(message.length() >= 2000){
            //Todo - Write algorithm to split up message over multiple smaller messages
            String cutMessage = "";
            longMessage(message,channel);
        } else {
            channel.sendMessage(message).queue();
        }
    }

    private static void longMessage(String message, TextChannel channel){
        String cutMessage = "";
        int messages = (int)Math.ceil(message.length()/2000.0);
        for(int x = 0; x < messages; x++){
            int endSub = (x + 1) * 2000;
            if(endSub > message.length()){
                endSub = message.length();
            }
            String subMessage = message.substring(x * 2000, endSub);
            channel.sendMessage(subMessage).queue();
        }
    }
}
