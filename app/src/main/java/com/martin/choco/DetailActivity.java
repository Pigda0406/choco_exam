package com.martin.choco;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.martin.choco.adapter.DramaAdapter;
import com.martin.choco.model.Drama;


public class DetailActivity extends AppCompatActivity {
  Drama drama;

  private DramaAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_drama_detail);
      initData();
      initView();
  }

    private void initData() {
      Bundle bundle = getIntent().getExtras();
      drama = bundle.getParcelable("Drama");
    }

    private void initView() {

      Glide.with(this).load(drama.getThumb()
      ).into((ImageView) findViewById(R.id.imageview_poster));
      ((TextView)findViewById(R.id.textview_drama_title)).setText(drama.getName());
      ((TextView)findViewById(R.id.textview_drama_time)).setText(drama.getCreatedAt());
      ((TextView)findViewById(R.id.textview_rating)).setText(String.valueOf(drama.getRating()));
      ((TextView)findViewById(R.id.textview_viewer)).setText(String.valueOf(drama.getTotalViews()));

    }



}
