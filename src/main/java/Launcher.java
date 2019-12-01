import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Launcher {
    private static JDA jda;

    public static void main(String[] args){
        String token = "";

        try{
            jda = new JDABuilder(token).build();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
