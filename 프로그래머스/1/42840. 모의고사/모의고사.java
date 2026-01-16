import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] nums1 = { 1, 2, 3, 4, 5};
        int[] nums2 = { 2, 1, 2, 3, 2, 4, 2, 5};
        int[] nums3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int[] scores = new int[3];
        
        for (int i = 0; i < answers.length; i++) { 
            if (answers[i] == nums1[i % nums1.length]) {
                scores[0]++;        
            }
            
            if (answers[i] == nums2[i % nums2.length]) {
                scores[1]++;        
            }
            
            if (answers[i] == nums3[i % nums3.length]) {
                scores[2]++;        
            }
        }
        
        int max = Math.max(scores[0], Math.max(scores[1], scores[2]));
        
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < scores.length; i++) {
            if (max == scores[i]) result.add(i + 1);
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}