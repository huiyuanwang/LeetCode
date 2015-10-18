/**
 * Created by why on 10/16/15.
 */
public class Solution28 {
    /* 283 */
    public void moveZeroes(int[] nums) {
        int index = 0;
        int len = nums.length;
        for (int i = 0; i < len; i ++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index ++;
            }
        }
        while (index < len) {
            nums[index] = 0;
            index ++;
        }
    }
    /* 289 */
    public void gameOfLife (int[][] board) {
        if( board.length <= 0 || board[0].length <= 0) return;
        int row = board.length;
        int col = board[0].length;
        for(int i = 0; i < row; i ++) {
            for(int j = 0; j < col; j ++) {
                int x = getLiveNum(board, i, j);
                if(board[i][j] == 0) {
                    if(x == 3) board[i][j]+=10;
                }else {
                    if(x == 2 || x == 3) board[i][j]+=10;
                }
            }
        }
        for(int i = 0; i < row; i ++) {
            for(int j = 0; j < col; j ++) {
                board[i][j] = board[i][j] / 10;
            }
        }
    }
    public int getLiveNum(int[][] board, int x, int y) {
        int count = 0;
        for(int i = x - 1; i <= x + 1; i ++) {
            for(int j = y - 1; j <= y + 1; j ++) {
                if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || (i == x && j == y)) continue;
                if(board[i][j] %10 == 1) count++;
            }
        }
        return count;
    }
}
