import java.io.*;
import java.util.*;
public class Main{
    static int N,M,arr[][];
    static boolean visit[];
    static int result = Integer.MAX_VALUE;
    static List<int[]> home = new ArrayList<>();
    static List<int[]> chicken = new ArrayList<>();
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==1){
                    home.add(new int[]{i,j});
                }
                else if(arr[i][j]==2){
                    chicken.add(new int[]{i,j});
                }
            }
        }
        
        visit = new boolean[chicken.size()];
        back(0,0);
        System.out.println(result);
    }
    
    static void back(int start, int count){
        if(count==M){
            int sum = 0;
            for(int[]h: home){
                int min = Integer.MAX_VALUE;
                for(int c =0;c<chicken.size();c++){
                    if(visit[c]){
                        int dist = Math.abs(h[0]-chicken.get(c)[0])+Math.abs(h[1]-chicken.get(c)[1]);
                        min = Math.min(min,dist);
                    }
                }
                sum+=min;
            }
            result = Math.min(result,sum);
            return;
        }
        
        
        for(int i=start;i<chicken.size();i++){
            visit[i]=true;
            back(i+1, count+1);
            visit[i]=false;
            
        }
    }
}