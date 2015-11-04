import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by why on 11/2/15.
 */
public class STest {
    /**
     * 1 2 3
     * 8 9 4
     * 7 6 5
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int k = 1;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        while (left < right && top < bottom) {
            for (int j = left; j < right; j++) {
                res[top][j] = k++;
            }
            for (int i = top; i < bottom; i++) {
                res[i][right] = k++;
            }
            for (int j = right; j > left; j--) {
                res[bottom][j] = k++;
            }
            for (int i = bottom; i > top; i--) {
                res[i][left] = k++;
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        if (n % 2 != 0)
            res[n / 2][n / 2] = k;
        return res;
    }
    /**
     * valid sudoku
     */
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character> hash = new HashSet<Character>();
        /* row */
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (board[i][j] == '.')
                    continue;
                if (hash.contains(board[i][j]))
                    return false;
                else
                    hash.add(board[i][j]);
            }
            hash.clear();
        }
        /* column */
        for (int j = 0; j < board[0].length; j ++) {
            for (int i = 0; i < board.length; i ++) {
                if (board[i][j] == '.')
                    continue;
                if (hash.contains(board[i][j]))
                    return false;
                else
                    hash.add(board[i][j]);
            }
            hash.clear();
        }
        /* block */
        for (int k = 0; k < 9; k ++) {
            for (int i = k / 3 * 3; i < k / 3 * 3 + 3; i ++) {
                for (int j = k % 3 * 3; j < k % 3 * 3 + 3; j ++) {
                    if (board[i][j] == '.')
                        continue;
                    if (hash.contains(board[i][j]))
                        return false;
                    else
                        hash.add(board[i][j]);
                }
            }
            hash.clear();
        }
        return true;
    }

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        int res = 0;
        while (node != null || ! stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                k --;
                if (k == 0) {
                    res = node.val;
                    break;
                }
                node = node.right;
            }
        }
        return res;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0;
        int y = 0;
        while(m > 0 && n > 0){
            if(m == 1){
                for(int i = 0; i < n; i ++){
                    res.add(matrix[x][y ++]);
                }
                break;
            }else if(n == 1){
                for(int i = 0; i < m; i ++){
                    res.add(matrix[x ++][y]);
                }
                break;
            }

            for(int i = 0; i < n - 1; i ++){
                res.add(matrix[x][y ++]);
            }
            for(int i = 0;i < m - 1; i ++){
                res.add(matrix[x ++][y]);
            }
            for(int i = 0;i < n - 1;i ++){
                res.add(matrix[x][y --]);
            }
            for(int i = 0;i < m - 1; i ++){
                res.add(matrix[x --][y]);
            }

            x ++;
            y ++;
            m -= 2;
            n -= 2;
        }
        return res;
    }
}
