
public class Duration implements Comparable<Duration> 
{
	public long duration_in_millis=0;
	public String name="";
	
	public Duration(int duration_in_millis,String name)
	{
		this.duration_in_millis=duration_in_millis;
		this.name=name;
	}
	
	public int compareTo(Duration that)
	{
		if(this.duration_in_millis>that.duration_in_millis)
		{
			return 1;
		}
		else if(this.duration_in_millis<that.duration_in_millis)
		{
			return -1;
		}
		else return 0;
	}

}
