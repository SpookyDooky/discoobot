package core.utils;

import ws.schild.jave.FFMPEGLocator;

import java.io.File;
import java.io.IOException;

public class BotLocator extends FFMPEGLocator {
    @Override
    public String getFFMPEGExecutablePath() {
        File path = new File("src\\main\\resources\\utils\\ffmpeg-amd64-2.7.1.exe");
        String result = "";
        try {
            result = path.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
