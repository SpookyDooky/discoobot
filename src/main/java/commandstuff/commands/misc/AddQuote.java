package commandstuff.commands.misc;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.managers.BotManager;
import core.utils.GuildInfo;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class AddQuote implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        String username = event.getAuthor().getName();
        String quote = parameters[0];

        System.out.println(quote);
        GuildInfo info = BotManager.getInstance().getGuildInfo(event.getGuild().getId());
        info.getQuotes().addQuote(quote,username);

        event.getChannel().sendMessage("Successfully added the quote to the guild!").queue();
    }

    @Override
    public String help() {
        return "Adds a quote to guild, usage: !!addquote ";
    }

    @Override
    public String category() {
        return "misc";
    }

    @Override
    public String getCommandName() {
        return "addquote";
    }
}
