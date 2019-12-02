package commands.voice;

import commands.CommandContext;
import commands.ICommand;
import core.Bot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Clip implements ICommand {

    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        if(parameters == null){
            //Take 5 secs
            byte[] data = getFinal(Bot.getInstance().getVoiceManager().getPCM_Stream(5));
            getWavFile(data);
        } else {
            try{
                int seconds = Integer.valueOf(parameters[0]);
                byte[] data = getFinal(Bot.getInstance().getVoiceManager().getPCM_Stream(seconds));
                getWavFile(data);
                System.out.println("SAMPLES: " + data.length);
            } catch(Exception e){
                context.getChannel().sendMessage("Please make sure that the first argument is a number");
            }
        }
    }

    private boolean getWavFile(byte[] PCM_Data){
        boolean result = false;
        try{
            File outputFile = new File("src/main/resources/result.wav");
            AudioFormat format = new AudioFormat(48000,16,2,true,true);
            AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(PCM_Data), format,PCM_Data.length), AudioFileFormat.Type.WAVE,outputFile);
            result = true;
        } catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("FINISHED BUILDING WAVE FILE");
        return result;
    }

    private byte[] getFinal(ArrayList<Byte> data){
        byte[] result = new byte[data.size()];
        for(int x = 0; x < result.length;x++){
            result[x] = data.get(x);
        }
        return result;
    }

    public void help() {

    }

    public String getCommandName() {
        return "clip";
    }
}
