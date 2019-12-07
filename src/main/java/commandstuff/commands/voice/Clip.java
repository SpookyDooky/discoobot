package commandstuff.commands.voice;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.Bot;
import core.utils.BotLocator;
import core.utils.Track;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(Clip.class);

    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        this.context = context;

        try {
            if (!event.getMember().getVoiceState().inVoiceChannel()) {
                context.getChannel().sendMessage("You can't use this command while not being in voice").queue();
                return;
            }
        } catch(NullPointerException e){
            context.getChannel().sendMessage("You can't use this command while not being in voice").queue();
            return;
        }

        if(parameters == null){
            //Take 5 secs
            byte[] data = getFinal(Bot.getInstance().getVoiceManager().getPCM_Stream(15));
            logger.info(data.length + "");
            getWavFile(data);
        } else {
            try{
                int seconds = Integer.valueOf(parameters[0]);
                if(seconds < 0){
                    context.getChannel().sendMessage("Please enter positive integers only").queue();
                    return;
                }
                byte[] data = getFinal(Bot.getInstance().getVoiceManager().getPCM_Stream(seconds));
                getWavFile(data);
                System.out.println("SAMPLES: " + data.length);
            } catch(Exception e){
                context.getChannel().sendMessage("Please make sure that the first argument is a integer").queue();
            }
        }

    }

    private boolean getWavFile(byte[] PCM_Data){
        boolean result = false;

        Track track = new Track(PCM_Data, "Wav");
        File wavFile = track.getWavFile();
        File mp3 = track.getMp3File(wavFile);

        context.getChannel().sendFile(mp3).queue();
        mp3.delete();
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

    @Override
    public String category() {
        return "voice";
    }

    public String getCommandName() {
        return "clip";
    }
}
