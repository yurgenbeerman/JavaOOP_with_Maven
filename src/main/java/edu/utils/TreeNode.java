package edu.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lena on 22.03.14.
 */
public class TreeNode {
    private static final int MAX_CHILDREN_COUNT = 2;
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
        if (childNodes.size() < MAX_CHILDREN_COUNT) {
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
        int minHeight = heights[0];
        int minHeightIndex = 0;
        for (int i = 0; i < heights.length; i++) {
            if ( heights[i] < minHeight )
                minHeight = heights[i];
                minHeightIndex = i;
        }
        return minHeightIndex;
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
            int childNodesSize = 0;
            for (TreeNode childNode : childNodes) {
                childNodesSize += childNode.size();
            }
            return 1 + childNodesSize;
        } else
            return 1;
    }

    public int getChildrenCount() {
        return this.childNodes.size();
    }

    public int getMaxChildrenCount() {
        return this.MAX_CHILDREN_COUNT;
    }


}
