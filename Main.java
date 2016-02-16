package com.echo.primestudio.dota2knowthyheroes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Main extends ActionBarActivity {

    private ViewPager pager ;
    private SlidingTabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        tabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        tabLayout.setViewPager(pager);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    //Pager Adapter
    class pagerAdapter extends FragmentPagerAdapter{

        String[] tabs;
        public pagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.type);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public Fragment getItem(int position) {
            typeFragement fragement = typeFragement.getInstance(position);
            return fragement;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public static class typeFragement extends Fragment{

        private ListView heroesLV ;

        public static typeFragement getInstance(int position){
            typeFragement fragment = new typeFragement();
            Bundle args = new Bundle();
            args.putInt("type",position);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.lists,container,false);
            heroesLV = (ListView) layout.findViewById(R.id.heroesList);
            Bundle bundle = getArguments();

            if(bundle != null){

                switch (bundle.getInt("type")){
                    case 0:
                            ArrayAdapter<String> strengthAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.strength_heroes));
                            heroesLV.setAdapter(strengthAdapter);
                            break;
                    case 1:
                        ArrayAdapter<String> agilityAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.agility_heroes));
                        heroesLV.setAdapter(agilityAdapter);
                        break;
                    case 2:
                        ArrayAdapter<String> intelligenceAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.intelligence_heroes));
                        heroesLV.setAdapter(intelligenceAdapter);
                        break;
                }

            }

            return layout;
        }
    }

}
