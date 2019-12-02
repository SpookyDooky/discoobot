package core;

import commands.CommandDetails;
import commands.voice.Clip;
import commands.voice.JoinChannel;
import commands.voice.LeaveChannel;

public class Initializer {

    private static Bot instance;

    public static void initializeBot(){
        new Bot();
        instance = Bot.getInstance();
        initCommands();
    }

    private static void initCommands(){
        CommandDetails joinChannelDetails = new CommandDetails(0,0,false,false);
        instance.addCommand(new JoinChannel(),joinChannelDetails);

        CommandDetails leaveChannelDetails = new CommandDetails(0,0,false,false);
        instance.addCommand(new LeaveChannel(),leaveChannelDetails);

        CommandDetails clipDetails = new CommandDetails(0,1,false,true);
        instance.addCommand(new Clip(), clipDetails);
    }

}
