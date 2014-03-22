package edu.utils;

import java.util.ArrayList;
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
        return 0;
    }
}
