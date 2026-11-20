import java.io.*;
import java.util.*;

// 완전탐색: 선택/미선택 -> O(2^N) = 2^20 < 10^8
// 탈출 
// 재료 N 초과시 
// 칼로리 L 초과시
// 재료 합의 최대값 갱신

public class Solution {
	
	static int max;
	static int N;
	static int L;
	static int[] T;
	static int[] C;
	
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=TC; tc++) { 
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			T = new int[N];
			C = new int[N];
			
			
			for (int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				T[i] = Integer.parseInt(st.nextToken());
				C[i] = Integer.parseInt(st.nextToken());
				
			}
			
			max = 0;
			
			dfs(0, 0, 0);
			
			System.out.println("#" + tc + " " + max);
		}
		
	
	}
	
	static void dfs(int idx, int taste, int calorie) {
		// 칼로리 초과
		if (calorie > L) {
		    return;
		}

		// 모든 재료 탐색 완료
		if (idx == N) {
		    max = Math.max(taste, max);
		    return;
		}
		
		dfs(idx + 1, taste + T[idx], calorie + C[idx]);
		dfs(idx + 1, taste, calorie);
	}

}
