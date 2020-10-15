import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class LittleMonkAndVirat {
	public static void main(String[] args) throws IOException {
		InputReader scan = new InputReader(System.in);
		BufferedWriter br = new BufferedWriter(new OutputStreamWriter(System.out));
		ArrayList<Long> al;
		
		int N = scan.nextInt();
		Long[] arr = new Long[N];
		for(int n=0; n<N; n++) {
			arr[n]=(scan.nextLong());
		}
		for(int n=0; n<N; n++) {
			arr[n]+=(scan.nextLong());
		}
		for(int n=0; n<N; n++) {
			arr[n]+=(scan.nextLong());
		}
		Arrays.sort(arr);
		al = new ArrayList<>(Arrays.asList(arr));
		StringBuilder sb = new StringBuilder();
		int Q = scan.nextInt();
		for(int q=0; q<Q; q++) {
			int k = scan.nextInt();
			if(k > al.size()) {
				sb.append(-1 + "\n");
			} else {
				sb.append(al.remove(k-1) + "\n");
			}
		}
		System.out.println(sb);
	}



	static class InputReader
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
            if (numChars==-1)
                throw new InputMismatchException();
 
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
 
                if(numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }
 
        public String nextLine()
        {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
        public int nextInt()
        {
            int c = read();
 
            while(isSpaceChar(c))
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
                if(c<'0'||c>'9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
 
            return res * sgn;
        }
 
        public long nextLong()
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
            long res = 0;
 
            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public double nextDouble()
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
            double res = 0;
            while (!isSpaceChar(c) && c != '.')
            {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.')
            {
                c = read();
                double m = 1;
                while (!isSpaceChar(c))
                {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, nextInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
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
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
}

