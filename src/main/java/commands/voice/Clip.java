package commands.voice;

import commands.CommandContext;
import commands.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Clip implements ICommand {

    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        if(parameters.length == 0){
            //Take 30 secs
            int samples = 1500;

        } else {
            try{
                int seconds = Integer.valueOf(parameters[0]);

            } catch(Exception e){
                context.getChannel().sendMessage("Please make sure that the first argument is a number");
            }
        }
    }

    private boolean getWavFile(byte[] PCM_Data){
        boolean result = false;
        try{
            File outputFile = new File("src/main/resources/result.wav");
            AudioFormat format = new AudioFormat(8000,16,1,true,false);
            AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(PCM_Data), format,PCM_Data.length), AudioFileFormat.Type.WAVE,outputFile);
            result = true;
        } catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public void help() {

    }

    public String getCommandName() {
        return "clip";
    }
}
