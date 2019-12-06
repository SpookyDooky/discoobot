package core.managers;

import core.utils.Track;

import java.util.HashMap;

public class TrackManager {

    private HashMap<String, Track> trackMap;

    private int tracks;
    private int maxTracks;

    public TrackManager(){
        this.trackMap = new HashMap<>();
        this.tracks = 0;
        this.maxTracks = 10;
    }


}
