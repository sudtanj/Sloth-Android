package com.example.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.R;
import com.example.database.dao.TimerDAO;
import com.example.database.dao.TimerTypesDAO;
import com.example.database.model.TimerModel;
import com.example.database.model.TimerTypesModel;

import java.sql.SQLException;
import java.util.List;

public class PrebuiltTimer extends AppCompatActivity {


    private List<TimerModel> timerList;
    ListView timerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prebuilt_timer);
        setupActionBar();
        setupViews();
    }

    private void setupViews(){
        loadTimerList();
        timerListView = (ListView) findViewById(R.id.prebuiltTimerList);
        String timerName[] = new String[timerList.size()];
        for(int i=0;i<timerList.size();++i){
            timerName[i]=timerList.get(i).getName();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,timerName);
        timerListView.setAdapter(arrayAdapter);
        timerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void setupActionBar(){
        setTitle(getIntent().getExtras().getString(TimerTypes.SELECTED_ITEM));
    }

    private void loadTimerList()
    {
        try
        {
            //timerList = TimerDAO.readByTimerTypes(getIntent().getExtras().getInt(TimerTypes.SELECTED_ITEM),0,128);
            timerList = TimerDAO.readAll(0,128);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        /*TimerTypesModel all = new TimerTypesModel();
        all.setId(CATEGORY_ID_ALL);
        all.setName(getResources().getString(R.string.drawer_category_all));

        CategoryModel favorites = new CategoryModel();
        favorites.setId(RecipeListFragment.CATEGORY_ID_FAVORITES);
        favorites.setName(getResources().getString(R.string.drawer_category_favorites));
        favorites.setImage("drawable://" + R.drawable.ic_category_favorites);

        timerTypesList.add(0, all);
        timerTypesList.add(1, favorites);*/
    }
}
