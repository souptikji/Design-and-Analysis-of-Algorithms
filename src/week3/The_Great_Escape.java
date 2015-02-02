package week3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;



public class The_Great_Escape
{
	public static void main(String[] args)
	{
		int V,E,start,end,hero_location,wife_location;
		InputReader in= new InputReader(System.in);
		V=in.readInt(); 
		E=in.readInt();
		Graph G=new Graph(V);
		
		for(int edge=0;edge<E;++edge)
		{
			start=in.readInt()-1;
			end=in.readInt()-1;
			G.addEdge(start, end);
		}
		hero_location=in.readInt()-1;
		wife_location=in.readInt()-1;
		
		//System.out.println(G);
		//System.out.println("Hero->"+hero_location+" & wife->"+wife_location);
		
		BFSPath bfspath= new BFSPath(G,hero_location,wife_location);
		System.out.println(bfspath.distTo[wife_location]);
	}
}

class Graph
{
	@SuppressWarnings("unchecked")
	private final int V;
    private int E;
    private List[] adj;

    public Graph(int V)
    {
        if (V < 0)
        {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }

        this.V = V;
        this.E = 0;
        adj = new ArrayList[V];

        for (int v = 0; v < V; v++)
        {
            adj[v] = new ArrayList();
        }
    }

    public Graph(InputReader in)
    {
        this(in.readInt());

        int E = in.readInt();

        if (E < 0)
        {
            throw new IllegalArgumentException(
                "Number of edges must be nonnegative");
        }

        for (int i = 0; i < E; i++)
        {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    
    /**
     * Returns the number of vertices in the graph.
     * @return the number of vertices in the graph
     */
    public int V()
    {
        return V;
    }

    /**
     * Returns the number of edges in the graph.
     * @return the number of edges in the graph
     */
    public int E()
    {
        return E;
    }

    // throw an IndexOutOfBoundsException unless 0 <= v < V
    private void validateVertex(int v)
    {
        if ((v < 0) || (v >= V))
        {
            throw new IndexOutOfBoundsException("vertex " + v +
                " is not between 0 and " + (V - 1));
        }
    }

    /**
     * Adds the undirected edge v-w to the graph.
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
     */
    public void addEdge(int v, int w)
    {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * Returns the vertices adjacent to vertex <tt>v</tt>.
     * @return the vertices adjacent to vertex <tt>v</tt> as an Iterable
     * @param v the vertex
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
     */
    public List<Integer> adj(int v)
    {
        validateVertex(v);

        return adj[v];
    }

    /**
     * Returns the degree of vertex <tt>v</tt>.
     * @return the degree of vertex <tt>v</tt>
     * @param v the vertex
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
     */
    public int degree(int v)
    {
        validateVertex(v);

        return adj[v].size();
    }

    /**
     * Returns a string representation of the graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *    followed by the <em>V</em> adjacency lists
     */
//    public String toString()
//    {
//        StringBuilder s = new StringBuilder();
//        String NEWLINE = System.getProperty("line.separator");
//        s.append(V + " vertices, " + E + " edges " + NEWLINE);
//
//        for (int v = 0; v < V; v++)
//        {
//            s.append(v + ": ");
//
//            for (int w :adj[v])
//            {
//                s.append(w + " ");
//            }
//
//            s.append(NEWLINE);
//        }
//
//        return s.toString();
//    }

    /**
     * Unit tests the <tt>Graph</tt> data type.
     */
    /*public static void main(String[] args)
    {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        Graph G = new Graph(in);
        System.out.println(G);
    }*/
}

class BFSPath
{
	@SuppressWarnings("unchecked")
	Graph G;
	boolean[] visited;
	int[] distTo;
	
	public BFSPath(Graph G,int source,int dest)
	{
		this.G=G;
		visited=new boolean[G.V()];
		distTo=new int[G.V()];
		Queue q= new LinkedList();
		
		//insert source into queue
		q.add(source);
		visited[source]=true;
		distTo[source]=0;
		
		//BFS traversal
		while(q.isEmpty()!=true)
		{
			int v=(int)(q.remove());
			if(v==dest)
			{
				break;
			}
			for(int w:G.adj(v))
			{
				if(!visited[w])
				{
					q.add(w);
					visited[w]=true;
					distTo[w]=distTo[v]+1;
				}
			}
		}
	}
}

class InputReader
{
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream)
    {
        this.stream = stream;
    }

    public int read()
    {
        if (numChars == -1)
        {
            throw new InputMismatchException();
        }

        if (curChar >= numChars)
        {
            curChar = 0;

            try
            {
                numChars = stream.read(buf);
            }
            catch (IOException e)
            {
                throw new InputMismatchException();
            }

            if (numChars <= 0)
            {
                return -1;
            }
        }

        return buf[curChar++];
    }

    public int readInt()
    {
        int c = read();

        while (isSpaceChar(c))
            c = read();

        int sgn = 1;

        if (c == '-')
        {
            sgn = -1;
            c = read();
        }

        int res = 0;

        do
        {
            if ((c < '0') || (c > '9'))
            {
                throw new InputMismatchException();
            }

            res *= 10;
            res += (c - '0');
            c = read();
        }
        while (!isSpaceChar(c));

        return res * sgn;
    }

    public String readString()
    {
        int c = read();

        while (isSpaceChar(c))
            c = read();

        StringBuilder res = new StringBuilder();

        do
        {
            res.appendCodePoint(c);
            c = read();
        }
        while (!isSpaceChar(c));

        return res.toString();
    }

    public boolean isSpaceChar(int c)
    {
        if (filter != null)
        {
            return filter.isSpaceChar(c);
        }

        return (c == ' ') || (c == '\n') || (c == '\r') || (c == '\t') ||
        (c == -1);
    }

    public String next()
    {
        return readString();
    }

    public interface SpaceCharFilter
    {
        public boolean isSpaceChar(int ch);
    }
}

class IOUtils
{
    public static int[] readIntArray(InputReader in, int size)
    {
        int[] array = new int[size];

        for (int i = 0; i < size; i++)
            array[i] = in.readInt();

        return array;
    }
}


class LinkedQueue{
    private int N;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private int item;
        private Node next;
    }

    /**
     * Initializes an empty queue.
     */
    public LinkedQueue() {
        first = null;
        last  = null;
        N = 0;
        assert check();
    }

    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     * @return the number of items in this queue
     */
    public int size() {
        return N;     
    }

    /**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public int poll() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    /**
     * Adds the item to this queue.
     * @param item the item to add
     */
    public void add(int item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
        assert check();
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public int remove() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        assert check();
        return item;
    }

    
    // check internal invariants
    private boolean check() {
        if (N == 0) {
            if (first != null) return false;
            if (last  != null) return false;
        }
        else if (N == 1) {
            if (first == null || last == null) return false;
            if (first != last)                 return false;
            if (first.next != null)            return false;
        }
        else {
            if (first == last)      return false;
            if (first.next == null) return false;
            if (last.next  != null) return false;

            // check internal consistency of instance variable N
            int numberOfNodes = 0;
            for (Node x = first; x != null; x = x.next) {
               numberOfNodes++;
            }
            if (numberOfNodes != N) return false;

            // check internal consistency of instance variable last
            Node lastNode = first;
            while (lastNode.next != null) {
               lastNode = lastNode.next;
            }
            if (last != lastNode) return false;
        }

        return true;
    } 
 
}
