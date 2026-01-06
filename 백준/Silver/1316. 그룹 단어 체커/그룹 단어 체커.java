import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int n = Integer.parseInt(s);
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            boolean[] alphabets = new boolean['z' - 'a' + 1];
            char prev = str.charAt(0); // a
            alphabets[prev - 'a'] = true;
            boolean isGroupWord = true;
            for (int j = 1; j < str.length(); j++) {
                // 문자 변경시, 기록된 문자 없으면 넘어간다.
                char cur = str.charAt(j);
                if (prev != cur && alphabets[cur - 'a']) {
                    isGroupWord = false;
                    break;
                }
                prev = cur;
                alphabets[cur - 'a'] = true;
            }
            if (isGroupWord) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

}

