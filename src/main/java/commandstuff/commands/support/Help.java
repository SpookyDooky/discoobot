package commandstuff.commands.support;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.Bot;
import core.managers.BotChatManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Help implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        if(parameters == null){
            context.getChannel().sendMessage(help()).queue();
        } else {
            String cat = parameters[0];
            String message = "List of commands: \n";
            if(cat.equalsIgnoreCase("all")){
                for(ICommand command : Bot.getInstance().getCommandList()){
                    if(!command.category().equalsIgnoreCase("admin")) {
                        message += "**!!" + command.getCommandName() + "** - ";
                        message += command.help();
                        message += "\n";
                    }
                }
            } else {
                for(ICommand command : Bot.getInstance().getCommandList()) {
                    if (command.category().equalsIgnoreCase(cat)) {
                        message += "**!!" + command.getCommandName() + "** - ";
                        message += command.help();
                        message += "\n";
                    }
                }
            }
            BotChatManager.sendMessage(message,context.getChannel());
        }
    }

    @Override
    public String help() {
        return "To use the help command: either use !!help + all/voice/misc/random/support";
    }

    @Override
    public String category() {
        return "support";
    }

    @Override
    public String getCommandName() {
        return "help";
    }
}
