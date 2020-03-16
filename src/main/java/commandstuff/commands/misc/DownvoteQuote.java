package commandstuff.commands.misc;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class DownvoteQuote implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {

    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String category() {
        return null;
    }

    @Override
    public String getCommandName() {
        return null;
    }
}
