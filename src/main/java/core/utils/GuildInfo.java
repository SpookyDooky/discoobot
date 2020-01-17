package core.utils;

import core.Initializer;
import core.managers.VoiceManager;
import core.utils.jsonmodels.GuildQuotesJSON;

public class GuildInfo {

    private String guildID;
    private VoiceManager voiceManager;
    private GuildQuotesJSON quotes;

    public GuildInfo(String guildID){
        this.guildID = guildID;
        this.voiceManager = null; //Don't automatically make it to conserve memory
        this.quotes = null;
    }

    public String getGuildID(){
        return this.guildID;
    }

    public VoiceManager getVoiceManager(){
        if(this.voiceManager == null){
            this.voiceManager = new VoiceManager(1);
        }
        return this.voiceManager;
    }

    public GuildQuotesJSON getQuotes(){
        if(this.quotes == null){
            this.quotes = GuildQuotesJSON.initGuildQuotes(this.guildID);
        }
        return this.quotes;
    }
}
