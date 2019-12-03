package commandstuff.commands.admin;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.Launcher;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Restart implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        context.getChannel().sendMessage("Restarting bot now, this might take a bit...").queue();
        Launcher.restart(context);
    }

    @Override
    public String help() {
        return "";
    }

    @Override
    public String getCommandName() {
        return "restart";
    }
}
