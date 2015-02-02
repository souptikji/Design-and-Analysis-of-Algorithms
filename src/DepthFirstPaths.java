import java.util.Stack;


/*************************************************************************
 *  Run depth first search on an undirected graph.
 *  Runs in O(E + V) time.
 *************************************************************************/

/**
 *  The <tt>DepthFirstPaths</tt> class represents a data type for finding
 *  paths from a source vertex <em>s</em> to every other vertex
 *  in an undirected graph.
 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>,
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  It uses extra space (not including the graph) proportional to <em>V</em>.
 *  <p>
 */
public class DepthFirstPaths
{
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int[] edgeTo; // edgeTo[v] = last edge on s-v path
    private final int s; // source vertex

    /**
     * Computes a path between <tt>s</tt> and every other vertex in graph <tt>G</tt>.
     * @param G the graph
     * @param s the source vertex
     */
    public DepthFirstPaths(Graph G, int s)
    {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v)
    {
        marked[v] = true;

        for (int w : G.adj(v))
        {
            if (!marked[w])
            {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a path between the source vertex <tt>s</tt> and vertex <tt>v</tt>?
     * @param v the vertex
     * @return <tt>true</tt> if there is a path, <tt>false</tt> otherwise
     */
    public boolean hasPathTo(int v)
    {
        return marked[v];
    }

    /**
     * Returns a path between the source vertex <tt>s</tt> and vertex <tt>v</tt>, or
     * <tt>null</tt> if no such path.
     * @param v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *   <tt>s</tt> and vertex <tt>v</tt>, as an Iterable
     */
    public Iterable<Integer> pathTo(int v)
    {
        if (!hasPathTo(v))
        {
            return null;
        }

        Stack<Integer> path = new Stack<Integer>();

        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);

        path.push(s);

        return path;
    }

    /**
     * Unit tests the <tt>DepthFirstPaths</tt> data type.
     */
    public static void main(String[] args)
    {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths dfs = new DepthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++)
        {
            if (dfs.hasPathTo(v))
            {
                System.out.printf("%d to %d:  ", s, v);

                for (int x : dfs.pathTo(v))
                {
                    if (x == s)
                    {
                        System.out.print(x);
                    }
                    else
                    {
                        System.out.print("-" + x);
                    }
                }

                System.out.println();
            }

            else
            {
                System.out.printf("%d to %d:  not connected\n", s, v);
            }
        }
    }
}
