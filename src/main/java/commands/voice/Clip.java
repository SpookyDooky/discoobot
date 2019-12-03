package commands.voice;

import commands.CommandContext;
import commands.command_interfaces.ICommand;
import core.Bot;
import core.utils.BotLocator;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import ws.schild.jave.*;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;

public class Clip implements ICommand {

    private CommandContext context;

    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        this.context = context;

        if(parameters == null){
            //Take 5 secs
            byte[] data = getFinal(Bot.getInstance().getVoiceManager().getPCM_Stream(15));
            getWavFile(data);
        } else {
            try{
                int seconds = Integer.valueOf(parameters[0]);
                byte[] data = getFinal(Bot.getInstance().getVoiceManager().getPCM_Stream(seconds));
                getWavFile(data);
                System.out.println("SAMPLES: " + data.length);
            } catch(Exception e){
                context.getChannel().sendMessage("Please make sure that the first argument is a number").queue();
            }
        }

    }

    private boolean getWavFile(byte[] PCM_Data){
        boolean result = false;
        try{
            File outputFileWave = new File("src/main/resources/result.wav");
            AudioFormat format = new AudioFormat(48000,16,2,true,true);
            AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(PCM_Data), format,PCM_Data.length), AudioFileFormat.Type.WAVE,outputFileWave);

            File target = new File("src/main/resources/clip.mp3");
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(128000);
            audio.setChannels(2);
            audio.setSamplingRate(44100);
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp3");
            attrs.setAudioAttributes(audio);

            FFMPEGLocator loc = new BotLocator();
            Encoder encoder = new Encoder(loc);
            MultimediaObject pls = new MultimediaObject(outputFileWave);
            encoder.encode(pls,target,attrs);

            this.context.getChannel().sendFile(target).queue();
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

    public String help() {
        return "Clips a piece of voice, usage !!clip for 15 second clip, and !!clip [time in seconds]";
    }

    public String getCommandName() {
        return "clip";
    }
}
