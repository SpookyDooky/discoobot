package commandstuff.commands.random;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Clap implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        String[] splittedWords = parameters[0].split(" ");
        String clapEmoji = ":clap:";
        String result = clapEmoji;

        for(String word : splittedWords){
            result += word + " " + clapEmoji;
        }

        context.getChannel().sendMessage(result).queue();
    }

    @Override
    public String help() {
        return "Surrounds every word with the clapping emoji for memes";
    }

    @Override
    public String category() {
        return "random";
    }

    @Override
    public String getCommandName() {
        return "clap";
    }
}
