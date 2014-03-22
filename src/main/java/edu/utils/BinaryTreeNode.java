package edu.utils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yurii.pyvovarenko on 22.03.14.
 */
public class BinaryTreeNode {
    String data;
    BinaryTreeNode childNodeRight;
    BinaryTreeNode childNodeLeft;

    public BinaryTreeNode(String data) {
        if (data != null)
            this.data = data;
        else
            throw new InvalidParameterException("Parameter cannot be null");
    }

    public void addNode(String data) {

    }

    public int getHeight() {
        return 0;
    }
    public int size() {
        return 0;
    }
}
