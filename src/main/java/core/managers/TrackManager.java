package core.managers;

import core.utils.Track;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class that makes sure not too many tracks are stored in the memory per guild
 *
 * Might put a hard total limit on this in the future so that it never exceeds 1000 in total
 */
public class TrackManager {

    private HashMap<String, Track> trackMap;

    private Queue<String> trackHistory;

    private int tracks;
    private int maxTracks;

    public TrackManager(int maxTracks){
        this.trackMap = new HashMap<>();
        this.trackHistory = new LinkedList<>();
        this.tracks = 0;
        this.maxTracks = maxTracks;
    }

    /**
     * Method that takes care of enforcing the track limit per guild in the memory
     * @param track - New track to add
     * @param name - Name of the track
     */
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

    /**
     * Returns a track with a specific name
     * @param name - name of the track
     * @return - The track
     */
    public Track getTrack(String name){
        if(trackMap.containsKey(name)){
            return trackMap.get(name);
        }
        return null;
    }
}
