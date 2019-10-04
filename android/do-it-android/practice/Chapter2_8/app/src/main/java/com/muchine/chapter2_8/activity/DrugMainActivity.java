package com.muchine.chapter2_8.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.muchine.chapter2_8.R;
import com.muchine.chapter2_8.model.drug.Drug;
import com.muchine.chapter2_8.repo.DrugRepository;
import com.muchine.chapter2_8.view.drug.DrugAdapter;
import com.muchine.chapter2_8.view.drug.DrugItem;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrugMainActivity extends AppCompatActivity {

    @Bind(R.id.drugQuery)
    EditText query;

    private final DrugRepository repo = new DrugRepository();

    private DrugAdapter adapter;

    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugview);
        ButterKnife.bind(this);

        this.adapter = new DrugAdapter(this);
        this.inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        initSearchButton();
        initDrugList();
    }

    private void initSearchButton() {
        Button button = (Button) findViewById(R.id.drugSearchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Drug> drugs = repo.search(query.getText().toString());
                adapter.reset(drugs);
                adapter.notifyDataSetChanged();

                inputMethodManager.hideSoftInputFromWindow(query.getWindowToken(), 0);
            }
        });
    }

    private void initDrugList() {
        ListView view = new ListView(this);
        view.setAdapter(adapter);

        LinearLayout drugListLayout = (LinearLayout) findViewById(R.id.drugList);
        drugListLayout.addView(view);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrugItem item = (DrugItem) adapter.getItem(i);
                startDetailActivity(item.getCode());
            }
        });
    }

    private void startDetailActivity(String drugCode) {
        Bundle bundle = new Bundle();
        bundle.putString(DrugDetailActivity.DRUG_CODE, drugCode);

        Intent intent = new Intent(this, DrugDetailActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
