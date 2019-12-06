package core;

import commandstuff.CommandContext;
import event.EventListenerBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Launcher {
    private static JDA jda;

    public static void main(String[] args) {
        startBot();
    }

    private static void startBot(){
        String token = "NjUwNzI3NTUzODM2Nzc3NDky.XePlwQ.5NL89kwbMtmoCCWir4g7UIJGVyc"; //Todo - make new token

        try{
            jda = new JDABuilder(token).build();
            jda.addEventListener(new EventListenerBot());
        } catch(Exception e){
            e.printStackTrace();
        }

        Initializer.initializeBot();
    }

    public static void restart(CommandContext context){
        Bot.getInstance().getVoiceManager().disconnect();
        context.getChannel().sendMessage("RESTARTING...").queue();
        System.exit(1);
    }
}
