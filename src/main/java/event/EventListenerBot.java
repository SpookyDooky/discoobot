package event;

import core.Bot;
import core.CommandHandlerBot;
import core.Initializer;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListenerBot extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        //TODO - Make sure the guildID becomes known so that we can load quotes and such
        if(Bot.getInstance().isGuildInit()){
            CommandHandlerBot.handleCommand(event);
        } else {
            Initializer.initData(event);
            CommandHandlerBot.handleCommand(event);
        }

    }
}
