package com.muchine.chapter2_8.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.muchine.chapter2_8.R;
import com.muchine.chapter2_8.model.drug.DrugContent;
import com.muchine.chapter2_8.repo.DrugRepository;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrugDetailActivity extends AppCompatActivity {

    public static final String DRUG_CODE = "drugCode";

    @Bind(R.id.drugContent)
    TextView drugContent;

    private final DrugRepository repo = new DrugRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugdetail);
        ButterKnife.bind(this);

        initContent();
    }

    private void initContent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String drugCode = bundle.getString(DRUG_CODE);
        List<DrugContent> contents = repo.getContents(drugCode);

        render(contents);
    }

    private void render(List<DrugContent> contents) {
        for (DrugContent content : contents) {
            drugContent.append("\n");
            drugContent.append("[" + content.getClassName() + "]\n");
            drugContent.append("\n");
            drugContent.append(content.getDetail() + "\n\n");
            drugContent.append("----------------------------------------------");
            drugContent.append("\n");
        }
    }

}
