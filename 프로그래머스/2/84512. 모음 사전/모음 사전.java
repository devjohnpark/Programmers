class Solution {
    private static final char[] VOWELS = {'A', 'E', 'I', 'O', 'U'};
    private int order = 0;
    private int answer = 0;
    private boolean found = false;

    public int solution(String word) {
        backtrack(new StringBuilder(), word);
        return answer;
    }

    private void backtrack(StringBuilder sb, String target) {
        if (found) return; // 이미 찾았으면 더 탐색할 필요 없음

        if (sb.toString().equals(target)) {
            answer = order;
            found = true;
            return;
        }
        
        order++;

        if (sb.length() == VOWELS.length) return; // VOWELS 개수에 도달하면 종료

        for (char c : VOWELS) {
            sb.append(c);
            backtrack(sb, target);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
