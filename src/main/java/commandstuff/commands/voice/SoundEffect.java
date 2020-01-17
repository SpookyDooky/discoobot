package commandstuff.commands.voice;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import core.Bot;
import core.BotManager;
import core.managers.VoiceManager;
import core.utils.GuildInfo;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class SoundEffect implements ICommand {

    //Make it so that you can use !![soundname] instead of !!sound [soundname]

    private String name;

    public SoundEffect(String name){
        this.name = name;
    }

    //TODO - Make it so that you can only play sounds while you are in a voice channel
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {
        String guildId = event.getGuild().getId();
        GuildInfo info = BotManager.getInstance().getGuildInfo(guildId);

        VoiceChannel userChannel = event.getMember().getVoiceState().getChannel();

        if(userChannel == null){
            TextChannel channel = event.getChannel();
            channel.sendMessage("Could not play the sound, you are not connected to a voicechannel").queue();
        } else {
            VoiceManager manager = info.getVoiceManager();
            manager.connectTo(userChannel,event.getGuild().getAudioManager());
            convert(event);
        }
    }

    //To change it from WAV to PCM, Big Endian notation
    private void convert(GuildMessageReceivedEvent event){
        File target = new File("src/main/resources/sounds/wav/" + this.name + ".wav");
        AudioFormat OUTPUT_FORMAT = new AudioFormat(48000.0f, 16, 2, true, true);

        try {
            //3840 Bytes per 20ms
            AudioInputStream stream = AudioSystem.getAudioInputStream(target);
            AudioInputStream result = AudioSystem.getAudioInputStream(OUTPUT_FORMAT, stream);
            byte[] rawResult = result.readAllBytes();
            byte[] fragment = new byte[3840];
            int index = 0;
            while(index  < rawResult.length){

                int offset = 0;
                while(offset < 3840 && index < rawResult.length){
                    fragment[offset] = rawResult[index];
                    offset++;
                    index++;
                }

                BotManager.getInstance().getGuildInfo(event.getGuild().getId()).getVoiceManager().offerVoiceData(fragment.clone()); //Putting it in the buffer
                fragment = new byte[3840];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String help() {
        return "Stay yeetin my friends!";
    }

    @Override
    public String category() {
        return "sounds";
    }

    @Override
    public String getCommandName() {
        return this.name;
    }
}
