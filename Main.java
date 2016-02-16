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
import java.util.Arrays;


public class Main extends ActionBarActivity {

    private ViewPager pager;
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
    class pagerAdapter extends FragmentPagerAdapter {

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

    public static class typeFragement extends Fragment {

        private ListView heroesLV;

        public static typeFragement getInstance(int position) {
            typeFragement fragment = new typeFragement();
            Bundle args = new Bundle();
            args.putInt("type", position);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.lists, container, false);
            heroesLV = (ListView) layout.findViewById(R.id.heroesList);
            Bundle bundle = getArguments();

            if (bundle != null) {

                switch (bundle.getInt("type")) {
                    case 0:
                        String[] sHeroes = getResources().getStringArray(R.array.strength_heroes);
                        int[] sIcons = {R.drawable.abaddon_icon,
                        R.drawable.alchemist_icon,
                        R.drawable.axe_icon,
                        R.drawable.beastmaster_icon,
                        R.drawable.brewmaster_icon,
                        R.drawable.bristleback_icon,
                        R.drawable.centaur_warrunner_icon,
                        R.drawable.chaos_knight_icon,
                        R.drawable.clockwerk_icon,
                        R.drawable.doom_bringer_icon,
                        R.drawable.dragon_knight_icon,
                        R.drawable.earthshaker_icon,
                        R.drawable.earth_spirit_icon,
                        R.drawable.elder_titan_icon,
                        R.drawable.huskar_icon,
                        R.drawable.io_icon,
                        R.drawable.kunkka_icon,
                        R.drawable.legion_commander_icon,
                        R.drawable.lifestealer_icon,
                        R.drawable.lycan_icon,
                        R.drawable.magnus_icon,
                        R.drawable.night_stalker_icon,
                        R.drawable.omniknight_icon,
                        R.drawable.phoenix_icon,
                        R.drawable.pudge_icon,
                        R.drawable.sand_king_icon,
                        R.drawable.slardar_icon,
                        R.drawable.spirit_breaker_icon,
                        R.drawable.sven_icon,
                        R.drawable.tidehunter_icon,
                        R.drawable.timbersaw_icon,
                        R.drawable.tiny_icon,
                        R.drawable.treant_protector_icon,
                        R.drawable.tusk_icon,
                        R.drawable.undying_icon,
                        R.drawable.wraith_king_icon};

                        heroesAdapter sAdapter = new heroesAdapter(getContext(), sHeroes, sIcons);
                        heroesLV.setAdapter(sAdapter);
                        break;
                    case 1:
                        String[] aHeroes = getResources().getStringArray(R.array.agility_heroes);
                        int[] aIcons = {R.drawable.anti_mage_icon,
                        R.drawable.arc_warden_icon,
                        R.drawable.bloodseeker_icon,
                        R.drawable.broodmother_icon,
                        R.drawable.bounty_hunter_icon,
                        R.drawable.clinkz_icon,
                        R.drawable.drow_ranger_icon,
                        R.drawable.ember_spirit_icon,
                        R.drawable.faceless_void_icon,
                        R.drawable.gyrocopter_icon,
                        R.drawable.juggernaut_icon,
                        R.drawable.lone_druid_icon,
                        R.drawable.luna_icon,
                        R.drawable.medusa_icon,
                        R.drawable.meepo_icon,
                        R.drawable.mirana_icon,
                        R.drawable.morphling_icon,
                        R.drawable.naga_siren_icon,
                        R.drawable.nyx_assassin_icon,
                        R.drawable.phantom_assassin_icon,
                        R.drawable.phantom_lancer_icon,
                        R.drawable.razor_icon,
                        R.drawable.riki_icon,
                        R.drawable.shadow_fiend_icon,
                        R.drawable.slark_icon,
                        R.drawable.sniper_icon,
                        R.drawable.spectre_icon,
                        R.drawable.templar_assassin_icon,
                        R.drawable.terrorblade_icon,
                        R.drawable.troll_warlord_icon,
                        R.drawable.ursa_icon,
                        R.drawable.vengeful_spirit_icon,
                        R.drawable.venomancer_icon,
                        R.drawable.viper_icon,
                        R.drawable.weaver_icon};

                        heroesAdapter aAdapter = new heroesAdapter(getContext(), aHeroes, aIcons);
                        heroesLV.setAdapter(aAdapter);
                        break;
                    case 2:
                        String[] iHeroes = getResources().getStringArray(R.array.intelligence_heroes);
                        int[] iIcons = {R.drawable.ancient_apparition_icon,
                        R.drawable.bane_icon,
                        R.drawable.batrider_icon,
                        R.drawable.chen_icon,
                        R.drawable.crystal_maiden_icon,
                        R.drawable.dark_seer_icon,
                        R.drawable.dazzle_icon,
                        R.drawable.death_prophet_icon,
                        R.drawable.disruptor_icon,
                        R.drawable.enchantress_icon,
                        R.drawable.enigma_icon,
                        R.drawable.invoker_icon,
                        R.drawable.jakiro_icon,
                        R.drawable.keeper_of_the_light_icon,
                        R.drawable.leshrac_icon,
                        R.drawable.lich_icon,
                        R.drawable.lina_icon,
                        R.drawable.lion_icon,
                        R.drawable.natures_prophet_icon,
                        R.drawable.necrophos_icon,
                        R.drawable.ogre_magi_icon,
                        R.drawable.oracle_icon,
                        R.drawable.outworld_devourer_icon,
                        R.drawable.puck_icon,
                        R.drawable.pugna_icon,
                        R.drawable.queen_of_pain_icon,
                        R.drawable.rubick_icon,
                        R.drawable.silencer_icon,
                        R.drawable.storm_spirit_icon,
                        R.drawable.shadow_demon_icon,
                        R.drawable.shadow_shaman_icon,
                        R.drawable.tinker_icon,
                        R.drawable.warlock_icon,
                        R.drawable.windranger_icon,
                        R.drawable.winter_wyvern_icon,
                        R.drawable.witch_doctor_icon,
                        R.drawable.zeus_icon};

                        heroesAdapter iAdapter = new heroesAdapter(getContext(), iHeroes, iIcons);
                        heroesLV.setAdapter(iAdapter);
                        break;

                }

            }

            return layout;
        }
    }

}
