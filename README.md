# Android Tree RecyclerView

## Introduction

Android Tree RecyclerView by use a infinite hierarchy RecyclerView adapter ,  View hierarchy transformed by data hierarchy . Based on https://coding.net/u/sloop/p/TreeView/git .

## Usage

write a adapter extends TreeAdapter and implements abstract method in it, that's all.

```java
public class FileTreeAdapter extends TreeAdapter<FileBean> {

    private LayoutInflater mLayoutInflater ;

    private View.OnLongClickListener mOnLongClickListener ;

    public FileTreeAdapter(Context context, List<FileBean> datas, int defaultExpandLevel,View.OnLongClickListener onItemLongClickListener) throws IllegalAccessException, IllegalArgumentException {
        super(context, datas, defaultExpandLevel);
        mLayoutInflater = LayoutInflater.from(context) ;
        mOnLongClickListener = onItemLongClickListener ;
    }

    @Override
    public FileTreeViewHolder onCreateTreeViewHolder(ViewGroup viewGroup, int i) {
        return new FileTreeViewHolder(mLayoutInflater.inflate(R.layout.item ,viewGroup,false), mOnLongClickListener);
    }

    @Override
    public void onBindTreeViewHolder(RecyclerView.ViewHolder viewHolder, int position, FileBean obj, boolean isLeaf, boolean isExpend, int level) {
        ((FileTreeViewHolder)viewHolder).bindData(obj,isLeaf,isExpend,level) ;
    }

}

```



## Copyright

Copyright 2015, MaChao

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
