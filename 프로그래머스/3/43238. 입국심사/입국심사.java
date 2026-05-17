// 직접 시뮬레이션하면 n이 최대 10^9 이라서 사람을 한 명씩 배정하면 시간초과
// 그래서 사람 기준이 아니라 시간 기준으로 생각해야 한다.
// 핵심은 t분이면 n명을 모두 처리할 수 있는가? 이다.
// 기준을 시간을 두고 처리할수있는 명수에 따라 시간 구간을 좁혀나가며 이분 탐색을 해야한다.

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times); // O(10^5 x log10^5) = O(10^5x5x3)

        long left = 1;
        long right = (long) times[0] * n; // 가장 빠른 심사관이 모든 사람을 처리하는 시간으로 최대값 초기화
        long answer = right; 

        // O(10^5 x log10^18) = O(10^5 x 18log₂10) = O(10^5 x 약 64)
        while (left <= right) {
            // 이분 탐색으로 중앙값을 시간으로 둔다.
            long mid = (left + right) / 2; // O(log N) = O(log10^9*10^9) = O(log10^18)

            System.out.println(left + " " + mid + " " + right);
            
            long count = 0;
            
            // 시간을 기준(mid)으로 잡는다.
            // 기준인 시간내에 각 심사관은 몇명을 처리할수있는지 계산
            // 각 심사관의 처리할수있는 인원을 합산
            // EX) 기준 30분
            // 30 / 7 = 4명
            // 30 / 10 = 3명
            // 총 7명 처리 가능
            
            // 합산한 명수가 처리해야될 n보다 같거나 크면 더 짧은 시간내에 심사를 처리할수있다. -> 왼쪽 구간 이동 
            // 반대로 n보다 작으면 더 늦은 시간에 심사를 처리할수있다. -> 오른쪽 구간 이동
            for (int time : times) { // O(10^5)
                count += mid / time; 

                if (count >= n) { // 불필요한 탐색 제거
                    break;
                }
            }

            if (count >= n) { // 시간내에 n명 이상 처리 가능하므로 왼쪽 시간 탐색 
                answer = mid; // n명을 심사할 수 있는 최소 시간 갱신
                right = mid - 1; // 시간의 왼쪽 구간 설정
            } else { // 시간내에 n명을 처리하기에는 부족하므로 오른쪽 시간 탐색
                left = mid + 1; // 시간의 오른쪽 구간 설정 
            }
        }

        return answer; 
    }
}