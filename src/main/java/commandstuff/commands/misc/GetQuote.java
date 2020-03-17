package commandstuff.commands.misc;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.managers.BotManager;
import core.utils.GuildInfo;
import core.utils.jsonmodels.GuildQuotesJSON;
import core.utils.jsonmodels.QuoteJSON;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class GetQuote implements ICommand {

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        String guildId = event.getGuild().getId();
        GuildInfo info = BotManager.getInstance().getGuildInfo(guildId);

        GuildQuotesJSON quotes = info.getQuotes();
        QuoteJSON quoteObject = quotes.getRandomQuote();
        if(quotes.getAmount() == 0){
            event.getChannel().sendMessage("There are no quotes stored for this guild :o").queue();
        } else {
            event.getChannel().sendMessage("**" + quoteObject.getQuote() + "**" + "\n quoteID: " + quoteObject.getId() + ", upvotes: " +
                    quoteObject.getUpvotes() + ", downvotes: " + quoteObject.getDownvotes()).queue();
        }
    }

    @Override
    public String help() {
        return "Returns a random quote for your guild";
    }

    @Override
    public String category() {
        return "misc";
    }

    @Override
    public String getCommandName() {
        return "randomquote";
    }
}
