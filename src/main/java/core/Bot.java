package core;

import commandstuff.CommandDetails;
import commandstuff.command_interfaces.ICommand;
import core.managers.TrackManager;
import core.managers.VoiceManager;
import core.utils.GuildInfo;
import org.javatuples.Pair;


import java.util.*;

public class Bot {

    private HashMap<String, Pair<ICommand,CommandDetails>> commandMap;
    private HashSet<String> whiteList;

    private static Bot instance;
    private VoiceManager voiceManager;
    private TrackManager trackManager;

    private boolean initializedData;
    private GuildInfo info;

    public Bot(){
        this.commandMap = new HashMap<>();
        this.voiceManager = new VoiceManager(3);
        this. trackManager = new TrackManager(10); //Number is the maximum tracks in memory
        this.whiteList = new HashSet<>();
        this.initializedData = false;

        instance = this;
    }

    public static Bot getInstance(){
        return instance;
    }

    public VoiceManager getVoiceManager(){
        return voiceManager;
    }

    public TrackManager getTrackManager(){
        return this.trackManager;
    }

    public void addCommand(ICommand theCommand, CommandDetails info){
        this.commandMap.put(theCommand.getCommandName(),new Pair<ICommand,CommandDetails>(theCommand,info));
    }

    public boolean whiteListContains(String userId){
        return this.whiteList.contains(userId);
    }

    public void addToWhiteList(String userId){
        this.whiteList.add(userId);
    }

    public boolean commandExists(String command){
        return this.commandMap.containsKey(command);
    }

    public Pair<ICommand, CommandDetails> getCommandInfo(String command){
        return this.commandMap.get(command);
    }

    public ArrayList<ICommand> getCommandList(){
        Collection<Pair<ICommand,CommandDetails>> values = this.commandMap.values();
        ArrayList<ICommand> commands = new ArrayList<>();

        for (Pair<ICommand, CommandDetails> value : values) {
            commands.add(value.getValue0());
        }

        return commands;
    }

    public boolean isGuildInit(){
        return this.initializedData;
    }

    public void setInfo(GuildInfo info){
        if(!this.initializedData){
            this.info = info;
            this.initializedData = true;
        }
    }

    public GuildInfo getInfo(){
        return this.info;
    }
}
