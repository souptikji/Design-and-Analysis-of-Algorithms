import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

 public class SMSdictionary {
	
	public static HashMap<Character,Integer> index;
	
	public static void initialize()
	{
		index=new HashMap<Character,Integer>();
		index.put(new Character('a'),2);
		index.put(new Character('b'),2);
		index.put(new Character('c'),2);
		index.put(new Character('d'),3);
		index.put(new Character('e'),3);
		index.put(new Character('f'),3);
		index.put(new Character('g'),4);
		index.put(new Character('h'),4);
		index.put(new Character('i'),4);
		index.put(new Character('j'),5);
		index.put(new Character('k'),5);
		index.put(new Character('l'),5);
		index.put(new Character('m'),6);
		index.put(new Character('n'),6);
		index.put(new Character('o'),6);
		index.put(new Character('p'),7);
		index.put(new Character('q'),7);
		index.put(new Character('r'),7);
		index.put(new Character('s'),7);
		index.put(new Character('t'),8);
		index.put(new Character('u'),8);
		index.put(new Character('v'),8);
		index.put(new Character('w'),9);
		index.put(new Character('x'),9);
		index.put(new Character('y'),9);
		index.put(new Character('z'),9);
	}
	public static int reverseIndex(String s)
	{
		int ans=0;
		for(int i=0;i<s.length();++i)
		{
			ans*=10;
			ans+=index.get(new Character(s.charAt(i))).intValue();
		}
		return ans;
	}
	
	public static void main(String[] args)
	{
		InputReader in 		= new InputReader(System.in);
        OutputWriter out	=	new OutputWriter(System.out);
        initialize();
        
        //Read the number of words
        int numWords = in.readInt();
        List<Integer> list= new ArrayList<Integer>();
        //Read all words
        for(int i=0;i<numWords;++i)
        {
        	String s= in.readString();
        	list.add(reverseIndex(s));
        }
        Collections.sort(list);
        
        //find max repeating value
		int max=-1,maxcount=0;
		for(int i=0;i<numWords;)
		{
			int count=0;
			
			for(int j=i;j<numWords && list.get(j).equals(list.get(i));++count,++j);
			if(count>maxcount)
			{
				max=i;
				maxcount=count;
			}
			i+=count;
		}
		System.out.println(list.get(max));
	}

}
