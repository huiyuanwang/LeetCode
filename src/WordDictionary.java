import java.util.HashMap;
import java.util.Map;

/**
 * Created by why on 10/10/15.
 */
public class WordDictionary {
    private TrieNode root = new TrieNode();
    // Adds a word into the data structure.
    public void addWord(String word) {
        Map<Character, TrieNode> children = root.children;
        TrieNode next = null;
        for (int i = 0; i < word.length(); i ++) {
            char ch = word.charAt(i);
            if (children.containsKey(ch)) {
                next = children.get(ch);
            } else {
                next = new TrieNode(ch);
                children.put(ch, next);
            }
            children = next.children;
            if (i == word.length() - 1) {
                next.isLeaf = true;
            }
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return searchNode(word, root);
    }
    public boolean searchNode(String word, TrieNode node) {
        if (node == null) return false;
        if (word.length() == 0) return true;

        HashMap<Character, TrieNode> children = node.children;
        char ch = word.charAt(0);
        if (ch == '.') {
            for (char temp: children.keySet()) {
                if (searchNode(word.substring(1), children.get(temp))) {
                    return true;
                }
            }
        }
        if (! children.containsKey(ch)) {
            return false;
        } else {
            return searchNode(word.substring(1), children.get(ch));
        }
    }
}
