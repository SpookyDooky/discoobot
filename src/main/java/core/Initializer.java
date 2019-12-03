package core;

import commandstuff.CommandDetails;
import commandstuff.commands.random.Huts;
import commandstuff.commands.support.GetInvite;
import commandstuff.commands.support.Help;
import commandstuff.commands.voice.*;
import commandstuff.commands.misc.*;

/**
 * Class that takes care of initialization of the bot
 * ATM primarily takes care of initializing all the available commandstuff
 */
public class Initializer {

    private static Bot instance;

    public static void initializeBot(){
        new Bot();
        instance = Bot.getInstance();
        initCommands();
    }

    private static void initCommands(){
        initMiscCommands();
        initRandomCommands();
        initSupportCommands();
        initVoiceCommands();
    }

    //Misc commandstuff
    private static void initMiscCommands(){
        CommandDetails chooseDetails = new CommandDetails(1,100, true, true,false);
        instance.addCommand(new Choose(),chooseDetails);
    }

    //Random commandstuff
    private static void initRandomCommands(){
        CommandDetails hutsDetails = new CommandDetails(0,0,false,false,false);
        instance.addCommand(new Huts(),hutsDetails);
    }

    //Support commandstuff
    private static void initSupportCommands(){
        CommandDetails getInviteDetails = new CommandDetails(0,0,false,false,false);
        instance.addCommand(new GetInvite(),getInviteDetails);

        CommandDetails helpDetails = new CommandDetails(0,1,false,true,false);
        instance.addCommand(new Help(),helpDetails);
    }

    //Voice commandstuff
    private static void initVoiceCommands(){
        CommandDetails joinChannelDetails = new CommandDetails(0,0,false,false,false);
        instance.addCommand(new JoinChannel(),joinChannelDetails);

        CommandDetails leaveChannelDetails = new CommandDetails(0,0,false,false,false);
        instance.addCommand(new LeaveChannel(),leaveChannelDetails);

        CommandDetails clipDetails = new CommandDetails(0,1,false,true,false);
        instance.addCommand(new Clip(), clipDetails);
    }
}
