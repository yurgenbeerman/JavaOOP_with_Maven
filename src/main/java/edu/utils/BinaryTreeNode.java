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
            this.data = new String(data);
        else
            throw new InvalidParameterException("Parameter cannot be null");
    }

    public void addNode(String data) {
        if (data != null) {
            if (data.hashCode() < this.data.hashCode()) {
                if (this.childNodeLeft == null) {
                    this.childNodeLeft = new BinaryTreeNode(data);
                } else
                    this.childNodeLeft.addNode(data);
            } else {
                if (this.childNodeRight == null) {
                    this.childNodeRight = new BinaryTreeNode(data);
                } else
                    this.childNodeRight.addNode(data);
            }
        }
    }

    public int getHeight() {
        return 0;
    }
    public int size() {
        return 0;
    }
}
