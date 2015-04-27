package com.foolhorse.treerecyclerview.bean;

import com.foolhorse.treerecyclerview.annotation.NodeId;
import com.foolhorse.treerecyclerview.annotation.NodePid;

/**
 * Created by ID_MARR on 2015/4/26.
 */
public class FileBean {

    @NodeId
    public int fileId;
    @NodePid
    public int filePId;

    public String fileName;

    public FileBean(int fileId, int filePId, String fileName) {
        this.fileId = fileId;
        this.filePId = filePId;
        this.fileName = fileName;
    }
}
