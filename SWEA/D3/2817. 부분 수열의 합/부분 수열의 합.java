// 모든 경우의 수 찾기 -> 완전탐색
// dfs로 선택/미선택
// 합산 K보다 크면 종료, K와 같으면 카운트

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static int cnt;
    static int N;
    static int K;
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
            K = sc.nextInt();
            int[] arr = new int[N];
            for (int i=0; i<N; i++) 
                arr[i] = sc.nextInt();
            cnt = 0;
            dfs(arr, 0, 0);
            System.out.println("#" + test_case + " " + cnt);
		}
	}
    
    static void dfs(int[] arr, int idx, int sum) {
        if (sum > K) return;
        
        if (sum == K) {
            cnt++;
            return;
        }
        
        if (idx >= N) return; // 다음 순서에 합산(sum)을 확인하므로, 합산 검사후 인덱스 검사
            
        dfs(arr, idx+1, sum + arr[idx]); // 선택
        dfs(arr, idx+1, sum); // 미선택 
    }
}