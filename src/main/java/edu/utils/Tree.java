package edu.utils;

/**
 * Created by Lena on 22.03.14.
 */

import java.util.ArrayList;
import java.util.List;

public class Tree extends ArrayList<TreeNode> {
    public Tree(String rootNodeData) {
        this.add(new TreeNode(rootNodeData));
    }
}