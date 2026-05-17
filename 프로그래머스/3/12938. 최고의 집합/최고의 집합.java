/*
4 8: [2,2,2,2]
2 9: [4, 5]
4 7: [1, 2, 2, 2]
3 8: [2,3,3]
5 8: [1, 1, 2, 2, 2]
5 7: [1,1,1,2,2], 3 15: [5,5,5]

패턴 찾기
s%n 개수 만큼 s/n 보다 1 큰값이 온다.
허나, 3 15: [5,5,5]를 보면 정확히는 s%n 개수만큼 s/n+1값이 온다.
즉, n-s%n 개수만큼 s/n가 앞에 오고 s%n 개수만큼 s/n+s%n가 뒤에 온다.
*/

import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        List<Integer> result = new ArrayList<>();
        // 1 2: [1,1]
        // 4 3: X
        if (n > s) return new int[] {-1};
        int q = s/n;
        int r = s%n;
        int[] nums = new int[n];
        
        // n개중에서 나머지의 크기만큼 빼고 적재
        for (int i=0; i<n-r; i++) {
            nums[i] = q;
        }
            
        // n개중에서 나머지의 크기만큼 적재
        for (int i=n-r; i<n; i++) {
            nums[i] = q + 1;
        }
        
        return nums;
    }
}
