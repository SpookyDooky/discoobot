package commandstuff.commands.voice;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.Bot;
import core.BotManager;
import core.utils.GuildInfo;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinChannel implements ICommand {

    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        VoiceChannel channelVoice = event.getMember().getVoiceState().getChannel();

        Guild guild = event.getGuild();
        AudioManager audioManager = guild.getAudioManager();

        GuildInfo info = BotManager.getInstance().getGuildInfo(guild.getId());

        boolean result = info.getVoiceManager().connectTo(channelVoice,audioManager);
        if(!result){
            context.getChannel().sendMessage("You are not in a voice channel").queue();
        } else {
            context.getChannel().sendMessage("Connecting to channel: " + channelVoice.getName()).queue();
        }
    }

    public String help() {
        return "bot joins your voice channel";
    }

    @Override
    public String category() {
        return "voice";
    }

    public String getCommandName() {
        return "join";
    }
}
