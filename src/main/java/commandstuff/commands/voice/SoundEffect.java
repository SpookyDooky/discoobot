package commandstuff.commands.voice;

import commandstuff.CommandContext;
import commandstuff.command_interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class SoundEffect implements ICommand {

    //Make it so that you can use !![soundname] instead of !!sound [soundname]

    private String name;

    public SoundEffect(String name){
        this.name = name;
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] parameters, CommandContext context) {

    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String category() {
        return null;
    }

    @Override
    public String getCommandName() {
        return this.name;
    }
}
