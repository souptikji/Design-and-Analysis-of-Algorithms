/*************************************************************************
 *  Run depth first search on an undirected graph.
 *  Runs in O(E + V) time.
 *
 *************************************************************************/

/**
 *  The <tt>DepthFirstSearch</tt> class represents a data type for
 *  determining the vertices connected to a given source vertex <em>s</em>
 *  in an undirected graph. For versions that find the paths, see
 *  {@link DepthFirstPaths} and {@link BreadthFirstPaths}.
 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>
 *  (in the worst case),
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  It uses extra space (not including the graph) proportional to <em>V</em>.
 *  <p>
 */
public class DepthFirstSearch
{
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int global_time = 0; // number of vertices connected to s
    private int[] pretime;
    private int[] posttime;

    /**
     * Computes the vertices in graph <tt>G</tt> that are
     * connected to the source vertex <tt>s</tt>.
     * @param G the graph
     * @param s the source vertex
     */
    public DepthFirstSearch(Graph G, int s)
    {
        marked = new boolean[G.V()];
        pretime = new int[G.V()];
        posttime = new int[G.V()];
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v)
    {
        //Concept of time : increment time only when you are done with processing the node, the time has passed.
        marked[v] = true;
        pretime[v] = global_time;
        ++global_time; //incrementing the time because we are done with processing the node, now to explore its children

        for (int w : G.adj(v))
        {
            if (!marked[w])
            {
                dfs(G, w);
            }
        }

        //Everything has been marked- write posttime and increment time again
        posttime[v] = global_time;
        ++global_time; //increasing time since we are now leaving the node, otherwise the postcount of this vertex will be the precount of the next vertex
    }

    /**
     * Is there a path between the source vertex <tt>s</tt> and vertex <tt>v</tt>?
     * @param v the vertex
     * @return <tt>true</tt> if there is a path, <tt>false</tt> otherwise
     */
    public boolean marked(int v)
    {
        return marked[v];
    }

    /**
     * Returns the number of vertices connected to the source vertex <tt>s</tt>.
     * @return the number of vertices connected to the source vertex <tt>s</tt>
     */
    public int time()
    {
        return global_time;
    }

    /**
     * Unit tests the <tt>DepthFirstSearch</tt> data type.
     */
    public static void main(String[] args)
    {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);

        for (int v = 0; v < G.V(); v++)
        {
            if (search.marked(v))
            {
                System.out.print(v + " ");
            }
        }
    }
}
