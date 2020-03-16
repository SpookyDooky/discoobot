package core;

import commandstuff.CommandDetails;
import commandstuff.command_interfaces.ICommand;
import core.managers.TrackManager;
import core.utils.GuildInfo;
import org.javatuples.Pair;


import java.util.*;

public class Bot {

    private HashMap<String, Pair<ICommand,CommandDetails>> commandMap;
    private HashSet<String> whiteList;

    private static Bot instance;
    private TrackManager trackManager;

    private GuildInfo info;

    public Bot(){
        this.commandMap = new HashMap<>();
        this.trackManager = new TrackManager(100); //Number is the maximum tracks in memory
        this.whiteList = new HashSet<>();

        instance = this;
    }

    public static Bot getInstance(){
        return instance;
    }


    public TrackManager getTrackManager(){
        return this.trackManager;
    }

}
