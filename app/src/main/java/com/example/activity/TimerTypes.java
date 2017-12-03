package com.example.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.R;
import com.example.database.dao.TimerTypesDAO;
import com.example.database.model.TimerTypesModel;

import java.sql.SQLException;
import java.util.List;

public class TimerTypes extends AppCompatActivity{

    public static final String SELECTED_ITEM_ID = "id";
    public static final String SELECTED_ITEM = "name";

    public static final int MENU_ADD_TIMER = 0;
    public static final long TIMER_TYPES_ID_ALL = -1l;
    String timerTypesMenu[] = {""};
    ListView manualTimersList;
    private List<TimerTypesModel> timerTypesList;
    ListView prebuiltList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_types);
        setupViews();
        setupActionBar();
        setupPrebuiltTimers();
    }

    private void setupViews(){
        manualTimersList = (ListView) findViewById(R.id.manualTimerList);
        timerTypesMenu[MENU_ADD_TIMER] = getString(R.string.timer_types_menu);
        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,timerTypesMenu);
        manualTimersList.setAdapter(menuAdapter);
        manualTimersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == MENU_ADD_TIMER){
                    Intent intent = new Intent(TimerTypes.this,AddTimer.class);
                    startActivity(intent);
                }
            }
        });

        prebuiltList = (ListView) findViewById(R.id.prebuiltTimerTypesList);
    }

    private void setupPrebuiltTimers(){
        loadTimerTypesList();
        String timerTypeName[] = new String[timerTypesList.size()];
        for(int i=0;i<timerTypesList.size();++i){
            timerTypeName[i]=timerTypesList.get(i).getName();
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,timerTypeName);
        prebuiltList.setAdapter(adapter);
        prebuiltList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Bundle bundle = new Bundle();
                //bundle.putInt(SELECTED_ITEM_ID,i);
                //bundle.putString(SELECTED_ITEM,adapter.getItem(i));
                //startActivity(new Intent(TimerTypes.this,PrebuiltTimer.class).putExtras(bundle));
            }
        });
    }

    private void setupActionBar(){
        setTitle(getString(R.string.timer_types));
    }

    private void loadTimerTypesList()
    {
        try
        {
            timerTypesList = TimerTypesDAO.readAll(-1l, -1l);
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
