import java.util.*;

class Solution {
    // leetcode보다 문해력이 필요. 
    // 시간복잡도: 1000000이므로 O(logn)내로 풀어야한다. 
    // 문제 정의
    // 배열에 저장된 음식의 스코프들을 조합해서 k 이상으로 만드는데 최소 횟수를 구해야한다.
    
    // 반복
    // 0. 배열의 값이 모두 k보다 크면 카운트 횟수 반환
    // 1. 배열에 k보다 작은값 두개를 조합: 두개의 요소를 선택해야하므로 O(N)이다.
    
    // 2. 스코프를 다시 배열에 삽입
    
    // 최소힙
    // 1. 배열 요소 모두 삽입
    
    // 반복
    // 1. 최소힙의 최솟값이 k보다 같거나 크면 or 빈 최소힙이 아니면 반복문 탈출
    // 2. 최소힙의 저장된 값의 개수가 2보다 작으면 -1 반환
    // 2. 최소값 두개 추출해서 조합 
    // 3. 횟수 카운팅
    // 4. 최소힙의 가장작은 두개를 조합한 값을 다시 삽입 
    
    // 반환
    // 횟수 카운팅 변수 반환

    
    // 정렬 nlogn = 10^6 x 6log10^6
    public int solution(int[] scoville, int K) {
        int answer = 0;

		// 1. `PriorityQueue`을 최소힙으로 생성한다.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 디폴트 오름차순

		// 2. 배열을 순회하면서 우선순위큐에 넣는다.
        for (int n: scoville) {
            minHeap.add(n);
        }

		// 3. 우선순위큐가 비지않거나 가장 작은 값이 `K`보다 작으면 반복한다.
        while (!minHeap.isEmpty() && minHeap.peek() < K) {
	        // 4. 우선순위큐의 크기가 2보다 작으면, K 이상으로 만들 수 없으므로 -1을 반환한다.
            if (minHeap.size() < 2) return -1; 

			// 5. 우선순위큐에서 가장 작은 2개의 값을 차례로 삭제해서 공식에 대입한 새로운 값으로 만든다.
            int min1 = minHeap.remove(); // 가장 작은 값
            int min2 = minHeap.remove(); // 두번째로 가장 작은 값

			// 6. 새로운값을 다시 우선순위큐에 넣는다.
            minHeap.add(min1 + (min2 * 2)); 
        
	        // 7. 공식을 대입한 회수를 카운팅한다.
            answer++; 
        }
        // 8. 카운팅한 회수를 반환한다.
        return answer;
    }
}