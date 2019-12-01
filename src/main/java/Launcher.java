import core.EventListenerBot;
import core.Initializer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Launcher {
    private static JDA jda;

    public static void main(String[] args){
        String token = "";

        try{
            jda = new JDABuilder(token).build();
            jda.addEventListener(new EventListenerBot());
        } catch(Exception e){
            e.printStackTrace();
        }

        Initializer.initializeBot();
    }
}
