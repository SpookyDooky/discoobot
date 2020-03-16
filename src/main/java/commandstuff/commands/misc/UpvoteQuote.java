package commandstuff.commands.misc;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.managers.BotManager;
import core.utils.jsonmodels.GuildQuotesJSON;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class UpvoteQuote implements ICommand {

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        int quoteID = Integer.valueOf(parameters[0]);
        String guildID = event.getGuild().getId();

        GuildQuotesJSON quotes = BotManager.getInstance().getGuildInfo(guildID).getQuotes();
        boolean success = quotes.upvoteQuote(quoteID);

        if(!success){
            event.getChannel().sendMessage("Could not upvote quote, quote ID does not exist").queue();
        } else {
            event.getChannel().sendMessage("Succesfully upvoted quote: " + quotes.getQuotes().get(quoteID).getQuote()).queue();
        }
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String category() {
        return null;
    }

    @Override
    public String getCommandName() {
        return null;
    }
}
