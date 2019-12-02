package commands.voice;

import commands.CommandContext;
import commands.ICommand;
import core.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinChannel implements ICommand {

    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        VoiceChannel channelVoice = event.getMember().getVoiceState().getChannel();

        Guild guild = event.getGuild();
        AudioManager audioManager = guild.getAudioManager();

        Bot.getInstance().getVoiceManager().connectTo(channelVoice,audioManager);
        context.getChannel().sendMessage("Connecting to channel: " + channelVoice.getName()).queue();
    }

    public void help() {

    }

    public String getCommandName() {
        return "join";
    }
}
