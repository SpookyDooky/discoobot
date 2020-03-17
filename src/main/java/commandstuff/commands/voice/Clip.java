package commandstuff.commands.voice;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.managers.BotManager;
import core.managers.TrackManager;
import core.managers.VoiceManager;
import core.utils.Track;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;

public class Clip implements ICommand {

    //TODO - Make it so that the temporary files
    private CommandContext context;
    private final Logger logger = LoggerFactory.getLogger(Clip.class);

    /**
     * Method that sets up all the rules for when you can use the command
     * @param event - The event
     * @param parameters - Parameters
     * @param context - Context in which the command was used
     */
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        this.context = context;
        try {
            if (!event.getMember().getVoiceState().inVoiceChannel()) {
                context.getChannel().sendMessage("You have to be in a voice channel to use this command.").queue();
                return;
            }
        } catch(NullPointerException e){
            context.getChannel().sendMessage("You have to be in a voice channel to use this command.").queue();
            return;
        }

        VoiceManager botVoiceManager = BotManager.getInstance().getGuildInfo(event.getGuild().getId()).getVoiceManager();
        if(parameters == null){
            //Take 15 secs
            byte[] data = getFinal(botVoiceManager.getPCM_Stream(15));
            logger.info("SAMPLE SIZE: " + data.length);
            getWavFile(data);
        } else {
            try{
                int seconds = Integer.valueOf(parameters[0]);
                if(seconds <= 0){
                    context.getChannel().sendMessage("Number has to be greater than zero").queue();
                    return;
                }
                byte[] data = getFinal(botVoiceManager.getPCM_Stream(seconds));
                logger.info("SAMPLE SIZE: " + data.length);
                getWavFile(data);
            } catch(Exception e){
                context.getChannel().sendMessage("Please make sure that the first argument is a integer").queue();
            }
        }

    }

    /**
     * Takes care of converting the stream to wav and from wav to MP3
     * Also makes sure mp3 files are deleted from the file system
     * @param PCM_Data - Raw audio data
     */
    private void getWavFile(byte[] PCM_Data){
        TrackManager manager = BotManager.getInstance().getTrackManager();

        Track track = new Track(PCM_Data, "Wav");
        File wavFile = track.getWavFile();
        File mp3 = track.getMp3File(wavFile);

        context.getChannel().sendFile(mp3).queue();
        manager.addTrack(track,mp3.getName());

        try {
            Thread.sleep(250); //TODO - Make sure that during this time other commands can also be ran
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(mp3.delete()){
            logger.info("MP3: " + mp3.getName() + " succesfully deleted.");
        } else {
            logger.warn("MP3: " + mp3.getName() + " could not delete file.");
        }
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
