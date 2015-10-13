import java.util.HashMap;

/**
 * Created by why on 10/9/15.
 */
public class TrieNode {
    char ch;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;

    // Initialize your data structure here.
    public TrieNode() {

    }
    public TrieNode(char ch) {
        this.ch = ch;
    }
}
