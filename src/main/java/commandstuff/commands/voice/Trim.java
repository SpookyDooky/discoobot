package commandstuff.commands.voice;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Trim implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        String times = parameters[0];
        if(times.contains(":")){
            String[] split = times.split(":");
        } else {
            context.getChannel().sendMessage("Wrong command usage: \n " + help()).queue();
        }
    }

    @Override
    public String help() {
        return "command to trim clips, usage: \n" +
                "!!trim Start:End if you dont set a start time it will take the clip from the beginning to the end time"
                + "if you don't give an end time it will take it from the start point all the way to the end \n" +
                "start and end are in seconds";
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
