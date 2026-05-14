// 집합: 1 ~ s - 1 까지 숫자 중에서 n개 선택
// 곱이 가장 클 조건
// n이 짝수면
//  s가 짝수면: s/n가 n개 -> ex) 4 8: [2,2,2,2]
//  s가 홀수면: s/n가 n/2개 + s/n+1가 n/2 개 -> ex) 2 9: [4, 5]
// n이 홀수면
//  s가 짝수면: s/n가 n/2개 + s/n+1가 n/2+1 개 -> ex) 3 8: [2,3,3], 5 8: [1, 1, 2, 2, 2]
//  s가 홀수면: s/n가 n/2+1개 s/n+1가 n/2개 -> ex) 3 7: [2,2,3], 5 7: [1,1,1,2,2]

// import java.util.*;

// class Solution {
//     public int[] solution(int n, int s) {
//         List<Integer> result = new ArrayList<>();
//         if (s < n) return new int[] {-1};
//         if (n % 2 == 0) {
//             if (s % 2 == 0) {
//                 insertValues(result, s/n, n);
//             } else {
//                 insertValues(result, s/n, n/2);
//                 insertValues(result, s/n+1, n/2);
//             }
//         } else {
//             if (n % s == 0) insertValues(result, s/n, n);
//             else if (s % 2 == 0) {
//                 insertValues(result, s/n, n/2);
//                 insertValues(result, s/n+1, n/2+1);
//             } else {
//                 insertValues(result, s/n, n/2+1);
//                 insertValues(result, s/n+1, n/2);
//             }
//         }
//         return result.stream()
//                 .mapToInt(Integer::intValue)
//                 .toArray();
//     }
    
//     // value를 n번 삽입
//     private void insertValues(List<Integer> list, int value, int n) {
//         for (int i=0; i<n; i++) {
//             list.add(value);
//         }
//     }
// }

import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        // 자연수(1이상) n개의 합은 최소 n
        if (s < n) return new int[]{-1};

        int q = s / n; // 몫
        int r = s % n; // 나머지

        // q가 (n-r)개, (q+1)이 r개 → 오름차순이므로 q 먼저 삽입
        int[] result = new int[n];
        for (int i = 0; i < n - r; i++) result[i] = q;
        for (int i = n - r; i < n; i++) result[i] = q + 1;

        return result;
    }
}