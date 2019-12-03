package commands;

public class CommandDetails {

    private int minParameters;
    private int maxParameters;

    private boolean commaNeedsRemoval;
    private boolean hasParameters;
    private boolean whiteList;

    public CommandDetails(int minParameters, int maxParameters, boolean commaNeedsRemoval, boolean hasParameters, boolean whiteList){
        this.minParameters = minParameters;
        this.maxParameters= maxParameters;
        this.commaNeedsRemoval = commaNeedsRemoval;
        this.hasParameters = hasParameters;
        this.whiteList = whiteList;
    }

    public int getMinParameters(){
        return this.minParameters;
    }

    public int getMaxParameters(){
        return this.maxParameters;
    }

    public boolean commaNeedsRemoval(){
        return this.commaNeedsRemoval;
    }

    public boolean hasParameters(){
        return this.hasParameters;
    }
}
