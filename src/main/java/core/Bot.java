package core;

import commandstuff.CommandDetails;
import commandstuff.command_interfaces.ICommand;
import core.managers.VoiceManager;

import java.util.ArrayList;
import java.util.HashSet;

public class Bot {

    ArrayList<ICommand> commandList;
    ArrayList<CommandDetails> commandDetailsList;

    private HashSet<String> whiteList;

    private static Bot instance;
    private static VoiceManager voiceManager;

    public Bot(){
        this.commandList = new ArrayList<ICommand>();
        this.commandDetailsList = new ArrayList<CommandDetails>();
        voiceManager = new VoiceManager(3);
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
        commandList.add(theCommand);
        commandDetailsList.add(info);
    }

    public int amountCommands(){
        return this.commandList.size();
    }

    public boolean whiteListContains(String userId){
        return this.whiteList.contains(userId);
    }

    public void addToWhiteList(String userId){
        this.whiteList.add(userId);
    }
}
