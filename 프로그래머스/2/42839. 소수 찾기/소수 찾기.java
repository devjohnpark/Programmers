// n자리수애서 각 자리수의 조합으로 만드는 모든 소수를 판별해서 카운팅
// 백트래킹으로 숫자의 조합이 만들어질때 마다 소수인지 확인해서 맞다면 카운팅

import java.util.*;

class Solution {
    public int solution(String numbers) {
        HashSet<Integer> hashSet = new HashSet<>();
        boolean[] visited = new boolean[numbers.length()];
        
        for (int i = 0; i < numbers.length(); i++) {
           backtracking("", visited, numbers, hashSet);
        }
        
        return hashSet.size();
    }
    
    private void backtracking(String cur, boolean[] visited, String numbers, HashSet<Integer> hashSet) {
        if(cur.length() != 0) {
            int num = toInt(cur);
            if (isDecimal(num)) {
                hashSet.add(num);
            }
        }
        
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(cur + numbers.charAt(i), visited, numbers, hashSet);
                visited[i] = false;
            }
        }
    }
    
    private int toInt(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            num *= 10;
            num += str.charAt(i) - '0';
        } 
        return num;
    }
    
    private boolean isDecimal(int num) {
        if (num == 0 || num == 1) return false;
        for (int i = 2; i < num - 1; i++) {
            if (num % i == 0) return false;
        } 
        return true;
    }
}