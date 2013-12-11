package com.sdk.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.mobeta.android.demodslv.R;

import java.util.ArrayList;

/**
 * Created by wb-wangxiaolong on 13-12-10.
 */
public class DragGridViewActivity extends Activity implements AdapterView.OnItemLongClickListener{

    private DragGridView dragGridView = null;
    private DragGridViewAdapter dragGridViewAdapter;
    private DragGridView.DropListener onDrop =
            new DragGridView.DropListener() {
                @Override
                public void drop(int from, int to) {
                    Object item = dragGridViewAdapter.getItem(from);

                    dragGridViewAdapter.remove(item);
                    dragGridViewAdapter.insert(item, to);
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag_grid_view_main);

        dragGridView = (DragGridView) findViewById(R.id.drag_grid_view);
        initData();
    }

    private void initData() {
        ArrayList<Object> data = new ArrayList<Object>();
        data.add("AAA");
        data.add("BBB");
        data.add("CCC");
        data.add("DDD");
        data.add("EEE");
        data.add("GGG");
        data.add("HHH");
        data.add("III");
        data.add("JJJ");
        data.add("KKK");
        data.add("lll");
        data.add("MMM");
        data.add("NNN");
        data.add("OOO");
        dragGridViewAdapter = new DragGridViewAdapter(this,0,data);
        dragGridView.setAdapter(dragGridViewAdapter);
        dragGridView.setDropListener(onDrop);
        dragGridView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        view.setVisibility(View.INVISIBLE);
        dragGridView.setDragEnabled(true);
        return true;
    }
}
