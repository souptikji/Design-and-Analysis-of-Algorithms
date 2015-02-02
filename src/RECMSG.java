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


public class RECMSG
{
    public static void main(String[] args)
    {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        long ans = 0;
        int test = in.readInt();

        while (test-- > 0)
        {
            String s = in.readString();
            //System.out.println("s="+s);
            ans = 0;

            for (int i = 0; i < s.length(); ++i)
            {
                int val = (s.charAt(i) - 'a');
                //System.out.println("char="+s.charAt(i)+"   val="+val);
                ans += (s.charAt(i) - 'a' + 1);
            }

            System.out.println(ans);
        }
    }
}
