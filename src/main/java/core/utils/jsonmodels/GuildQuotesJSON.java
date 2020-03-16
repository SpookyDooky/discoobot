package core.utils.jsonmodels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for holding all the quotes that belong to a specific guild
 */
public class GuildQuotesJSON {

    private int amount;
    private List<QuoteJSON> quotes;
    private static String guildIdStored;

    public GuildQuotesJSON(int amount, List<QuoteJSON> quotes){
        this.amount = amount;
        this.quotes = quotes;
    }

    public int getAmount(){
        return this.amount;
    }

    public List<QuoteJSON> getQuotes(){
        return this.quotes;
    }

    /**
     * Adds a quote to the quote list of a guild
     * @param quote - The quote
     * @param userName - Username who made the quote (needs to be deleted)
     */
    public void addQuote(String quote, String userName){
        QuoteJSON theQuote = new QuoteJSON(amount++,quote,0,0);
        this.quotes.add(theQuote);
        rewriteFile();
    }

    /**
     * Returns a random quote of a specific guild
     * @return - The quote
     */
    public QuoteJSON getRandomQuote(){
        int index = (int)(Math.random() * amount);
        return quotes.get(index);
    }

    /**
     * Deletes a quote for a specific guild
     * @param index - Index(ID) of the quote
     */
    public void deleteQuote(int index){
        quotes.remove(index);
        amount--;
        rewriteFile();
    }

    public boolean upvoteQuote(int index){
        try{
            this.getQuotes().get(index).upvote();
        } catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        rewriteFile();
        return true;
    }

    /**
     * Method for loading the quotes from a json file
     * @param guildId - ID of the guild
     * @return - Returns an object containing all quotes of a specific guild
     */
    public static GuildQuotesJSON initGuildQuotes(String guildId){
        guildIdStored = guildId;
        File guildQuotes = new File("src/main/resources/quotes/" + guildId + ".json");

        try{
            BufferedReader reader = new BufferedReader(new FileReader(guildQuotes));
            Gson gson = new Gson();
            GuildQuotesJSON quotes = gson.fromJson(reader,GuildQuotesJSON.class);

            return quotes;
        } catch(FileNotFoundException e){
            createQuotesFile(guildId);
            return initGuildQuotes(guildId);
        }
    }

    /**
     * Method to create the json file if it does not exist
     * @param guildId - ID of the guild to create the file for
     */
    private static void createQuotesFile(String guildId){
        Gson gson = new Gson();

        List<QuoteJSON> list = new ArrayList<>();
        GuildQuotesJSON json = new GuildQuotesJSON(0,list);

        String jsonString = gson.toJson(json);
        try{
            FileWriter writer = new FileWriter("src/main/resources/quotes/" + guildId + ".json");
            writer.write(jsonString);
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * For adding new quotes to the file
     */
    private void rewriteFile(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(this);

        try {
            FileWriter writer = new FileWriter("src/main/resources/quotes/" + guildIdStored + ".json");
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
