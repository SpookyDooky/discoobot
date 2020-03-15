package core;

public class Task {
    //Class for the future
    //Will be used for scheduling conversion task of sound tracks, attempt to make the bot more efficient so that when people spam sounds
    //It will not fill up all memory, also necessary for music in the future because we dont want a playlist of 100 tracks in the memory

    private String guildId; //-1 -> general bot task (flushing memory, removing unused voice managers, etc)

    public Task(String guildId){
        this.guildId = guildId;
    }
}
