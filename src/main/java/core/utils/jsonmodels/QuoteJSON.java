package core.utils.jsonmodels;

public class QuoteJSON {

    private int id;
    private String quote;
    private String by;
    private int upvotes;
    private int downvotes;

    public QuoteJSON(int id, String quote, String by, int upvotes, int downvotes){
        this.id = id;
        this.quote = quote;
        this.by = by;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public int getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }

    public String getBy() {
        return by;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }
}
