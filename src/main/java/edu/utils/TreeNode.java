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

    public TreeNode(String data, TreeNode parentNode, List<TreeNode> childNodes) {
        this.data = data;
        this.parentNode = parentNode;
        this.childNodes = childNodes;
    }

    public int getHeight() {
        int result = 1;
        if ( (childNodes != null) && (childNodes.size() > 0) ){
            int[] heights = new int[childNodes.size()];
            for (int i = 0; i < childNodes.size(); i++) {
                heights[i] = childNodes.get(i).getHeight();
            }
//            int i = 0;
//            for (int height : heights) {
//                height = childNodes.get(i).getHeight();
//                i++;
//            }
            Arrays.sort(heights);
            result = heights[heights.length];
        }
        return result;
    }
}
