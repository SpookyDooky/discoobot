package commands.voice;

import commands.CommandContext;
import commands.command_interfaces.ICommand;
import core.Bot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class LeaveChannel implements ICommand {

    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        Bot.getInstance().getVoiceManager().disconnect();
    }

    public String help() {
        return "bot leaves voice";
    }

    public String getCommandName() {
        return "leave";
    }
}
