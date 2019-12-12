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
        String token = "NjUwNzI3NTUzODM2Nzc3NDky.XeuGrA.kzzOizj5769aBOjqlYih5kK7pB4";

        try{
            jda = new JDABuilder(token).build();
            jda.addEventListener(new EventListenerBot());
        } catch(Exception e){
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
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
