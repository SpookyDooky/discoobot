package core;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class VoiceManager implements AudioReceiveHandler, AudioSendHandler {

    private int connections;
    private AudioManager manager;

    private Queue<byte[]> history;
    private int mins;
    private int stuffed;

    public VoiceManager(int mins){
        this.connections = 0;
        this.history = new ConcurrentLinkedQueue<byte[]>();
        this.mins = mins;
        this.stuffed = 0;
    }

    public boolean canProvide() {
        return !history.isEmpty();
    }

    @Nullable
    public ByteBuffer provide20MsAudio() {
        byte[] data = history.poll();
        return data == null ? null : ByteBuffer.wrap(data);
    }

    public void handleCombinedAudio(CombinedAudio audio){
        byte[] data = audio.getAudioData(1.0f);

        if(this.history.size() >= mins * 50 * 60 + 50){
            this.history.poll();
            this.history.offer(data);
        } else {
            stuffed++;
            this.history.offer(data);
        }
    }


    public void connectTo(VoiceChannel channel, AudioManager manager){
        this.manager = manager;

        if(connections == 0){
            this.manager.openAudioConnection(channel);
            this.connections++;
        }
    }

    public void disconnect(){
        if(connections > 0){
            this.manager.closeAudioConnection();
            connections--;
        }
    }

    public byte[] getPCM_Stream(int seconds){
        int sampleSize = seconds * 50;
        byte[] result = new byte[sampleSize];

        return result;
    }
}
