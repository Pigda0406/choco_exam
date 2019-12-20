package com.martin.choco;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import okhttp3.ResponseBody;

import com.google.gson.Gson;
import com.martin.choco.adapter.DramaAdapter;
import com.martin.choco.model.Drama;
import com.martin.choco.model.DramaResponse;
import com.martin.choco.netsubscribe.DramaSubscribe;
import com.martin.choco.netutils.OnSuccessAndFaultListener;
import com.martin.choco.netutils.OnSuccessAndFaultSub;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private DramaAdapter adapter;
    DramaResponse dramas;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        getDramaData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        final SharedPreferences pref = getSharedPreferences("search", MODE_PRIVATE);;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(adapter != null){
                    adapter.getFilter().filter(newText);
                }
                pref.edit().putString("search_text", newText).commit();
                return false;
            }
        });
        searchView.setQueryHint(pref.getString("search_text", ""));
        return true;
    }

    @Override
    public void supportInvalidateOptionsMenu() {
        super.supportInvalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void getDramaData() {

        DramaSubscribe.getDrama(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(ResponseBody body) throws IOException, JSONException {
                Gson gson = new Gson();
                String jsonString = body.string();
                JSONObject Jobject = new JSONObject(jsonString);
                dramas = new Gson().fromJson(Jobject.toString(), DramaResponse.class);
                initView();
            }

            @Override
            public void onFault(String errorMsg) {
                Toast.makeText(MainActivity.this, "Fail：" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }, MainActivity.this));
    }

    private void initView() {
        Uri uri = getIntent().getData();
        if (null == uri) {
            setUpRecyclerView(dramas.getData());
        } else {
            setUpRecyclerView(dramas.getData());
            parserUri(uri);
        }


    }

    private void parserUri(Uri uri) {
        //http://www.example.com/dramas/1
        String path = uri.getPath();
        for (int i = 0; i < dramas.getData().size(); i++) {
            if (path.contains(String.valueOf(dramas.getData().get(i).getDramaId()))) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Drama", dramas.getData().get(i));
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);
            }
        }
    }

    private void setUpRecyclerView(List<Drama> dramas) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new DramaAdapter(MainActivity.this, dramas);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}
