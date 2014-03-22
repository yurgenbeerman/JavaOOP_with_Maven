package edu.utils;

/**
 * Created by Lena on 22.03.14.
 */

import java.util.ArrayList;
import java.util.List;

public class Tree extends ArrayList<TreeNode> {
    public Tree(String rootNodeData) {
        this.add(new TreeNode(rootNodeData, null, null));
    }

    public void addNode(String nodeData) {
        //TODO Node constructor should take 4 parameters: parent, left, right and data
        //TODO check to what tree branch add new node -- needs getHeight
        this.add(new TreeNode(nodeData, null, null));
    }


}