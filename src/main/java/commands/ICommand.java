package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface ICommand {

    void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context);
    String help();
    String getCommandName();
}
