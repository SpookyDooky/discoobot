package commandstuff.commands.voice;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Trim implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        int startTime = Integer.valueOf(parameters[0]);
        int endTime = Integer.valueOf(parameters[1]);

        if(startTime < endTime){
            String fileName = parameters[2];
        } else {
            context.getChannel().sendMessage("Startime needs to be smaller than endtime").queue();
        }
    }

    @Override
    public String help() {
        return "command to trim clips, usage: \n";
    }

    @Override
    public String category() {
        return "voice";
    }

    @Override
    public String getCommandName() {
        return "trim";
    }
}
