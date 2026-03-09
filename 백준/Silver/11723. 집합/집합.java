import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Set<Integer> set = new HashSet<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("add")) {
                set.add(Integer.parseInt(st.nextToken()));

            } else if (cmd.equals("remove")) {
                set.remove(Integer.parseInt(st.nextToken()));

            } else if (cmd.equals("check")) {
                if (set.contains(Integer.parseInt(st.nextToken()))) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                sb.append("\n");
            } else if (cmd.equals("toggle")) {
                int x = Integer.parseInt(st.nextToken());
                if (set.contains(x)) {
                    set.remove(x);
                } else {
                    set.add(x);
                }

            } else if (cmd.equals("all")) {
                for (int j = 1; j <= 20; j++) {
                    set.add(j);
                }

            } else if (cmd.equals("empty")) {
                set.clear();
            }
        }
        System.out.println(sb.toString());
    }
}