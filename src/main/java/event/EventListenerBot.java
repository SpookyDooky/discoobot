package event;

import core.CommandHandlerBot;
import core.managers.BotManager;
import core.managers.VoiceManager;
import core.utils.GuildInfo;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListenerBot extends ListenerAdapter {

    private static String id;
    private static boolean init = false;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        id = event.getGuild().getId();
        if(!init) {
            System.out.println(id);
            init = true;
        }

        CommandHandlerBot.handleCommand(event);
    }

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event){
        String guildID = event.getGuild().getId();
        GuildInfo info = BotManager.getInstance().getGuildInfo(guildID);
        VoiceManager voiceManager = info.getVoiceManager();
        if(voiceManager != null){
            voiceManager.userJoined(event);
        }
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event){
        String guildID = event.getGuild().getId();
        GuildInfo info = BotManager.getInstance().getGuildInfo(guildID);
        VoiceManager voiceManager = info.getVoiceManager();
        if(voiceManager != null){
            voiceManager.userLeft(event);
        }
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event){
        String guildID = event.getGuild().getId();
        GuildInfo info = BotManager.getInstance().getGuildInfo(guildID);
        VoiceManager voiceManager = info.getVoiceManager();
        if(voiceManager != null){
            voiceManager.userMoved(event);
        }
    }
}
