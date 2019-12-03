package commandstuff.commands.random;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Huts implements ICommand {
    String[] sentences = {
            "Fakka strijders", "Liever stank voor iedereen dan pijn voor mij alleen.", "no u", "Geen aids in mijn melkherberg!", "Hiv+ is nog steeds positief zijn!",
            "ewa niffo", "Mooie schoenen, neuken?", "Say sike right now", "Hmmmm lekker"
    };
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        int index = (int)(Math.random() * sentences.length);
        context.getChannel().sendMessage(sentences[index]).queue();
    }

    @Override
    public String help() {
        return "Returns a random funny piece of text";
    }

    @Override
    public String category() {
        return "random";
    }

    @Override
    public String getCommandName() {
        return "huts";
    }
}
