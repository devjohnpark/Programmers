// import java.util.*;

// // 제한사항을 보았는가? 주어진 공항 개수 3개 이상 10000개 이하 -> 10^4이므로 10^8까지라서 시간복잡도 n^2을 이하로 풀어야한다.
// // 문제를 보고 알고리즘을 떠올렸는가? 항공권 정보가 담긴 2차원 배열 방문하는 티켓으로 공항 경로를 배열에 담는것이므로 DFS를 써야한다.
// // 2차원 배열에서 공항간에 탐색을 어떻게 진행할것인가? 
// // 1. 배열의 첫번째 요소에서 두번째 요소로 탐색하도록 해야한다.
// // 2. 모든 경로를 탐색해야하며, 방문 마킹을 해서 방문한곳은 제외시킨다.
// // 3. 2차원 배열을 탐색할때 경로가 모두 성립하지않으므로, 성립하지 않을때 백트래킹을 수행해서 시간복잡도를 낮춘다.
// class Solution {
//     private final List<String> result = new ArrayList<>();
    
//     // 이차원 배열에서 각 첫번째 요소를 출발점으로 순회시, 중복 탐색을 제거하기 위해 출발점 방문 마킹
    
//     // 시작점(첫번째 요소)를 기준으로 방문한 항공을 마킹
//     private boolean[] visited;
    
//     public String[] solution(String[][] tickets) {        
//         // 찾은 탐색 경로를 공항 이름과 공백을 붙여서 문자열로 만들어서 List<String>에 저장
//         dfs(0, "ICN", "ICN", tickets, new boolean[tickets.length]);
        
//         // 탐색 경로 문자열을 사전순 정렬
//         Collections.sort(result);
        
//         // 가장 첫번째 문자열을 반환
//         String[] answer = result.get(0).split(" ");
//         return answer;
//     }
    
//     // 티켓을 모두 사용할 경우, 탐색 경로 완성되므로 리스트에 추가
//     // int depth == tickets.length
    
//     // 항공권이 연결되기 위해서는 두번째 요소와 첫번째 요소가 같아야한다.
//     // 따라서 재귀 메서드를 호출할때 두번째 요소를 매개변수로 전달한다. (매개변수명은 또 출발점이되므로 start로 지정)
//     // if(tickets[i][0].equal(start)) 일때만 재귀메서드 호출
    
//     // 모든 출발점을 시작으로 경로 탐색
//     // 방문한 항공 방문 표시
//     // dfs 탐색
//     // 방문한 항공 방문 표시를 제거해서 부모 노드올라가 형제 노드로 탐색하게 한다. 
    
//     // 방문 표시 제거를 빼면 DFS는 단 하나의 경로만 탐색하고 끝나버리게 된다.
//     // 모든 항공권을 한 번씩 사용하여 가능한 모든 여행 경로를 찾아야한다.
//     private void dfs(int depth, String start, String route, String[][] tickets, boolean[] visited) {
//         if (tickets.length == depth) {
//             result.add(route);
//             return;
//         }
        
//         // 모든 경로를 탐색하며, 방문한 공황은 제외
//         for (int i = 0; i < tickets.length; i++) {
//             // 현재 공항(start)에서 출발하는 항공권을 찾는다.ㅁㅊ
//             if (tickets[i][0].equals(start) && !visited[i]) {
//                 visited[i] = true; // 항공권 사용
//                 dfs(depth + 1, tickets[i][1], route + " " + tickets[i][1], tickets, visited);
//                 visited[i] = false; // 항공권 미사용
//             }
//         }
//     }
// }


// 핵심 알고리즘
// 각 배열에서 2번째 요소와 1번째 요소가 동일해야 경로 탐색이 가능
// 위의 조건이 일치할때, DFS 탐색을 하고 아니면 무시한다.
// DFS 탐색 이후, 부모까지의 경로를 유지하고 형제 노드를 탐색하기 위해 백트래킹을 해야한다.
// 다만 백트래킹을 무엇으로 사용할지? 방문 배열을 재활용해서 true/false 처리 or List<String> 문자열 경로 추가 삭제
// List<String>을 사용하면 리스트 배열을 사용해야한다.
// 방문 배열을 사용하면 List<String> 만을 사용해도 되며, 경로 추가전 '-'을 더하고 공항 이름을 붙인다.
// 결과값중 여러 String을 사전순으로 정렬한뒤에 '_'으로 나눠서 맨앞에 있는 것을 반환한다.
// 이 문제는 문자열 다루는 것에 정확한 이해와 암기가 필요하다.

import java.util.*;

class Solution {    
    List<String> result = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {        
        // 백트리킹 알고리즘 진행
        dfs(tickets, "ICN", "ICN", 0, new boolean[tickets.length]);
        
        // 사전순 정렬
        Collections.sort(result);
        
        // ' '으로 나눠서 맨앞에 있는 값을 반환
        return result.get(0).split(" ");
    }
    

    private void dfs(String[][] tickets, String start, String path, int depth, boolean[] visited) {
        // 재귀 탈출
        // 깊이가 티켓의 개수와 같을시, 모든 도시 방문한 것이다. 따라서 완성된 경로를 추가
        if (tickets.length == depth) {
            result.add(path.toString());
            return;
        }
        
        
        // for문으로 2차원 배열을 모두 탐색
        // 만일 배열의 첫번째 요소와 두번쨰 요소값이 일치시
        // 배열에 대해 방문 마킹
        // dfs 탐색 수행 (경로를 추가해서 인자로 넘김)
        // 배열에 대해 방문 언마킹
        for (int i = 0; i < tickets.length; i++) {
            if (start.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                dfs(tickets, tickets[i][1], path + " " + tickets[i][1], depth + 1, visited);
                visited[i] = false;
            }
        }
    }
    
}

