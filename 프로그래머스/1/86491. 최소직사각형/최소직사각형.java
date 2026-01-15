// 지갑의 크기가 가장 작아야한다.
// 따라서 각 가로와 세로
// 값들의 범위 차가 적은 길이 끼리 묶어야한다.
// 이것은 모든 경우의 수를 탐색해야하므로 완전 탐색을 진행해야한다.


// 가장 큰 길이의 명함들, 가장 작은 길이의 명함들로 나눈다.
class Solution {
    public int solution(int[][] sizes) {
        for (int i = 0; i < sizes.length; i++) {
            int larger = Math.max(sizes[i][0], sizes[i][1]);
            int smaller = Math.min(sizes[i][0], sizes[i][1]);
            sizes[i][0] = larger;
            sizes[i][1] = smaller;
        }
        int maxW = 0;
        int maxH = 0;
        for (int i = 0; i < sizes.length; i++) {
            maxW = Math.max(sizes[i][0], maxW);
            maxH = Math.max(sizes[i][1], maxH);
        }
        int result = maxW * maxH;
        return result;
    }
}