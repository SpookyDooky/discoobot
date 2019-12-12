package core.utils.jsonmodels;

import java.util.List;

public class SoundJSON {

    private int amount;
    private List<String> soundNames;

    public SoundJSON(int amount, List<String> soundNames){
        this.amount = amount;
        this.soundNames = soundNames;
    }

    public int getAmount() {
        return amount;
    }

    public List<String> getSoundNames() {
        return soundNames;
    }
}
