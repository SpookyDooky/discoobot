package commands.support;

import commands.CommandContext;
import commands.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Help implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {

    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "help";
    }
}
