package core;

import commandstuff.CommandDetails;
import commandstuff.commands.admin.*;
import commandstuff.commands.random.*;
import commandstuff.commands.support.*;
import commandstuff.commands.voice.*;
import commandstuff.commands.misc.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
        System.out.println("INITIALIZING");
        initWhiteList();
        initAdminCommands();
        initMiscCommands();
        initRandomCommands();
        initSupportCommands();
        initVoiceCommands();
    }

    private static void initWhiteList(){
        File whiteList = new File("src/main/resources/whitelist/whitelist.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(whiteList));
            String line = reader.readLine();
            while(line != null){
                instance.addToWhiteList(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Admin commands
    private static void initAdminCommands(){
        CommandDetails restartDetails = new CommandDetails(0,0,false,false,true);
        instance.addCommand(new Restart(),restartDetails);
    }

    //Misc commands
    private static void initMiscCommands(){
        CommandDetails chooseDetails = new CommandDetails(1,100, true, true,false);
        instance.addCommand(new Choose(),chooseDetails);
    }

    //Random command
    private static void initRandomCommands(){
        CommandDetails hutsDetails = new CommandDetails(0,0,false,false,false);
        instance.addCommand(new Huts(),hutsDetails);
    }

    //Support commands
    private static void initSupportCommands(){
        CommandDetails getInviteDetails = new CommandDetails(0,0,false,false,false);
        instance.addCommand(new GetInvite(),getInviteDetails);

        CommandDetails helpDetails = new CommandDetails(0,1,false,true,false);
        instance.addCommand(new Help(),helpDetails);
    }

    //Voice commands
    private static void initVoiceCommands(){
        CommandDetails joinChannelDetails = new CommandDetails(0,0,false,false,false);
        instance.addCommand(new JoinChannel(),joinChannelDetails);

        CommandDetails leaveChannelDetails = new CommandDetails(0,0,false,false,false);
        instance.addCommand(new LeaveChannel(),leaveChannelDetails);

        CommandDetails clipDetails = new CommandDetails(0,1,false,true,false);
        instance.addCommand(new Clip(), clipDetails);
    }
}
