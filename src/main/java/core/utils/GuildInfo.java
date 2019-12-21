package core.utils;

import core.managers.VoiceManager;

public class GuildInfo {

    private String guildID;
    private VoiceManager voiceManager;

    public GuildInfo(String guildID){
        this.guildID = guildID;
        this.voiceManager = null; //Don't automatically make it to conserve memory
    }

    public String getGuildID(){
        return this.guildID;
    }

    public VoiceManager getVoiceManager(){
        if(this.voiceManager == null){
            initVoiceManager();
        }
        return this.voiceManager;
    }

    private void initVoiceManager(){
        this.voiceManager = new VoiceManager(1);
    }
}
