package com.foolhorse.treerecyclerview.node;


import android.util.Log;

import com.foolhorse.treerecyclerview.annotation.NodeId;
import com.foolhorse.treerecyclerview.annotation.NodePid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NodeHelper {

    public static <T> Node converData2Node(T data) throws IllegalAccessException {
        Node node ;
        int id = -1;
        int pId = -1;

        Class clazz = data.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(NodeId.class) != null) {
                NodeId annotation = field.getAnnotation(NodeId.class);
//                    Class type = annotation.type();
//                    if (type==String.class) {
//                    }else if (type == Integer.class) {
//                    }

                if (field.getType() == int.class) {
                    Log.e("MARR", "  field.getType():::::int");
                } else if (field.getType() == Integer.class) {
                    Log.e("MARR", "  field.getType()::::::Integer");
                }

                field.setAccessible(true);
                id = field.getInt(data);
            }
            if (field.getAnnotation(NodePid.class) != null) {
                field.setAccessible(true);
                pId = field.getInt(data);
            }
        }
        node = new Node(id, pId);
        node.setObj(data);
        return node;
    }

    /**
     * set parent and children base on id and pId
     * @param nodes
     */
    public static void bindHierarchy(List<Node> nodes) {
        for (Node n :  nodes ) {
            for (Node m : nodes) {
                if (m.getpId() == n.getId() && (!n.getChildren().contains(m))) {
                    n.getChildren().add(m);
                } else if (m.getId() == n.getpId()) {
                    n.setParent(m);
                }
            }
        }
    }

    public static <T> List<Node> convertDatas2Nodes(List<T> datas) throws IllegalAccessException, IllegalArgumentException {
        List<Node> nodes = new ArrayList<>();
        Node node;
        for (T t : datas) {
            node = converData2Node(t);
            nodes.add(node);
        }
        return nodes;
    }

    public static List<Node> getSortedNodes(List<Node> nodes) throws IllegalAccessException, IllegalArgumentException {
        bindHierarchy(nodes);
        List<Node> rootNodes = getRootNodes(nodes);

        List<Node> result = new ArrayList<>();
        for (Node node : rootNodes) {
            addNode(result, node);
        }
        return result;
    }

    /**
     * add node and their chilren to result by recursion
     */
    private static void addNode(List<Node> result, Node node ) {
        result.add(node);
        if (node.isLeaf()) {
            return;
        }
        for ( Node child : node.getChildren()) {
            addNode(result, child);
        }
    }

    public static void setExpandeLevel(List<Node> list,int expandeLevel){
        for(Node node : list){
            if (expandeLevel >= 0) {
                if (node.getLevel() < expandeLevel) {
                    node.setExpend(true);
                } else {
                    node.setExpend(false);
                }
            }
        }
    }

    public static List<Node> fliterVisibleNodes(List<Node> nodes) {
        List<Node> result = new ArrayList<>();
        for (Node node : nodes) {
            if (node.isRoot() || node.isParentExpend()) {
                result.add(node);
            }
        }
        return result;
    }

    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<>();
        for (Node node : nodes) {
            if (node.isRoot()) {
                root.add(node);
            }
        }
        return root;
    }


}
