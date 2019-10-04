package com.muchine.chapter2_4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.muchine.chapter2_4.ui.gridview.ArrayTable;
import com.muchine.chapter2_4.ui.gridview.DataAdapter;

public class GridViewActivity extends AppCompatActivity {

    private GridView gridView;

    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        getSupportActionBar().hide();

        adapter = new DataAdapter(this, initSampleTable());

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setNumColumns(adapter.getNumColumns());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int rowIndex = i / adapter.getTable().getColumnCount();

                String message = "Selected position: " + i + ", rowIndex: " + rowIndex;
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                adapter.setSelectedRow(rowIndex);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private ArrayTable initSampleTable() {
        ArrayTable table = new ArrayTable(4, 3);

        table.setCell(0, 0, "Name");
        table.setCell(0, 1, "Address");
        table.setCell(0, 2, "Group");

        table.setCell(1, 0, "Mike");
        table.setCell(1, 1, "Seoul");
        table.setCell(1, 2, "Friends");

        table.setCell(2, 0, "Ginnie");
        table.setCell(2, 1, "Busan");
        table.setCell(2, 2, "Friends");

        table.setCell(3, 0, "John");
        table.setCell(3, 1, "Daejeon");
        table.setCell(3, 2, "Family");

        return table;
    }

}
