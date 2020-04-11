package commandstuff.commands.misc;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.managers.BotManager;
import core.utils.GuildInfo;
import core.utils.jsonmodels.QuoteJSON;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class DeleteQuote implements ICommand {

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        String quoteId = parameters[0];
        try {
            int quoteIndex = Integer.valueOf(quoteId);

            if(quoteIndex < 0){
                event.getChannel().sendMessage("Please make sure you put in a positive number.").queue();
                return;
            }

            GuildInfo info = BotManager.getInstance().getGuildInfo(event.getGuild().getId());
            QuoteJSON quote = info.getQuotes().getQuotes().get(quoteIndex);

            info.getQuotes().deleteQuote(quoteIndex);

            event.getChannel().sendMessage("Successfully removed the quote: \n" + "\"" + quote.getQuote() + "\"").queue();
        } catch(NumberFormatException e){
            event.getChannel().sendMessage("Please make sure you put in a number.").queue();
        }
    }

    @Override
    public String help() {
        return "Command to delete a quote: !!deletequote quoteID";
    }

    @Override
    public String category() {
        return "misc";
    }

    @Override
    public String getCommandName() {
        return "deletequote";
    }
}
