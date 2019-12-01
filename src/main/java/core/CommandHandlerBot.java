package core;

import commands.CommandContext;
import commands.CommandDetails;
import commands.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandHandlerBot {


    public static void handleCommand(GuildMessageReceivedEvent event){
        CommandContext context = new CommandContext(event);

        String command = context.getMessage();
        if(command.charAt(0) == 33 && command.charAt(1) == 33){
            String[] splitted = command.split(" ", 2);
            findCommand(splitted, context, event);
        }
    }

    private static void findCommand(String[] commandSliced, CommandContext context, GuildMessageReceivedEvent event){
        String commandName = commandSliced[0].toLowerCase().replaceAll("!","");

        int index = 0;
        boolean exists = false;

        ICommand command = null;
        CommandDetails details = null;
        for(int x = 0; x < Bot.getInstance().amountCommands(); x++){
            if(commandName.equalsIgnoreCase(Bot.getInstance().commandList.get(x).getCommandName())){
                exists = true;
                index = x;
                command = Bot.getInstance().commandList.get(x);
                details = Bot.getInstance().commandDetailsList.get(x);
                break;
            }
        }

        if(!exists){
            context.getChannel().sendMessage("ThAt CoMmAnD dOeS nOt ExIsT nIfFo...").queue();
            return;
        }

        if(details.hasParameters()){
            cutParameters(index, commandSliced[1],context,event);
        } else {
            command.execute(event,null,context);
        }

    }

    private static void cutParameters(int index, String parameters, CommandContext context, GuildMessageReceivedEvent event){
        ICommand command = Bot.getInstance().commandList.get(index);
        CommandDetails details = Bot.getInstance().commandDetailsList.get(index);

        if(details.commaNeedsRemoval()){
            String[] separated = parameters.split(", ");
            if(separated.length == 1) {
                separated = parameters.split(",");
            }

            if(separated.length >= details.getMinParameters() && separated.length <= details.getMaxParameters()){
                command.execute(event,separated,context);
            } else {
                context.getChannel().sendMessage("Parameters exceed limites").queue();
            }
        }
    }
}
