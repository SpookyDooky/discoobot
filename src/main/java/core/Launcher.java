package core;

import commandstuff.CommandContext;
import core.managers.BotManager;
import event.EventListenerBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Launcher {

    private static JDA jda;

    public static void main(String[] args) {
        startBot();
    }

    /**
     * Launches the bot
     */
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

    /**
     * Shuts the bot down for now, in the future we need a script that restarts the bot
     * based on the exit code
     * @param context
     */
    public static void restart(CommandContext context){
        BotManager.getInstance().destroyAllConnections();
        System.exit(0);
    }
}
