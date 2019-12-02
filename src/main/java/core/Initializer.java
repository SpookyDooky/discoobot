package core;

import commands.CommandDetails;
import commands.voice.*;
import commands.misc.*;

public class Initializer {

    private static Bot instance;

    public static void initializeBot(){
        new Bot();
        instance = Bot.getInstance();
        initCommands();
    }

    private static void initCommands(){
        //Voice commands
        CommandDetails joinChannelDetails = new CommandDetails(0,0,false,false);
        instance.addCommand(new JoinChannel(),joinChannelDetails);

        CommandDetails leaveChannelDetails = new CommandDetails(0,0,false,false);
        instance.addCommand(new LeaveChannel(),leaveChannelDetails);

        CommandDetails clipDetails = new CommandDetails(0,1,false,true);
        instance.addCommand(new Clip(), clipDetails);

        //Misc commands
        CommandDetails chooseDetails = new CommandDetails(1,100, true, true);
        instance.addCommand(new Choose(),chooseDetails);
    }

}
