// 시간복잡도 고려
// 입력크기는 10⁶ 이므로 시간복잡도는 O(n log n) 이내 여야한다. (10⁶ × 20 = 2 × 10⁷)
// 집 도둑 문제에서 순환 배열을 고려해야한다.
// 원형이므로 첫번째 집과 마지막 집을 동시에 털 수 없다.
// 첫번째 집을 포함한 집털기는 마지막 집을 제외 시킨다.
// 첫번째 집을 제외한 집털기는 마지막 집을 포함 시킨다.
class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        if (n == 1) return money[0];

        // case1: 첫 집 포함 가능 (마지막 집 제외)
        int case1 = robLinear(money, 0, n - 2);
        
        // case2: 첫 집 제외 (마지막 집 포함 가능)
        int case2 = robLinear(money, 1, n - 1);

        return Math.max(case1, case2);
    }
    
    private int robLinear(int[] nums, int start, int end) {
        int len = end - start + 1;

        int[] dp = new int[len];
        
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
        }

        return dp[len - 1];
    }
}