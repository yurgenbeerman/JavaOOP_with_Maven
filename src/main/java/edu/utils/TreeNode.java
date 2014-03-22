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

    public void addNode(String data) {
        //TODO check to what tree branch add new node -- needs getHeight
        if (childNodes.size() < 2) {
            childNodes.add(new TreeNode(data, this));
        } else {
            int[] heights = new int[childNodes.size()];
            for (int i = 0; i < childNodes.size(); i++) {
                heights[i] = childNodes.get(i).getHeight();
            }
            childNodes.get(getShortestNodeIndex(heights)).addNode(data);
        }
    }

    private int getShortestNodeIndex(int[] heights) {
//        int[][] heightsIndexes = new int[heights.length][2];
//        for (int i = 0; i < heights.length; i++) {
//            heightsIndexes[i][]
//        }
        return 0;
    }

    public int getHeight() {
        int result = 1;
        if ( (childNodes != null) && (childNodes.size() > 0) ) {
            int[] heights = getSortedHeights();
            result = 1 + heights[heights.length-1];
        }
        return result;
    }

    public int[] getSortedHeights() {
        int[] heights = new int[childNodes.size()];
        for (int i = 0; i < childNodes.size(); i++) {
            heights[i] = childNodes.get(i).getHeight();
        }
        Arrays.sort(heights);
        return heights;
    }

    public int size() {
        if (childNodes != null) {
            return 1 + childNodes.size();
        } else
            return 1;
    }
}
