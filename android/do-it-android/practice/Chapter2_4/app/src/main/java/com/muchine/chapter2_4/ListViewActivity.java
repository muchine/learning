package com.muchine.chapter2_4;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.muchine.chapter2_4.ui.listview.IconTextItem;
import com.muchine.chapter2_4.ui.listview.IconTextListAdapter;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;

    private IconTextListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        adapter = new IconTextListAdapter(this);
        initItems(adapter);

        listView = (ListView) findViewById(R.id.listView01);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IconTextItem item = (IconTextItem) adapter.getItem(i);
                String[] data = item.getData();

                Toast.makeText(getApplicationContext(), "Selected : " + data[0], Toast.LENGTH_LONG).show();
            }
        });

    }

    private IconTextItem newItem(int iconId, String text1, String text2, String text3) {
        Resources resources = getResources();

        Drawable icon = resources.getDrawable(iconId);
        return new IconTextItem(icon, text1, text2, text3);
    }

    private void initItems(IconTextListAdapter adapter) {
        adapter.addItem(newItem(R.drawable.icon05, "추억의 테트리스", "30,000 다운로드", "900 원"));
        adapter.addItem(newItem(R.drawable.icon06, "고스톱 - 강호동 버전", "26,000 다운로드", "1500 원"));
        adapter.addItem(newItem(R.drawable.icon05, "친구찾기 (Friends Seeker)", "300,000 다운로드", "900 원"));
        adapter.addItem(newItem(R.drawable.icon05, "강좌 검색", "120,000 다운로드", "900 원"));
        adapter.addItem(newItem(R.drawable.icon05, "지하철 노선도 - 서울", "4,000 다운로드", "1500 원"));
        adapter.addItem(newItem(R.drawable.icon05, "지하철 노선도 - 도쿄", "6,000 다운로드", "1500 원"));
        adapter.addItem(newItem(R.drawable.icon05, "지하철 노선도 - LA", "8,000 다운로드", "1500 원"));
        adapter.addItem(newItem(R.drawable.icon05, "지하철 노선도 - 워싱턴", "7,000 다운로드", "1500 원"));
        adapter.addItem(newItem(R.drawable.icon05, "지하철 노선도 - 파리", "9,000 다운로드", "1500 원"));
        adapter.addItem(newItem(R.drawable.icon05, "지하철 노선도 - 베를린", "38,000 다운로드", "1500 원"));
    }
}
