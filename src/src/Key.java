package src;

public class Key  
{
    public Key(String t, long s, int d) 
    {
        tone = t;
        start = s;
        duration = d;
    }
    public String getTone()
    {
        return tone;
    }    
    public long getStart()
    {
        return start;
    }    
    public int getDuration()
    {
        return duration;
    }
    public void setDuration(int d) {
    	duration = d;
    }
    private String tone;
    private long start;
    private int duration;  
}