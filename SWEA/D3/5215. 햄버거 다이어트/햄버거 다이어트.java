import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    private static int maxFavor;

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int L = sc.nextInt();
            int[][] arr = new int[N][2];
            maxFavor = 0;
            
            for (int i = 0; i < N; i++) {
                arr[i][0] = sc.nextInt();   
                arr[i][1] = sc.nextInt();   
            }

            combination(arr, 0, L, N, 0, 0);

            System.out.println("#" + test_case + " " + maxFavor);  
        }
    }

    private static void combination(int[][] arr, int start, int L, int N, int totalFavor, int totalCal) {

        maxFavor = Math.max(maxFavor, totalFavor);

        for (int i = start; i < N; i++) {
            int nextCal = totalCal + arr[i][1];
            
            if (nextCal > L) continue;      
            
            int nextFavor = totalFavor + arr[i][0];

            combination(arr, i + 1, L, N, nextFavor, nextCal);
        }
    }
}