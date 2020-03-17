package core.managers;

import commandstuff.CommandDetails;
import commandstuff.command_interfaces.ICommand;
import core.utils.GuildInfo;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class for managing the bot, making sure each guild has its own voice manager when necessary
 * Also removes objects that aren't used anymore like if a guild is not using its voice manager we would rather remove it from the memory
 */
@SuppressWarnings("duplicate")
public class BotManager {

    private static final Logger logger = LoggerFactory.getLogger(BotManager.class);

    public static BotManager instance;
    private HashMap<String, GuildInfo> shards;

    private HashMap<String, Pair<ICommand, CommandDetails>> commandMap;
    private HashSet<String> whiteList;

    private TrackManager trackManager;

    //Todo - make it more memory conservative
    public BotManager(){
        this.shards = new HashMap<>();
        this.commandMap = new HashMap<>();
        this.whiteList = new HashSet<>();
        this.trackManager = new TrackManager(200); //Only has 1 track manager to enforce a hard maximum of the total amount of tracks in memory
        instance = this;
    }

    /**
     * For retrieving the only instance of this class
     * @return - The instance
     */
    public static BotManager getInstance(){
        return instance;
    }

    public boolean whiteListContains(String userId){
        return this.whiteList.contains(userId);
    }

    public void addToWhiteList(String userId){
        this.whiteList.add(userId);
    }

    /**
     * Method to add a command to the command list of the bot so that it becomes executable
     * @param theCommand - command interface
     * @param details - command details, information about the command
     */
    public void addCommand(ICommand theCommand, CommandDetails details){
        this.commandMap.put(theCommand.getCommandName(),new Pair<ICommand,CommandDetails>(theCommand, details));
    }

    /**
     * Method to check if a specific command exists
     * @param command - command name
     * @return - boolean
     */
    public boolean commandExists(String command){
        return this.commandMap.containsKey(command);
    }

    /**
     * Returns the information about a specific command
     * @param command - name of the command
     * @return - information about the command
     */
    public Pair<ICommand, CommandDetails> getCommandInfo(String command){
        return this.commandMap.get(command);
    }

    /**
     * Returns a list of all commands that the bot can execute
     * @return - The list
     */
    public ArrayList<ICommand> getCommandList(){
        Collection<Pair<ICommand,CommandDetails>> values = this.commandMap.values();
        ArrayList<ICommand> commands = new ArrayList<>();

        for (Pair<ICommand, CommandDetails> value : values) {
            commands.add(value.getValue0());
        }

        return commands;
    }

    /**
     * Returns the track manager of the bot
     * @return - track manager
     */
    public TrackManager getTrackManager(){
        return this.trackManager;
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

}
