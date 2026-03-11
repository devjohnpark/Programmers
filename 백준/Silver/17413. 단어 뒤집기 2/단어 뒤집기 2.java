import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// stack, queue 사용
// <> 태그는 문자열을 뒤집으면 안된다.
// 기본 문자는 스택에 넣고 <, 공백, 입력 문자열 끝에 도달하면 출력한다.
// <가 나오면 >가 나올때까지 큐에 넣는다.

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '<') {
                while (!stack.isEmpty()) {
                    System.out.print(stack.pop());
                }
                queue.add(c);
            } else if (c == '>') {
                queue.add(c);
                while(!queue.isEmpty()) {
                    System.out.print(queue.poll());
                }
            } else if (queue.isEmpty()) {
                if (c == ' ') {
                    while (!stack.isEmpty()) {
                        System.out.print(stack.pop());
                    }
                    System.out.print(c);
                } else {
                    stack.push(c);
                }
            } else {
                queue.add(c);
            }

        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}