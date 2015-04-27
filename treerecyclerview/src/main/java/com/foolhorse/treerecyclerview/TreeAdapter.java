package com.foolhorse.treerecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foolhorse.treerecyclerview.node.Node;
import com.foolhorse.treerecyclerview.node.NodeHelper;

import java.util.List;

/**
 *
 * infinite hierarchy RecyclerView adapter
 *
 * Created by ID_MARR on 2015/4/26.
 */
public abstract class TreeAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected List<T> mDatas ;
    protected List<Node> mAllNodes;
    protected List<Node> mVisibleNodes;

    public TreeAdapter(Context context, List<T> datas, int defaultExpandLevel) throws IllegalAccessException, IllegalArgumentException {
        mContext = context;
        mDatas = datas ;
        sort(defaultExpandLevel);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int position) {
        return onCreateTreeViewHolder(viewGroup, position);
    }

    public abstract RecyclerView.ViewHolder onCreateTreeViewHolder(ViewGroup viewGroup, int position);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        onBindTreeViewHolder(viewHolder, position, (T)mVisibleNodes.get(position).getObj(),mVisibleNodes.get(position).isLeaf(),mVisibleNodes.get(position).isExpend(),mVisibleNodes.get(position).getLevel());
        // ugly
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandOrCollapse(position);
            }
        });
    }

    public abstract void onBindTreeViewHolder(RecyclerView.ViewHolder viewHolder, int position, T obj,boolean isLeaf, boolean isExpend, int level);


    @Override
    public int getItemCount() {
        return mVisibleNodes.size();
    }

    public void sort() throws IllegalAccessException {
        sort(-1);
    }

    public void sort(int defaultExpandLevel) throws IllegalAccessException, IllegalArgumentException{
        List<Node> unsortedNodes = NodeHelper.convertDatas2Nodes(mDatas);

        mAllNodes = NodeHelper.getSortedNodes(unsortedNodes);
        NodeHelper.setExpandeLevel(mAllNodes, defaultExpandLevel);
        mVisibleNodes = NodeHelper.fliterVisibleNodes(mAllNodes);
        notifyDataSetChanged();
    }

    public void addData(T t) throws IllegalAccessException {
        mDatas.add(t);

        Node node = NodeHelper.converData2Node(t) ;
        mAllNodes.add(node);

        mAllNodes = NodeHelper.getSortedNodes(mAllNodes);
        node.getParent().setExpend(true);
        mVisibleNodes = NodeHelper.fliterVisibleNodes(mAllNodes);
        notifyDataSetChanged();
    }

    protected void expandOrCollapse(int position) {
        Node node = mVisibleNodes.get(position);
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            return;
        }
        node.setExpend(!node.isExpend());
        mVisibleNodes = NodeHelper.fliterVisibleNodes(mAllNodes);
        notifyDataSetChanged();
    }

}