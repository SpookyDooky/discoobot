package core;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
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
        this.manager = null;
    }

    public boolean canReceiveCombined(){
        return true;
    }

    public boolean canProvide() {
        return true;
    }

    @Nullable
    public ByteBuffer provide20MsAudio() {
        return null;
    }

    public void handleCombinedAudio(@Nonnull CombinedAudio audio){
        byte[] data = audio.getAudioData(1.0f);

        if(this.history.size() >= mins * 50 * 60 + 50){
            this.history.poll();
            this.history.offer(data);
        } else {
            this.history.offer(data);
        }
    }


    public void connectTo(VoiceChannel channel, AudioManager manager){
        if(this.manager == null) {
            this.manager = manager;
            this.manager.setSendingHandler(this);
            this.manager.setReceivingHandler(this);
        }

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

    public ArrayList<Byte> getPCM_Stream(int seconds){
        int sampleSize = seconds * 50;
        ArrayList<byte[]> result = new ArrayList<byte[]>();

        Iterator<byte[]> iterator = this.history.iterator();
        int max = sampleSize;
        if(this.history.size() < max){
            max = this.history.size();
        }

        for(int x = 0; x < max;x++){
            result.add(iterator.next());
        }

        ArrayList<Byte> finalResult = new ArrayList<Byte>();
        int index = 0;
        for(int x = 0;x < result.size();x++){
            for(int y = 0; y < result.get(x).length;y++){
                finalResult.add(result.get(x)[y]);
            }
        }
        return finalResult;
    }
}
