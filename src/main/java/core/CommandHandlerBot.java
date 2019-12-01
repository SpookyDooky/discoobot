package core;

import commands.CommandContext;
import commands.CommandDetails;
import commands.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;

public class CommandHandlerBot {

    private ArrayList<ICommand> commandList;
    private ArrayList<CommandDetails> commandDetailsList;

    public static void handleCommand(GuildMessageReceivedEvent event){
        CommandContext context = new CommandContext(event);
    }
}
