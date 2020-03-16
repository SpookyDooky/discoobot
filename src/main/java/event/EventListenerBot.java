package event;

import core.CommandHandlerBot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListenerBot extends ListenerAdapter {

    private static String id;
    private static boolean init = false;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        //TODO - Make sure the guildID becomes known so that we can load quotes and such
        id = event.getGuild().getId();
        if(!init) {
            System.out.println(id);
            init = true;
        }

        CommandHandlerBot.handleCommand(event);
    }
}
