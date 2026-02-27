import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        String[] targets = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        int cnt = 0;
        // 0, 1,
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < targets.length; j++) {
                if (word.startsWith(targets[j], i)) {
                    i += targets[j].length() - 1;
                }
            }
            cnt++;
        }
        System.out.println(cnt);
    }
}