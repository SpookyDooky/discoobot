package core.utils;

import ws.schild.jave.*;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Track {

    private byte[] rawTrack;
    private String name;

    public Track(byte[] rawTrack, String name){
        this.rawTrack = rawTrack;
        this.name = name;
    }

    public Track trimData(int start, int end){
        if(start < end && start >= 0) {
            int bytesSecond = 192000;
            int startIndex = start * bytesSecond;
            int endIndex = end * bytesSecond;

            byte[] trimmedData = new byte[endIndex - startIndex];
            for(int x = startIndex; x < endIndex;x++){
                trimmedData[x - startIndex] = this.rawTrack[x];
            }

            return new Track(trimmedData,getRandomName(8));
        }
        return null;
    }

    public File getWavFile(){
        File outputFileWave = new File("src/main/resources/trimmed.wav");
        AudioFormat format = new AudioFormat(48000,16,2,true,true);
        try {
            AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(this.rawTrack), format,this.rawTrack.length), AudioFileFormat.Type.WAVE,outputFileWave);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFileWave;
    }

    public File getMp3File(File wavFile){
        String fileName = getRandomName(8);
        File target = new File("src/main/resources/" + fileName + ".mp3");

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(128000);
        audio.setChannels(2);
        audio.setSamplingRate(44100);

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);

        FFMPEGLocator loc = new BotLocator();
        Encoder encoder = new Encoder(loc);

        MultimediaObject pls = new MultimediaObject(wavFile);
        try {
            encoder.encode(pls,target,attrs);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        return target;
    }

    private static String getRandomName(int lengthName){
        StringBuilder result = new StringBuilder(lengthName);
        String options = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        char[] optionChars = options.toCharArray();
        for(int x = 0; x < lengthName;x++){
            int random = (int)(Math.random() * options.length());
            result.append(optionChars[random]);
        }
        return result.toString();
    }
}
