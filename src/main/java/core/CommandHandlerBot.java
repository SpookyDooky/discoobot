package core;

import commandstuff.CommandContext;
import commandstuff.CommandDetails;
import commandstuff.command_interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.javatuples.Pair;

public class CommandHandlerBot {

    /**
     * Splits the prefix from the name
     * @param event - Guild event
     */
    public static void handleCommand(GuildMessageReceivedEvent event){
        CommandContext context = new CommandContext(event);

        String command = context.getMessage();
        if(command.length() > 2 && command.charAt(0) == 33 && command.charAt(1) == 33){
            String[] splitted = command.split(" ", 2);
            findCommand(splitted, context, event);
        }
    }

    private static void findCommand(String[] commandSliced, CommandContext context, GuildMessageReceivedEvent event){
        String commandName = commandSliced[0].toLowerCase().replaceAll("!","");

        ICommand command = null;
        CommandDetails details = null;
        if(Bot.getInstance().commandExists(commandName)){
            Pair<ICommand,CommandDetails> info = Bot.getInstance().getCommandInfo(commandName);
            command = info.getValue0();
            details = info.getValue1();
        } else {
            context.getChannel().sendMessage("ThAt CoMmAnD dOeS nOt ExIsT nIfFo...").queue();
            return;
        }

        if(details.isWhiteList()){
            if(!checkWhiteList(context)){
                context.getChannel().sendMessage("You do not have permission to use this command :o").queue();
                return;
            }
        }

        if(details.hasParameters()){
            if(commandSliced.length > 1) {
                cutParameters(command, details , commandSliced[1], context, event);
            } else {
                command.execute(event,null,context);
            }
        } else {
            command.execute(event,null,context);
        }

    }

    private static void cutParameters(ICommand command, CommandDetails details, String parameters, CommandContext context, GuildMessageReceivedEvent event){
        if(details.getMaxParameters() == 1){
            String[] paras = {parameters};
            command.execute(event,paras,context);
            return;
        }

        if(details.commaNeedsRemoval()){
            String[] separated = parameters.split(", ");
            if(separated.length == 1) {
                separated = parameters.split(",");
            }

            if(separated.length >= details.getMinParameters() && separated.length <= details.getMaxParameters()){
                command.execute(event,separated,context);
            } else {
                context.getChannel().sendMessage("Wrong command usage: \n" + command.help()).queue();
            }
        }
    }

    private static boolean checkWhiteList(CommandContext context){
        return Bot.getInstance().whiteListContains(context.getUser().getId());
    }
}
