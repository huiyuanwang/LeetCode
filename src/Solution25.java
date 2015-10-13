import java.util.LinkedList;
import java.util.List;

/**
 * Created by why on 10/10/15.
 */
public class Solution25 {
    /* 257 */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<String>();
        helper(root, new StringBuilder(), res);
        return res;
    }
    public void helper(TreeNode node, StringBuilder sb, List<String> res) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            sb.append(node.val);
            res.add(sb.toString());
            return;
        }
        sb.append(node.val).append("->");
        if (node.left != null) helper(node.left, new StringBuilder(sb), res);
        if (node.right != null) helper(node.right, sb, res);
    }
}
