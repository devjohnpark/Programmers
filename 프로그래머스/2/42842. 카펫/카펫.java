// 카펫의 가로 세로는 Y로 만드는 직사각형의 가로 세로 보다 +2 이상 커야함
// 카펫은 Y와 B로 생성할수있다.
// 따라서 Y로 생성한 직사각형과 B의 개수를 합쳐서 생성할수 있는 직사각형중에서, Y의 가로와 세로보다 +2이상 큰 직사각형을 구해라.(단, 가로>= 세로)

import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        // Y로 생성할수있는 모든 직사각형의 경우의 수를 저장
        // Y와 B로 합쳐서 생성 가능한 가로 >= 세로인 직사각형을 저장
        // 모든 카페트 경우의 숫에서 Y의 가로와 세로보다 +2이상인 직사각형만 반환 배열에 저장
        
        List<int[]> yellows = new ArrayList<>();
        List<int[]> carpets = new ArrayList<>();
        List<int[]> result = new ArrayList<>();
        
        for (int i = 1; i <= yellow; i++) {
            if (yellow % i == 0) {
                int w = yellow / i;
                int h = i;
                yellows.add(new int[] { w, h });
            }
        }
        
        int carpet = brown + yellow;
        
        for (int i = 1; i <= carpet; i++) {
            if (carpet % i == 0) {
                int w = carpet / i;
                int h = i;
                if (w >= h) {
                    carpets.add(new int[] { w, h });
                }
            }
        }
        
        for (int[] c: carpets) {
            int cw = c[0];
            int ch = c[1];
            for (int[] y: yellows) {
                int yw = y[0];
                int yh = y[1];
                if (cw >= yw + 2 && ch >= yh + 2) {
                    result.add(new int[] { cw, ch });
                }
            }
            
        }
        
        // carpets를 돈다.
        // yellows를 돈다.
        // yellows의 요소중 각 가로와 세로보다 2이상 큰 카페트를 result에 저장한다.
        
        
        return result.get(0);
        
    }
}