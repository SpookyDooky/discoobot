package core;

import commands.CommandDetails;
import commands.voice.JoinChannel;

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
    }

}
