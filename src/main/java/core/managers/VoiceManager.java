package core.managers;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.*;

public class VoiceManager implements AudioReceiveHandler, AudioSendHandler {

    private int usersConnected;
    private AudioManager manager;
    private VoiceChannel connectedTo;

    private Deque<byte[]> history; //Voice history buffer
    private int mins;


    private Queue<byte[]> voiceBuffer; //Voice play buffer

    /**
     * Takes care of basic voice functions, like connecting, disconnecting, playing audio, also holds the voice history
     * and the voice play buffer
     * @param mins - Size of the voice history buffer
     */
    public VoiceManager(int mins){
        this.history = new ArrayDeque<byte[]>();
        this.mins = mins;
        this.voiceBuffer = new LinkedList<>();
    }

    public boolean canReceiveCombined(){
        return true;
    }

    public boolean canProvide() {
        return true;
    }

    /**
     * lets the bot play sounds
     * @return - Byte buffer for 20ms of audio
     */
    @Nullable
    public ByteBuffer provide20MsAudio() {
        if(!voiceBuffer.isEmpty()){
            return ByteBuffer.wrap(voiceBuffer.poll());
        }
        return null;
    }

    /**
     * Takes care of storing the audio for clipping
     * @param audio - Combined user audio
     */
    public void handleCombinedAudio(@Nonnull CombinedAudio audio){
        byte[] data = audio.getAudioData(1.0f);

        if(this.history.size() >= mins * 50 * 60 + 50){
            this.history.pollLast();
            this.history.offerFirst(data);
        } else {
            this.history.offerFirst(data);
        }
    }

    /**
     * Connect to voice channel
     * @param channel - The channel
     * @param manager - Guild AudioManager
     * @return - Boolean, true -> connection was successful otherwise false
     */
    public boolean connectTo(VoiceChannel channel, AudioManager manager){
        if(this.manager == null) {
            this.manager = manager;
            this.manager.setSendingHandler(this);
            this.manager.setReceivingHandler(this);
        }

        if(channel == null){
            return false;
        }

        if(this.connectedTo == null){
            this.manager.openAudioConnection(channel);
            this.connectedTo = channel;
            this.usersConnected = channel.getMembers().size();
            return true;
        }
        return false;
    }

    /**
     * Disconnect from channel
     */
    public void disconnect(){
        if(this.connectedTo != null){
            this.manager.closeAudioConnection();
            this.connectedTo = null;
        }
    }

    /**
     * Returns the last X seconds
     * @param seconds - Amount of seconds
     * @return - Raw data
     */
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

        ArrayList<Byte> finalResult = new ArrayList<>();
        int index = 0;

        for(int x = result.size() - 1;x >= 0 ;x--){
            for(int y = 0; y < result.get(x).length;y++){
                finalResult.add(result.get(x)[y]);
            }
        }
        return finalResult;
    }

    public void offerVoiceData(byte[] data){
        voiceBuffer.offer(data);
    }

    public void userJoined(GuildVoiceJoinEvent event){
        VoiceChannel channel = event.getChannelJoined();
        if(this.connectedTo.equals(channel)){
            System.out.println(this.usersConnected);
        }
    }

    public void userLeft(GuildVoiceLeaveEvent event){
        VoiceChannel channel = event.getChannelLeft();
        if(this.connectedTo.equals(channel)){
            this.usersConnected = this.connectedTo.getMembers().size();
            if(this.usersConnected - 1 == 0){
                //Todo - start a timer to leave the channel automatically
            }
        }
    }

    public void userMoved(GuildVoiceMoveEvent event){
        VoiceChannel left = event.getChannelLeft();
        VoiceChannel joined = event.getChannelJoined();

        if(this.connectedTo.equals(left) || this.connectedTo.equals(joined)){
            this.usersConnected = this.connectedTo.getMembers().size();
            if(this.usersConnected - 1 == 0){
                //Do something
            }
        }
    }
}
