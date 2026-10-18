
class Solution {
    public int totalNQueens(int n) {
        return solve(n, 0, 0, 0, 0);
    }

    private int solve(int n, int row, int columns,
                      int diagonals, int antiDiagonals) {
        if (row == n) {
            return 1;
        }

        int available = ((1 << n) - 1)
                & ~(columns | diagonals | antiDiagonals);

        int count = 0;

        while (available != 0) {
            int bit = available & -available;
            available -= bit;

            count += solve(
                n,
                row + 1,
                columns | bit,
                (diagonals | bit) << 1,
                (antiDiagonals | bit) >> 1
            );
        }

        return count;
    }
}
