package com.wfc.testcomm.treetest;

import java.util.ArrayList;
import java.util.List;
public class TwoTree {

    private int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private List<Node> nodeList = null;

    private class Node {
        Node leftChilder;
        Node rightChilder;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }

    public void mockBinTree() {
        nodeList = new ArrayList<Node>();

        for (int i = 0; i < array.length; i++) {
            nodeList.add(new Node(array[i]));
        }

        int lastNodeIndex = nodeList.size();

        // distinction child node
        for (int i = 0; i < lastNodeIndex / 2 - 1; i++) {
            nodeList.get(i).leftChilder = nodeList.get(i * 2 + 1);
            nodeList.get(i).rightChilder = nodeList.get(i * 2 + 2);
        }

    }

}
