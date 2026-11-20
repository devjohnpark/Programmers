// baabaa
// bbaa
// aa
// stack 사용해서 top과 현재 문자가 같으면 pop해서 제거/다르면 push
import java.util.*;

class Solution
{
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        
        for (int i=0; i<s.length(); i++)  {
            char c = s.charAt(i);
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        
        return stack.isEmpty() ? 1 : 0;
    }
}