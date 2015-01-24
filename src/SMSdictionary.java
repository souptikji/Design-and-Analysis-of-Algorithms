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

class InputReader {

		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1)
				throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public String readString() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}

class OutputWriter {
		private final PrintWriter writer;

		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
		}

		public OutputWriter(Writer writer) {
			this.writer = new PrintWriter(writer);
		}

		public void print(Object...objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0)
					writer.print(' ');
				writer.print(objects[i]);
			}
		}

		public void printLine(Object...objects) {
			print(objects);
			writer.println();
		}

		public void close() {
			writer.close();
		}

		public void flush() {
			writer.flush();
		}

		}

class IOUtils {

		public static int[] readIntArray(InputReader in, int size) {
			int[] array = new int[size];
			for (int i = 0; i < size; i++)
				array[i] = in.readInt();
			return array;
		}

		}
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
