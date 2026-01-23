// 카펫의 가로 세로는 Y로 만드는 직사각형의 가로 세로 보다 +2 이상 커야함
// 카펫은 Y와 B로 생성할수있다.
// 따라서 Y로 생성한 직사각형과 B의 개수를 합쳐서 생성할수 있는 직사각형중에서, Y의 가로와 세로보다 +2이상 큰 직사각형을 구해라.(단, 가로>= 세로)

import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        // Y로 생성할수있는 모든 직사각형의 경우의 수를 저장
        // Y와 B로 합쳐서 생성 가능한 가로 >= 세로인 직사각형을 저장
        // 모든 카페트 경우의 숫에서 Y의 가로와 세로보다 +2이상인 직사각형을 반환한다.
        
        List<int[]> yellows = new ArrayList<>();
        List<int[]> carpets = new ArrayList<>();
        
        for (int h = 1; h <= yellow; h++) {
            if (yellow % h == 0) {
                int w = yellow / h;
                yellows.add(new int[] { w, h });
            }
        }
        
        int carpet = brown + yellow;
        
        for (int h = 3; h <= carpet / h; h++) {
            if (carpet % h == 0) {
                int w = carpet / h;
                if (w >= h) {  // 가로 >= 세로 조건
                    // yellows 리스트에서 조건 맞는 것 찾기
                    for (int[] y : yellows) {
                        int yw = y[0];
                        int yh = y[1];
                        
                        // 노란색의 사각형 가로 세로가 2이상 작아야한다.
                        if (yw <= w - 2 && yh <= h - 2) {
                            return new int[] {w, h};
                        }
                    }
                }
            }
        }
        return new int[] {};
    }
}