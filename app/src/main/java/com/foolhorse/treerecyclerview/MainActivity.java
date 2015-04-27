package com.foolhorse.treerecyclerview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.foolhorse.treerecyclerview.bean.FileBean;
import com.foolhorse.treerecyclerview.node.Node;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FileTreeAdapter mTreeAdapter ;
    private List<FileBean> mDatas = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initView();
    }

    private void initView() {
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final FileBean file = (FileBean) v.getTag();

                final EditText et = new EditText(MainActivity.this );
                new AlertDialog.Builder(MainActivity.this )
                        .setTitle("Insert a FileBean")
                        .setView(et)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = et.getText().toString();
                                if (TextUtils.isEmpty(name)) {
                                    Toast.makeText(MainActivity.this, "file name can not be null",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                try {
                                    newFileBean(file, name);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        };


        try {
            mTreeAdapter = new FileTreeAdapter(this, mDatas ,1,onLongClickListener);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        rv.setAdapter(mTreeAdapter);
    }

    private void initDatas() {
        FileBean fileBean  ;
        fileBean = new FileBean(0,-1,"root node 0");
        mDatas.add(fileBean) ;
        fileBean = new FileBean(1,-1,"root node 1");
        mDatas.add(fileBean) ;
        fileBean = new FileBean(2,-1,"root node 2");
        mDatas.add(fileBean) ;
        fileBean = new FileBean(3,-1,"root node 3");
        mDatas.add(fileBean) ;
        fileBean = new FileBean(4,-1,"root node 4");
        mDatas.add(fileBean) ;
        fileBean = new FileBean(5,-1,"root node 5");
        mDatas.add(fileBean) ;
        fileBean = new FileBean(6,-1,"root node 6");
        mDatas.add(fileBean) ;
        fileBean = new FileBean(7,-1,"root node 7");
        mDatas.add(fileBean) ;
        fileBean = new FileBean(8,-1,"root node 8");
        mDatas.add(fileBean) ;
        fileBean = new FileBean(9,-1,"root node 9");
        mDatas.add(fileBean) ;
        fileBean = new FileBean(10,0,"node 0-0");
        mDatas.add(fileBean) ;
        fileBean = new FileBean(11,10,"node 0-0-0");
        mDatas.add(fileBean) ;
    }

    private void newFileBean(FileBean file, String name) throws IllegalAccessException {
        FileBean newFile = new FileBean((int)System.currentTimeMillis(), file.fileId, name);

        mTreeAdapter.addData(newFile);
    }


}
