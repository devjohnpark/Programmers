import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 순방향으로 읽었을 때와 역방향으로 읽었을 때 같은 문자열 만들기
// 사전순으로 앞서는 것을 출력
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] alphabet = new int['Z' - 'A' + 1];

        for (int i = 0; i < str.length(); i++) {
            alphabet[str.charAt(i) - 'A']++;
        }

        int oddCnt = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] % 2 == 1) {
                oddCnt++;
            }
        }

        if (oddCnt > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        Stack<Character> stack = new Stack<>();



        // 알파벳 순회
        for (int i = 0; i < alphabet.length; i++) {
            // 알파벳 개수 반 출력 및 입력
            int n = alphabet[i] / 2;
            if (alphabet[i] % 2 == 1) {
                alphabet[i] = 1;
            } else {
                alphabet[i] = 0;
            }
            char c = (char) ('A' + i);
            for (int j = 0; j < n; j++) {
                System.out.print(c);
                stack.push(c);
            }
        }

        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == 1) {
                char c = (char) ('A' + i);
                System.out.print(c);
            }
        }


        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }


    }



}