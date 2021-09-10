package org.seancheer.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 * <p>
 * Implement the Trie class:
 * <p>
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 * <p>
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 * 实现一个前缀树
 *
 * @author: seancheer
 * @date: 2021/8/26
 **/
public class _208ImplementTrie_PrefixTree {

    public static void main(String[] args) {
        Trie trie = new TrieTest();
        trie.insert("apple");
        trie.search("apple");   // return True
        trie.insert("cpp");
        trie.search("app");     // return False
        trie.startsWith("app"); // return True
        trie.insert("app");
        trie.search("app");     // return True
    }

    static class Trie {
        public static final int CHAR_SUM = 26;
        private TrieNode root;

        static class TrieNode {
            public TrieNode(char value) {
                this.val = value;
            }

            public char val;
            public boolean isWord = false;
            public TrieNode[] children = new TrieNode[CHAR_SUM];
        }

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode(' ');
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (null == word || word.isEmpty()) {
                return;
            }

            TrieNode cur = root;
            for (int i = 0; i < word.length(); ++i) {
                //不能共享节点，否则search的时候会有问题，所以这里以a开头的，使用自己的节点，以b开头的，也是用自己的节点
                int idx = word.charAt(i) - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode(word.charAt(i));
                }
                cur = cur.children[idx];
            }
            cur.isWord = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            if (null == word || word.isEmpty()) {
                return false;
            }

            TrieNode cur = root;
            for(int i = 0;i < word.length();++i){
                int idx = word.charAt(i) - 'a';
                if (cur.children[idx] == null){
                    return false;
                }
                cur = cur.children[idx];
            }
            return cur.isWord;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            //这个比search简单多了，只需要判断前缀即可
            if (null == prefix || prefix.isEmpty()) {
                return false;
            }

            TrieNode cur = root;
            for(int i = 0;i < prefix.length();++i){
                int idx = prefix.charAt(i) - 'a';
                if (cur.children[idx] == null){
                    return false;
                }
                cur = cur.children[idx];
            }
            return true;
        }
    }


    /**
     * 测试类
     */
    static class TrieTest extends Trie {
        static boolean firstTime = true;

        public TrieTest() {
            super();
            if (!firstTime) {
                System.out.println("");
            }
            firstTime = false;
        }

        @Override
        public void insert(String word) {
            super.insert(word);
        }

        @Override
        public boolean search(String word) {
            boolean res = super.search(word);
            System.out.print(res + " ");
            return res;
        }

        @Override
        public boolean startsWith(String prefix) {
            boolean res = super.startsWith(prefix);
            System.out.print(res + " ");
            return res;
        }
    }
}
