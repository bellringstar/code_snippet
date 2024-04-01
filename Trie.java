import java.util.HashMap;
import java.util.Map;

public class Trie {
	static class TrieNode {
		private Map<Character, TrieNode> children;
		private boolean isEndOfWord;

		public TrieNode() {
			children = new HashMap<>();
			isEndOfWord = false;
		}
	}

	TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public void insert(String word) {
		TrieNode node = root;

		for (int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			node.children.putIfAbsent(currentChar, new TrieNode());
			node = node.children.get(currentChar);
		}

		node.isEndOfWord = true;
	}

	public boolean search(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			TrieNode nextNode = node.children.get(currentChar);
			if (nextNode == null) return false;
			node = nextNode;
		}
		return node.isEndOfWord;
	}

	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for (int i = 0; i < prefix.length(); i++) {
			char currentChar = prefix.charAt(i);
			TrieNode nextNode = node.children.get(currentChar);
			if (nextNode == null) return false;
			node = nextNode;
		}
		return true;
	}


}
