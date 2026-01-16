import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] nums1 = { 1, 2, 3, 4, 5};
        int[] nums2 = { 2, 1, 2, 3, 2, 4, 2, 5};
        int[] nums3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int nums1Cnt = 0;
        int nums2Cnt = 0;
        int nums3Cnt = 0;
        
        for (int i = 0; i < answers.length; i++) { 
            if (answers[i] == nums1[i % nums1.length]) {
                nums1Cnt++;        
            }
            
            if (answers[i] == nums2[i % nums2.length]) {
                nums2Cnt++;        
            }
            
            if (answers[i] == nums3[i % nums3.length]) {
                nums3Cnt++;        
            }
        }
        
        int[][] sortCnt = { {nums1Cnt, 1}, {nums2Cnt, 2}, {nums3Cnt, 3} };
        
        Arrays.sort(sortCnt, (a, b) -> Integer.compare(b[0], a[0]));
    
        if (sortCnt[0][0] == 0) return new int[] {};
        
        List<Integer> list = new ArrayList<>();
        list.add(sortCnt[0][1]);
        
        for (int i = 1; i < sortCnt.length; i++) {
            if (sortCnt[0][0] == sortCnt[i][0]) {
                list.add(sortCnt[i][1]);
            }
        } 
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}