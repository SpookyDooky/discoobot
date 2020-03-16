package commandstuff.commands.admin;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.Launcher;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Shutdown implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        context.getChannel().sendMessage("Shutting down...").queue();
        Launcher.restart(context);
    }

    @Override
    public String help() {
        return "";
    }

    @Override
    public String category() {
        return "admin";
    }

    @Override
    public String getCommandName() {
        return "shutdown";
    }
}
