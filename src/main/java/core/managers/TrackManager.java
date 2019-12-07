package core.managers;

import core.utils.Track;

import java.util.HashMap;
import java.util.Queue;

public class TrackManager {

    private HashMap<String, Track> trackMap;

    private Queue<String> trackHistory;

    private int tracks;
    private int maxTracks;

    public TrackManager(int maxTracks){
        this.trackMap = new HashMap<>();
        this.tracks = 0;
        this.maxTracks = maxTracks;
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

    public Track getTrack(String name){
        if(trackMap.containsKey(name)){
            return trackMap.get(name);
        }
        return null;
    }
}
