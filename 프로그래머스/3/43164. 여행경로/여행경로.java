// 제한사항
// 주어진 공항 개수 3개 이상 10000개 이하 -> 10^4이므로 10^8까지라서 시간복잡도 n^2을 이하로 풀어야한다.

// 문제 정의
// 주어진 항공권은 모두 사용해서 완성된 경로 생성: 완전 탐색을 통해 경로를 완성
// 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return: List<String> 중에서 문자열을 사전순으로 정렬하고 첫번째 문자열 가져오기

// 핵심 알고리즘
// 각 배열에서 2번째 요소와 1번째 요소가 동일해야 경로 탐색이 가능
// DFS를 사용해서 조건에 맞는 모든 경로를 탐색한다. 효율성을 위해서 조건에 부합하지 않으면 형제 정점으로 이동하는 백트래킹을 사용한다.
// 깊이 탐색을 진행할때마다 모든 배열을 확인해야하므로 방문한 정점을 탐색을 제외한다.
// DFS 전 방문 마킹을하고 DFS 이후 언마킹을 하여 마킹 유무를 원상태로 복원한다.
// 이를 위해 tikcets의 개수만큼 방문을 배열을 사용한다.
// 찾은 경로는 공백을 포함해서 정점 이름을 추가한다. 
// 백트래킹을 마치고 Collections.sort을 사용해서 사전순으로 정렬한다.
// List.get(0).split(" ")으로 나눠서 모든 공항 이름을 String[]으로 반환한다.
import java.util.*;

class Solution {    
    private final List<String> result = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {        
        // 백트래킹 알고리즘
        backtracking(tickets, 0, new boolean[tickets.length], "ICN", "ICN");
        
        Collections.sort(result);
        
        return result.get(0).split(" ");
    }    
    
    private void backtracking(String[][] tickets, int depth, boolean visited[], String start, String route) {
        if (tickets.length == depth) {
            result.add(route);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            // 인자로 넘긴 배열의 1번 요소값으로 다음 티켓의 배열 0번 요소값과 동일하면 경로 추가 가능
            if (tickets[i][0].equals(start) && !visited[i]) {
                visited[i] = true;
                backtracking(tickets, depth + 1, visited, tickets[i][1], route + " " + tickets[i][1]);
                visited[i] = false;
            }
        }
    }
}



