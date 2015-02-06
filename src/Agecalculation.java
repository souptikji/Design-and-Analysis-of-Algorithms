import java.util.Calendar;
import java.util.Date;
import java.util.SortedSet;


public class Agecalculation {
	
	public DurationDB durationdb;
	
	public String age(Date dob)
	{
		Calendar cal = Calendar.getInstance();
		long age_in_millis= (cal.getTimeInMillis());
		SortedSet<Duration> set_of_durations = durationdb.set_of_durations;
		
		long finalquotient=0;
		Duration last_duration=null;
		
		for(Duration d:set_of_durations)
		{
			long quotient = age_in_millis/d.duration_in_millis;
			
			if(quotient>0)
			{
				finalquotient=quotient;
				last_duration=d;
			}
			else if(quotient==0)
			{
				break;
			}
		}
		
		//quotient has the value which came just before zero, last_duration is the duration before it just broke
		String ans= finalquotient+" "+last_duration.name;
		return ans;
	}
	
}
