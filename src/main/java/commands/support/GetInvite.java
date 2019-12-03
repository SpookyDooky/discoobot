package commands.support;

import commands.CommandContext;
import commands.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class GetInvite implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        context.getChannel().sendMessage("https://discordapp.com/oauth2/authorize?client_id=650727553836777492&permissions=0&scope=bot").queue();
    }

    @Override
    public String help() {
        return "gives the invite link for the bot";
    }

    @Override
    public String getCommandName() {
        return "invitelink";
    }
}
