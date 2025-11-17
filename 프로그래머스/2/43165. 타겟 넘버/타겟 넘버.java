// 완전 탐색시 경우의 수는 2^(numbers.length) -> 최대 2^20 == 1024^2는 1000000 근사 < 10^8
// 깊이 탐색으로 sum == target을 만족하는 모든 경로를 찾는다.
// 각 수는 +/-로 분기해서 (그래프의 깊이)^2씩 경우의 수가 늘어난다.
// 그러므로 깊이 == numbers.length를 만족할때까지 dfs(sum + numbers[깊이]) + dfs(sum - numbers[깊이])로 재귀 탐색을 수행한다.
// sum == target을 만족하면 1을 반환하고 그렇지 않으면 0을 반환해서 완성된 경롤르 카운팅 하도록한다.
class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
    
    private int dfs(int[] numbers, int target, int depth, int sum) {
        if (depth == numbers.length) return sum == target ? 1 : 0;
        
        return dfs(numbers, target, depth + 1, sum + numbers[depth]) + dfs(numbers, target, depth + 1, sum - numbers[depth]);
    }
}