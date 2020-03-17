package core;

import com.google.gson.Gson;
import commandstuff.CommandDetails;
import commandstuff.commands.admin.*;
import commandstuff.commands.random.*;
import commandstuff.commands.support.*;
import commandstuff.commands.voice.*;
import commandstuff.commands.misc.*;
import core.managers.BotManager;
import core.utils.jsonmodels.SoundJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Class that takes care of initialization of the bot
 * ATM primarily takes care of initializing all the available commandstuff
 */
public class Initializer {

    private static BotManager instance;
    private static final Logger logger = LoggerFactory.getLogger(Initializer.class);

    private static String[] soundNames;

    public static void initializeBot(){
        new BotManager();
        instance = BotManager.getInstance();
        logger.info("Initializing commands and white lists");
        initCommands();
        logger.info("Initialization of commands has been successful");
    }

    private static void initCommands(){
        //WhiteList initialization
        initWhiteList();

        //Command initialization
        initAdminCommands();
        initMiscCommands();
        initRandomCommands();
        initSupportCommands();
        initVoiceCommands();

        //Sounds initialization
        logger.info("Sounds names are being initialized");
        initSoundNames();
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
        instance.addCommand(new Shutdown(),restartDetails);

    }

    //Misc commands
    private static void initMiscCommands(){
        CommandDetails chooseDetails = new CommandDetails(1,100, true, true,false);
        instance.addCommand(new Choose(),chooseDetails);

        CommandDetails getQuoteDetails = new CommandDetails(0,0,false,false,false);
        instance.addCommand(new GetQuote(),getQuoteDetails);

        CommandDetails addQuoteDetails = new CommandDetails(1,1,false,true,false);
        instance.addCommand(new AddQuote(),addQuoteDetails);

        CommandDetails deleteQuoteDetails = new CommandDetails(1,1,false,true,false);
        instance.addCommand(new DeleteQuote(),deleteQuoteDetails);

        CommandDetails upvoteQuoteDetails = new CommandDetails(1,1,false,true,false);
        instance.addCommand(new UpvoteQuote(),upvoteQuoteDetails);

        CommandDetails downvoteQuoteDetails = new CommandDetails(1,1,false,true,false);
        instance.addCommand(new DownvoteQuote(),downvoteQuoteDetails);
    }

    //Random commands
    private static void initRandomCommands(){
        CommandDetails hutsDetails = new CommandDetails(0,0,false,false,false);
        instance.addCommand(new Huts(),hutsDetails);

        CommandDetails clapDetails = new CommandDetails(1,1,false,true,false);
        instance.addCommand(new Clap(),clapDetails);
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

        CommandDetails trimDetails = new CommandDetails(3, 3,true,true,false);
        instance.addCommand(new Trim(),trimDetails);
    }

    //Sounds, uses a json file for all the sound names
    private static void initSoundNames(){
        //TODO - Make sure it automatically makes all commands for the sounds
        File soundNames = new File("src/main/resources/sounds/soundlist/sounds.json");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(soundNames));
            Gson gson = new Gson();
            SoundJSON sounds = gson.fromJson(reader,SoundJSON.class);

            logger.info("Sound names loaded, amount: " + sounds.getAmount());
            logger.info("Initializing sound commands");
            initSoundCommands(sounds);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Creates all the various sound commands
    private static void initSoundCommands(SoundJSON sounds){
        List<String> names = sounds.getSoundNames();
        CommandDetails details = new CommandDetails(0,0,false,false,false);
        for(int x = 0; x < names.size();x++){
            SoundEffect sound = new SoundEffect(names.get(x));
            instance.addCommand(sound,details);
        }
        logger.info("Sound commands have been successfully initialized");
    }
}
