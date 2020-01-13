package core.utils.jsonmodels;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GuildQuotesJSON {

    private int amount;
    private List<QuoteJSON> quotes;

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

    public String getRandomQuote(){
        int index = (int)(Math.random() * amount);
        return quotes.get(index).getQuote();
    }

    public static GuildQuotesJSON initGuildQuotes(String guildId){
        File guildQuotes = new File("src/main/resources/quotes/" + guildId + ".json");

        try{
            BufferedReader reader = new BufferedReader(new FileReader(guildQuotes));
            Gson gson = new Gson();
            GuildQuotesJSON quotes = gson.fromJson(reader,GuildQuotesJSON.class);

            return quotes;
        } catch(FileNotFoundException e){
            //Todo - Make sure it takes care of creating the file if needed
            createQuotesFile(guildId);
            return initGuildQuotes(guildId);
        }
    }

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
}
