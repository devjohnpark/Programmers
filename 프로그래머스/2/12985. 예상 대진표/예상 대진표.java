// 1 2 3 4 5 6 7 8
// 다음 순번: n+1/2

// 1 2 3 4
// 1 2
// 1 1


class Solution
{
    public int solution(int n, int a, int b)
    {
        int round = 0;

        while (a != b) {

            a = (a + 1) / 2;
            b = (b + 1) / 2;

            round++;
        }

        return round;
    }
}