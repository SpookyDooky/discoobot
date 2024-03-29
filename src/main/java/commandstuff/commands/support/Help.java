package commandstuff.commands.support;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.utils.ChatUtils;
import core.managers.BotManager;
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
                for(ICommand command : BotManager.getInstance().getCommandList()){
                    if(!command.category().equalsIgnoreCase("admin") && !command.category().equals("sounds")) {
                        message += "**!!" + command.getCommandName() + "** - ";
                        message += command.help();
                        message += "\n";
                    }
                }
            } else {
                for(ICommand command : BotManager.getInstance().getCommandList()) {
                    if (command.category().equalsIgnoreCase(cat)) {
                        message += "**!!" + command.getCommandName() + "** - ";
                        message += command.help();
                        message += "\n";
                    }
                }
            }
            ChatUtils.sendMessage(message,context.getChannel());
        }
    }

    @Override
    public String help() {
        return "To use the help command: either use !!help + all/voice/misc/random/support/sounds";
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
