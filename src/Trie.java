import java.util.HashMap;

/**
 * Created by why on 10/9/15.
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;
        for (int i = 0; i < word.length(); i ++) {
            char ch = word.charAt(i);
            TrieNode node;
            if (children.containsKey(ch)) {
                node = children.get(ch);
            } else {
                node = new TrieNode(ch);
                children.put(ch, node);
            }

            children = node.children;
            if (i == word.length() - 1) {
                node.isLeaf = true;
                node.word = word;
            }
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode res = searchNode(word);
        if (res != null && res.isLeaf) return true;
        else return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (searchNode(prefix) != null) return true;
        else return false;
    }

    public TrieNode searchNode(String node) {
        HashMap<Character, TrieNode> children = root.children;
        TrieNode res = null;
        for (char ch: node.toCharArray()) {
            if (! children.containsKey(ch))
                return null;
            res = children.get(ch);
            children = res.children;
        }
        return res;
    }
}
