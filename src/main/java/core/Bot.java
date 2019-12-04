package core;

import commandstuff.CommandDetails;
import commandstuff.command_interfaces.ICommand;
import core.managers.VoiceManager;
import org.javatuples.Pair;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Bot {

    private ArrayList<ICommand> commandList; //Todo - Make it so that when we need these they are built to free up memory
    private ArrayList<CommandDetails> commandDetailsList; //Now we have double the amount of what we need in the memory

    private HashMap<String, Pair<ICommand,CommandDetails>> commandMap;

    private HashSet<String> whiteList;

    private static Bot instance;
    private VoiceManager voiceManager;

    public Bot(){
        this.commandList = new ArrayList<ICommand>();
        this.commandDetailsList = new ArrayList<CommandDetails>();

        this.commandMap = new HashMap<>();
        this.voiceManager = new VoiceManager(3);
        instance = this;
        this.whiteList = new HashSet<>();
    }

    public static Bot getInstance(){
        return instance;
    }

    public VoiceManager getVoiceManager(){
        return voiceManager;
    }

    public void addCommand(ICommand theCommand, CommandDetails info){
        this.commandMap.put(theCommand.getCommandName(),new Pair<ICommand,CommandDetails>(theCommand,info));

        commandList.add(theCommand);
        commandDetailsList.add(info);
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
        return this.commandList;
    }

    public ArrayList<CommandDetails> getCommandDetailsList(){
        return this.commandDetailsList;
    }
}
