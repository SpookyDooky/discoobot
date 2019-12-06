package commandstuff.commands.support;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class GetInvite implements ICommand {
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        context.getChannel().sendMessage("https://discordapp.com/api/oauth2/authorize?client_id=650727553836777492&permissions=8&scope=bot").queue();
    }

    @Override
    public String help() {
        return "gives the invite link for the bot";
    }

    @Override
    public String category() {
        return "support";
    }

    @Override
    public String getCommandName() {
        return "invitelink";
    }
}
