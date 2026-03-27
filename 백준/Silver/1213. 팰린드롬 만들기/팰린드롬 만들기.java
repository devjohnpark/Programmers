import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 홀수 개수인 알파벳이 하나를 초과하면 팰린드롬 생성이 불가
// 사전순으로 알파벳 왼쪽 절반 문자 저장
// 중간의 홀수 알파벳의 값 저장
// 알파멧 왼쪽 + 홀수 알파벳이 있는 경우, 해당 알파벳 + 알파벳 왼쪽 절반 반대 (reverse)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] freqAlphabet = new int['Z' - 'A' + 1];

        for (int i = 0; i < str.length(); i++) {
            freqAlphabet[str.charAt(i) - 'A']++;
        }

        // 홀수 개수인 문자가 1개 초과하면 불가능
        int oddCnt = 0;
        char midChar = 0;
        for (int i = 0; i < freqAlphabet.length; i++) {
            if (freqAlphabet[i] % 2 == 1) {
                oddCnt++;
                midChar = (char) ('A' + i); // 홀수 알파벳 저장
            }
        }
        if (oddCnt > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuilder left = new StringBuilder();
        
        // 알파벳 왼쪽 절반 문자 저장
        for (int i = 0; i < freqAlphabet.length; i++) {
            int n = freqAlphabet[i] / 2;
            char c = (char) ('A' + i);
            for (int j = 0; j < n; j++) {
                left.append(c);
            }
        }

        StringBuilder result = new StringBuilder();
        
        // 알파벳 왼쪽 절반 
        result.append(left);
        
        // 홀수 알바펫 있으면 중간에 올 값 저장
        if (oddCnt != 0) {
            result.append(midChar);
        }
        
        // 알파벳 왼쪽 절반 반대 (reverse)
        result.append(left.reverse());
        System.out.println(result.toString());
    }
}