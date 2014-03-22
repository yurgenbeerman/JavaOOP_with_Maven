package edu.utils;

/**
 * Created by Lena on 22.03.14.
 */

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private TreeNode root;
    public Tree(String rootNodeData) {
        this.root = new TreeNode(rootNodeData, null);
    }

    public void addNode(String nodeData) {
        //TODO Node constructor should take 4 parameters: parent, left, right and data
        //TODO check to what tree branch add new node -- needs getHeight
        root.addNode(nodeData, null);
    }

    public int getHeight() {
        return 2;//this.get(0).getHeight();
    }
}