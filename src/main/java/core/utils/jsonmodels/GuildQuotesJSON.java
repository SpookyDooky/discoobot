package core.utils.jsonmodels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public void addQuote(String quote, String userName){
        QuoteJSON theQuote = new QuoteJSON(amount++,quote,0,0);
        this.quotes.add(theQuote);
        rewriteFile();
    }

    public QuoteJSON getRandomQuote(){
        int index = (int)(Math.random() * amount);
        return quotes.get(index);
    }

    public void deleteQuote(int index){
        quotes.remove(index);
        amount--;
        rewriteFile();
    }

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
