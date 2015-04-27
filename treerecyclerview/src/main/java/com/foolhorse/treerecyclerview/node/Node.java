package com.foolhorse.treerecyclerview.node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ID_MARR on 2015/4/26.
 */
public class Node {

    private int id;
    /**
     * parent id
     * root node 's pId=0
     */
    private int pId;

    private boolean isExpend = false;

    private Node parent;

    private List<Node> children = new ArrayList<Node>();
    /**
     * data
     */
    private Object obj ;

    public Node(int id, int pId ) {
        this.id = id;
        this.pId = pId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getpId() {
        return pId;
    }
    public void setpId(int pId) {
        this.pId = pId;
    }

    /**
     * get hierarchy level
     */
    public int getLevel() {
        return isRoot() ? 0 : parent.getLevel() + 1;
    }

    public boolean isExpend() {
        return isExpend;
    }


    public void setExpend(boolean isExpend) {
        this.isExpend = isExpend;
        if (!isExpend) { // 收缩子节点
            for (Node node : children) {
                node.setExpend(false);
            }
        }
    }


    public Node getParent() {
        return parent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public List<Node> getChildren() {
        return children;
    }
    public void setChildren(List<Node> children) {
        this.children = children;
    }
    public Object getObj(){
        return obj ;
    }
    public void setObj(Object obj){
        this.obj = obj ;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isParentExpend() {
        if (isRoot() ){
            return false;
        }
        return parent.isExpend();
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

}
