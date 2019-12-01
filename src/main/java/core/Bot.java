package core;

import commands.CommandDetails;
import commands.ICommand;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.ArrayList;

public class Bot {

    ArrayList<ICommand> commandList;
    ArrayList<CommandDetails> commandDetailsList;

    private static Bot instance;

    public Bot(){
        this.commandList = new ArrayList<ICommand>();
        this.commandDetailsList = new ArrayList<CommandDetails>();

        this.instance = this;
    }

    public static Bot getInstance(){
        return instance;
    }

    public void addCommand(ICommand theCommand, CommandDetails info){
        commandList.add(theCommand);
        commandDetailsList.add(info);
    }

    public int amountCommands(){
        return this.commandList.size();
    }
}
