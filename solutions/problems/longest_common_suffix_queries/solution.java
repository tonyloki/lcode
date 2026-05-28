class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];

        int idx = -1;
        int len = Integer.MAX_VALUE;
    }
    TrieNode root = new TrieNode();
    private void update(TrieNode node, int index, int length) {
        if (length < node.len || (length == node.len && index < node.idx)) {
            node.len = length;
            node.idx = index;
        }
    }

    private void insert(String word, int index) {
        TrieNode node = root;
        int n = word.length();
        update(node, index, n);
        for (int i = n - 1; i >= 0; i--) {
            int ch = word.charAt(i) - 'a';

            if (node.children[ch] == null) {
                node.children[ch] = new TrieNode();
            }

            node = node.children[ch];
            update(node, index, n);
        }
    }

    private int search(String word) {
        TrieNode node = root;

        for (int i = word.length() - 1; i >= 0; i--) {
            int ch = word.charAt(i) - 'a';

            if (node.children[ch] == null) {
                break;
            }

            node = node.children[ch];
        }

        return node.idx;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i);
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = search(wordsQuery[i]);
        }

        return ans;
    }
}