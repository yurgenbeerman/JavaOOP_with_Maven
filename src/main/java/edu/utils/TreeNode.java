package edu.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lena on 22.03.14.
 */
public class TreeNode {
    String data;
    TreeNode parentNode;
    List<TreeNode> childNodes;

    public TreeNode(String data, TreeNode parentNode) {
        this.data = data;
        this.parentNode = parentNode;
        this.childNodes = new ArrayList<TreeNode>();
    }

    public void addNode(String data, TreeNode parentNode) {
        //TODO Node constructor should take 4 parameters: parent, left, right and data
        //TODO check to what tree branch add new node -- needs getHeight
        childNodes.add(new TreeNode(data, null));
    }

    public int getHeight() {
        int result = 1;
        if ( (childNodes != null) && (childNodes.size() > 0) ) {
            int[] heights = new int[childNodes.size()];
            for (int i = 0; i < childNodes.size(); i++) {
                heights[i] = childNodes.get(i).getHeight();
            }
            Arrays.sort(heights);
            result = 1 + heights[heights.length-1];
        }
        return result;
    }

    public int size() {
        if (childNodes != null) {
            return 1 + childNodes.size();
        } else
            return 1;
    }
}
