import java.util.HashMap;

class Solution {
    // 문제 정의: participant 배열 요소중에서 completion 배열의 요소에 포함 안된 요소를 찾아라. 참가자 중에는 동명이인이 있을 수 있다.
    // 시간 복잡도: 100,000명이므로 O(N^2) 이하여야됨
    // 부루트 포스: participant 배열 요소 순회하면서 completion 배열 요소값을 순회해야하므로 O(n^2)이 걸린다.
    // 핵심 문제 풀이: HashSet에 completion 요소를 저장해서 O(1) 내로 가져오면 총 시간 복잡도는 participant 배열 순회시간만 걸린다.
    // 주의사항: 동명이인이 있을수 있으므로 HashSet에서 HashMap으로 String에 대해 카운팅해서 저장하고, 감소시켜서 0이면 반환하는 것으로 한다.
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        for (String str: completion) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for (String str: participant) {
            // participant의 문자열을 가져와서 해시맵에 값을 가져온다.
            // 맵에서 가져온 값을 -1한다. (디폴트 0)
            // 값이 0보다 작으면 문자열을 리턴한다.
            // 맵에서 가져온 값을 다시 저장한다.
            Integer n = map.getOrDefault(str, 0) - 1;
            if (n < 0) return str;
            map.put(str, n);
        }
        return answer;
    }
}