import java.util.SortedSet;
import java.util.TreeSet;


public class DurationDB{
	
	//durations sorted in order of millis- eg {sec,min,hour,day,week}
	public SortedSet<Duration> set_of_durations;
	
	public DurationDB()
	{
		set_of_durations= new TreeSet<Duration>();
	}
	
	public void addDuration(Duration d)
	{
		long duration_in_millis= d.duration_in_millis;
		set_of_durations.add(d);
	}

}
