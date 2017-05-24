package jilong.split;

import jilong.tree.TrieTree;

/**
 * @author Administrator
 * @date 2016/12/10.
 */
public class TrieDictionary implements Dictionary {

    private TrieTree tree;

    public TrieDictionary(String[] keyWords) {
        tree = new TrieTree();
        for (String keyWord : keyWords) {
            tree.insert(keyWord);
        }
    }

    @Override
    public MatchResult indexMaxLenSuffix(char[] str, int start, int end) {
        return tree.indexMaxLenSuffix(str, start, end);
    }

}
