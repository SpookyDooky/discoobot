package commandstuff.commands.misc;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Choose implements ICommand {
    private String[] sentences = {
            "The lord has spoken it will be: ", "I have concluded that it will be: ", "All shall perish, except this one: ",
            "Praise lord GabeN he has blessed us with his decision(wow): ", "Apparently the board has (poorly) decided: ",
            "After debating for hours with mee6 I have come to the conclusion of: ", "The prophecy mentioned: ", "Sadly the winner is: "
    };

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        int choices = parameters.length;
        double chance = 1.0 / choices;

        double randomNum =  Math.random();
        int decision = (int)(randomNum / chance);

        int sent = (int) (Math.random() * sentences.length);

        String message = sentences[sent] + parameters[decision];
        context.getChannel().sendMessage(message).queue();
    }

    @Override
    public String help() {
        return "Makes a decision between options, usage: !!choose option1, option2, ... etc";
    }

    @Override
    public String category() {
        return "misc";
    }

    @Override
    public String getCommandName() {
        return "choose";
    }
}
