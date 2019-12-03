package commandstuff;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandContext {

    private User user;
    private String message;
    private TextChannel channel;

    public CommandContext(GuildMessageReceivedEvent event){
        this.user = event.getAuthor();
        this.message = event.getMessage().getContentRaw();
        this.channel = event.getChannel();
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public TextChannel getChannel() {
        return channel;
    }
}
