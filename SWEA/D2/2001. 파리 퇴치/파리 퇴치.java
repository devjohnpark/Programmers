import java.io.*;
import java.util.*;

public class Solution {
	
	
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) { 
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][] map = new int[n][n];
			
			for (int i=0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j< n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
				
			}
			
			int max = 0;
			
			
			for (int i=0; i <= n - m; i++) { // n - m
				for (int j=0; j<= n - m; j++) {
					int sum = 0; 
					for (int x=i; x < i + m; x++) { // i ~ i + m - 1 = m  
						for (int y=j; y < j + m; y++) {
							sum += map[x][y]; 
						}
					}
					max = Math.max(sum, max);
				}
			}
			
			System.out.println("#" + tc + " " + max);
		}
		
	
	}

}
