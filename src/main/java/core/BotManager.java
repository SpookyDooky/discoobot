package core;

import core.managers.VoiceManager;
import core.utils.GuildInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Class for managing the bot, making sure each guild has its own voice manager when necessary
 * Also removes objects that aren't used anymore like if a guild is not using its voice manager we would rather remove it from the memory
 * @Author - Ray
 */
public class BotManager {

    public static BotManager instance;
    private HashMap<String, GuildInfo> shards;

    private HashMap<String, VoiceManager> voiceManager;

    private static final Logger logger = LoggerFactory.getLogger(BotManager.class);

    //Todo - make it memory conservative
    public BotManager(){
        this.shards = new HashMap<>();
        instance = this;
    }

    /**
     * For retrieving the only instance of this class
     * @return - The instance
     */
    public static BotManager getInstance(){
        return instance;
    }

    /**
     * Looks up a guild, on guild id
     * @param id - the guilds id
     * @return - The guild, if we have data on it
     */
    public GuildInfo getGuildInfo(String id){
        GuildInfo result = this.shards.get(id);
        if(result == null){
            logger.warn("Could not find guild: " + id);
            logger.info("Attempting to initialize guild: " + id);

            GuildInfo info = new GuildInfo(id);
            this.shards.put(id,info);

            logger.info("Succesfully initialized guild: " + id);
            return info;
        }
        return result;
    }

    /**
     * Destroys all voice connections
     */
    public void destroyAllConnections(){
        for(GuildInfo info : shards.values()){
            VoiceManager manager = info.getVoiceManager();
            if(manager != null){
                manager.disconnect();
            }
        }
    }

    public void containsGuild(String guildId){

}
}
