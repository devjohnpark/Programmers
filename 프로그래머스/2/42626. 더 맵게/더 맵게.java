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
    
    // 횟수 카운팅 -1로 초기화
    
    // 반복
    // 1. 최소힙의 최솟값이 k보다 같거나 크면 or 빈 최소힙이 아니면 반복문 탈출
    // 2. 최소힙의 저장된 값의 개수가 2보다 작으면 -1 반환
    // 2. 최소값 두개 추출해서 조합 
    // 3. 횟수 카운팅
    // 4. 최소힙의 가장작은 두개를 조합한 값을 다시 삽입 
    
    // 반환
    // 횟수 카운팅 변수 반환

    
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 디폴트 오름차순
        // 내림차순
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        // O(nlogn)
        for (int n: scoville) {
            minHeap.add(n);
        }
        
         while (!minHeap.isEmpty() && minHeap.peek() < K) {
            if (minHeap.size() < 2) return -1; // 더 섞을 재료가 부족하면 불가능

            int min1 = minHeap.remove(); // 최소
            int min2 = minHeap.remove(); // 두 번째 최소

            minHeap.add(min1 + (min2 * 2));
            answer++;
        }
        return answer;
    }
}