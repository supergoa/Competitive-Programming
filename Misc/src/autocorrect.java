import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class autocorrect {
    public static void main(String[] args) throws Exception{
        JS in = new JS();
        PrintWriter out = new PrintWriter(System.out);
        new autocorrect().solve(in,out);
        out.close();
    }

    class Trie {
        int id;
        Trie[] t = new Trie[26];
    }
    
    String[] hm;
    int curID=0;
    private void solve(JS in, PrintWriter out) throws Exception {
        int N = in.nextInt();
        int M = in.nextInt();
        hm = new String[1000001];
        
        Trie root = new Trie();
        Trie curT = root;
        for(int n=0; n<N; n++) {
            String word = in.next();
            curID++;
            curT=root;
            for(int i=0; i<word.length(); i++) {
                if(curT.t[word.charAt(i)-'a'] == null) {
                	curT.t[word.charAt(i)-'a'] = new Trie();
                	hm[curID] = word;
                    curT.t[word.charAt(i)-'a'].id = curID;
                }
                curT = curT.t[word.charAt(i)-'a'];
            }
        }
        curT=root;
        for(int m=0; m<M; m++) {
            String querey = in.next();
            curT=root;
            int ans = 1+solveRec(curT.t[querey.charAt(0)-'a'], querey, 0);
            out.println(ans);
        }
    }

    private int solveRec(Trie trie, String querey, int i) {
        if(i>=querey.length()) return 0;
        if(i==querey.length()-1) return 0;
        if(trie == null) return querey.length()-i-1;

        String tab = hm[trie.id];
        if(tab.isEmpty()) return 1 + solveRec(trie.t[querey.charAt(i+1)-'a'], querey, i+1);
        
        int lastInd = i-1;
        for(int j=i; j<Math.min(tab.length(), querey.length()); j++) {
            if(querey.charAt(j) == tab.charAt(j)) lastInd = j;
            else break;
        }
        if(lastInd<=i) return 1 + solveRec(trie.t[querey.charAt(i+1)-'a'], querey, i+1);
        int costTab = tab.length() - lastInd;
        int costNorm = lastInd - i;
        
        for(int j=i+1; j<= lastInd; j++) {
            trie = trie.t[tab.charAt(j)-'a'];
        }
        return Math.min(costTab, costNorm) + solveRec(trie, querey, lastInd);
    }
    
    static class JS {
        public int BS = 1<<16;
        public char NC = (char)0;
        byte[] buf = new byte[BS];
        int bId = 0, size = 0;
        char c = NC;
        double num = 1;
        BufferedInputStream in;

        public JS() {
            in = new BufferedInputStream(System.in, BS);
        }

        public JS(String s) throws FileNotFoundException {
            in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
        }

        public char nextChar(){
            while(bId==size) {
                try {
                    size = in.read(buf);
                }catch(Exception e) {
                    return NC;
                }
                if(size==-1)return NC;
                bId=0;
            }
            return (char)buf[bId++];
        }

        public int nextInt() {
            return (int)nextLong();
        }

        public long nextLong() {
            num=1;
            boolean neg = false;
            if(c==NC)c=nextChar();
            for(;(c<'0' || c>'9'); c = nextChar()) {
                if(c=='-')neg=true;
            }
            long res = 0;
            for(; c>='0' && c <='9'; c=nextChar()) {
                res = (res<<3)+(res<<1)+c-'0';
                num*=10;
            }
            return neg?-res:res;
        }

        public double nextDouble() {
            double cur = nextLong();
            return c!='.' ? cur:cur+nextLong()/num;
        }

        public String next() {
            StringBuilder res = new StringBuilder();
            while(c<=32)c=nextChar();
            while(c>32) {
                res.append(c);
                c=nextChar();
            }
            return res.toString();
        }

        public String nextLine() {
            StringBuilder res = new StringBuilder();
            while(c<=32)c=nextChar();
            while(c!='\n') {
                res.append(c);
                c=nextChar();
            }
            return res.toString();
        }

        public boolean hasNext() {
            if(c>32)return true;
            while(true) {
                c=nextChar();
                if(c==NC)return false;
                else if(c>32)return true;
            }
        }
    }
}