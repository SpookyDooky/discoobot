package core.utils.jsonmodels;

public class QuoteJSON {

    private int id;
    private String quote;
    private int upvotes;
    private int downvotes;

    public QuoteJSON(int id, String quote, int upvotes, int downvotes){
        this.id = id;
        this.quote = quote;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public int getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }
}
