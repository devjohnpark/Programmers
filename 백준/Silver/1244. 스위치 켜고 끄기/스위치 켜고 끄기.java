import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] s = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            if (sex == 1) {
                for (int j = idx; j <= N; j++) {
                    if (j%idx == 0) {
                        if (s[j] == 0) s[j] = 1;
                        else s[j] = 0;
                    }
                }
            } else {
                int left = idx - 1;
                int right = idx + 1;
                if (s[idx] == 0) s[idx] = 1;
                else s[idx] = 0;
                while(left >= 1 && right <= N && s[left] == s[right]) {
                    if (s[left] == 0) s[left] = s[right] = 1;
                    else s[left] = s[right] = 0;
                    left--;
                    right++;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(s[i] + " ");
            if (i % 20 == 0) System.out.println();
        }

    }
}

// 일단 입출력부터 확인해서 어떤값의 입력에 대한 출력을 해야하는지?
// 그 다음 문제의 요구사항을 정확히 인지한다.
// 문제 풀이 로직을 명확이 기재한다.
// 문제 풀이 로직을 큰 덩이로 나눈다.
// 큰덩이를 세부로 나눈다.
// 구현한다.

// 0 1 0 1 0 0 0 1
// 0 1 1 1 0 1 0 1
// 1 0 0 0 1 1 0 1

// 인덱스는 1부터 시작
// 남(1): 배열 순회중 배수인 인덱스는 반대값으로 스위칭 (자기 자신 포함)
// 여(2): 배열 순회중 인덱스를 대칭인 값까지만 반대값으로 스위칭 (자기 자신 포함, 좌우 동일 개)
// 출력: 스위치값을 한줄에 20개씩 출력
