package core;

import commandstuff.CommandDetails;
import commandstuff.command_interfaces.ICommand;

import java.util.ArrayList;

public class Bot {

    ArrayList<ICommand> commandList;
    ArrayList<CommandDetails> commandDetailsList;

    private static Bot instance;
    private static VoiceManager voiceManager;

    public Bot(){
        this.commandList = new ArrayList<ICommand>();
        this.commandDetailsList = new ArrayList<CommandDetails>();
        voiceManager = new VoiceManager(3);
        instance = this;
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
}
