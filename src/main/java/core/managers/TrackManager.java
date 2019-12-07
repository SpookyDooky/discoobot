package core.managers;

import core.utils.Track;

import java.util.HashMap;
import java.util.Queue;

public class TrackManager {

    private HashMap<String, Track> trackMap;

    private Queue<String> trackHistory;

    private int tracks;
    private int maxTracks;

    public TrackManager(){
        this.trackMap = new HashMap<>();
        this.tracks = 0;
        this.maxTracks = 10;
    }

    public void addTrack(Track track, String name){
        trackMap.put(name,track);
        trackHistory.offer(name);
        tracks++;
        if(tracks > maxTracks){
            String toRemove = trackHistory.poll();
            Track theTrack = trackMap.remove(toRemove);
            tracks--;
        }
    }
}
