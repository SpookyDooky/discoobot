package core.utils.jsonmodels;

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
}
