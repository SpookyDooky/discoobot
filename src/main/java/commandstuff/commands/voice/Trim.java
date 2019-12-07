package commandstuff.commands.voice;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.Bot;
import core.managers.TrackManager;
import core.utils.Track;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.File;

public class Trim implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        try {
            int startTime = Integer.valueOf(parameters[0]);
            int endTime = Integer.valueOf(parameters[1]);

            if (startTime < endTime) {
                String fileName = parameters[2];
                if(startTime < 0){
                    context.getChannel().sendMessage("Please make sure that the start time is positive").queue();
                    return;
                }

                TrackManager manager = Bot.getInstance().getTrackManager();
                Track track = manager.getTrack(fileName);

                if(track == null){
                    context.getChannel().sendMessage("Track is not stored anymore sorry :(").queue();
                    return;
                }

                Track newTrack = track.trimData(startTime, endTime);
                File wav = newTrack.getWavFile();
                File mp3 = newTrack.getMp3File(wav);

                context.getChannel().sendFile(mp3).queue();
                if(mp3.delete()){
                    System.out.println("File deleted");
                }
            } else {
                context.getChannel().sendMessage("Startime needs to be smaller than endtime").queue();
            }
        } catch (Exception e){
            context.getChannel().sendMessage("Start and end time need to be integers").queue();
        }
    }

    @Override
    public String help() {
        return "command to trim clips, usage: \n";
    }

    @Override
    public String category() {
        return "voice";
    }

    @Override
    public String getCommandName() {
        return "trim";
    }
}
