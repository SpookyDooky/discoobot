package commandstuff.commands.voice;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.managers.BotManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class LeaveChannel implements ICommand {

    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        BotManager.getInstance().getGuildInfo(event.getGuild().getId()).getVoiceManager().disconnect();
    }

    public String help() {
        return "bot leaves voice";
    }

    @Override
    public String category() {
        return "voice";
    }

    public String getCommandName() {
        return "leave";
    }
}
