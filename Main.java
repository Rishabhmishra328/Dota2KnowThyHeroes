package com.echo.primestudio.dota2knowthyheroes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Arrays;

public class Main extends ActionBarActivity {

    private ViewPager pager;
    public static SlidingTabLayout tabLayout;
    public static ScrollView heroPreview;

    public static View skillTemplate;

    public static String prevHero;

    public static String[] skillDesc, skillSpec, skillNames;
    public static int[] skillIcons;

    public static TextView heroLore;
    public static ImageView heroImage;
//    public static ListView skillLV;

    public static Context applicationContext;

    public static LinearLayout heroIntro;

    public static Resources appResource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        //Declaration
        heroPreview = (ScrollView) findViewById(R.id.hero_preview);
        heroLore = (TextView) findViewById(R.id.hero_lore);
        heroImage = (ImageView) findViewById(R.id.hero_image);
        heroIntro = (LinearLayout) findViewById(R.id.hero_introduction);

        if (skillTemplate == null) {
            Log.d("SKILLTEMPLATE", " FROM MAIN IS NULL");
        }

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        tabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        tabLayout.setViewPager(pager);

        //Setting Context
        Context appContext = getApplicationContext();
        applicationContext = appContext;

        //Setting resources
        Resources res = getResources();
        appResource = res;


    }

    @Override
    public void onBackPressed() {
        if (isPanelShown()) {
            slideIn();
        } else
            super.onBackPressed();
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

    public static void tabsSlideIn() {
        Log.d("TABS", "IN SLIDE IN");
        if (isTypeShown()) {
            // Hide the panel
            Animation topOut = AnimationUtils.loadAnimation(applicationContext,
                    R.anim.top_out);
            tabLayout.startAnimation(topOut);
            tabLayout.setVisibility(View.GONE);
        } else {
            // Show the Panel
//            Animation rightOut = AnimationUtils.loadAnimation(applicationContext,
//                    R.anim.right_out);
//
//            tabLayout.startAnimation(rightOut);
            tabLayout.setVisibility(View.VISIBLE);
        }
    }


    private static boolean isTypeShown() {
        return tabLayout.getVisibility() == View.VISIBLE;
    }

    public static void slideIn() {
        if (!isPanelShown()) {
            // Show the panel
            Animation rightIn = AnimationUtils.loadAnimation(applicationContext,
                    R.anim.right_in);

            heroPreview.startAnimation(rightIn);
            tabsSlideIn();
            heroPreview.setVisibility(View.VISIBLE);
        } else {
            // Hide the Panel
            Animation rightOut = AnimationUtils.loadAnimation(applicationContext,
                    R.anim.right_out);

            heroPreview.startAnimation(rightOut);
            tabLayout.setVisibility(View.VISIBLE);
            heroPreview.setVisibility(View.GONE);
        }
    }


    private static boolean isPanelShown() {
        return heroPreview.getVisibility() == View.VISIBLE;
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

        public static ListView heroesLV;

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

            typeFragement.heroesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    slideIn();


                    TextView heroName = (TextView) view.findViewById(R.id.hero_name);
                    String hero = (String) heroName.getText();

                    if (prevHero != hero) {
                        heroPreview.scrollTo(0, 0);
                    }

                    prevHero = hero;

                    getHeroDetails(hero);

                    for (int i = 0; i < skillNames.length; i++) {

                        if (skillTemplate == null) {

                            Log.d("SKILLTEMPLATE", " IS NULL");
                        }

                        LayoutInflater layoutInflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        skillTemplate = layoutInflater.inflate(R.layout.skill_template, heroIntro, false);

                        ImageView skillIV = (ImageView) skillTemplate.findViewById(R.id.skill_image);
                        TextView skillNameTV = (TextView) skillTemplate.findViewById(R.id.skill_name);
                        TextView skillDesTV = (TextView) skillTemplate.findViewById(R.id.skill_description);
                        TextView skillSpecTV = (TextView) skillTemplate.findViewById(R.id.skill_specifications);

                        skillIV.setImageResource(skillIcons[i]);
                        skillNameTV.setText(skillNames[i]);
                        skillDesTV.setText(skillDesc[i]);
                        skillSpecTV.setText(skillSpec[i]);

                        skillDesTV.setTextColor(Color.DKGRAY);
                        skillNameTV.setTextColor(Color.DKGRAY);
                        skillSpecTV.setTextColor(Color.DKGRAY);

                        heroIntro.addView(skillTemplate);

                    }

                }
            });

            return layout;
        }
    }

    public static void getHeroDetails(String hero) {

        heroIntro.removeAllViewsInLayout();

        switch (hero) {
            case "Ancient Apparition":
                heroImage.setImageResource(R.drawable.ancient_apparition);
                heroLore.setText(appResource.getString(R.string.ancient_apparition_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.cold_feet_icon, R.drawable.ice_vortex_icon, R.drawable.chilling_touch_icon, R.drawable.ice_blast_icon, R.drawable.release_icon};
                skillNames = appResource.getStringArray(R.array.ancient_apparition_skill_names);
                skillDesc = appResource.getStringArray(R.array.ancient_apparition_skill_description);
                skillSpec = appResource.getStringArray(R.array.ancient_apparition_skills_specifications);
                break;
            case "Bane":
                heroImage.setImageResource(R.drawable.bane);
                heroLore.setText(appResource.getString(R.string.bane_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.enfeeble_icon, R.drawable.brain_sap_icon, R.drawable.nightmare_icon, R.drawable.fiends_grip_icon, R.drawable.nightmare_end_icon};
                skillNames = appResource.getStringArray(R.array.bane_skill_names);
                skillDesc = appResource.getStringArray(R.array.bane_skill_description);
                skillSpec = appResource.getStringArray(R.array.bane_skills_specifications);
                break;
            case "Batrider":
                heroImage.setImageResource(R.drawable.batrider);
                heroLore.setText(appResource.getString(R.string.batrider_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.sticky_napalm_icon, R.drawable.flamebreak_icon, R.drawable.firefly_icon, R.drawable.flaming_lasso_icon};
                skillNames = appResource.getStringArray(R.array.batrider_skill_names);
                skillDesc = appResource.getStringArray(R.array.batrider_skill_description);
                skillSpec = appResource.getStringArray(R.array.batrider_skills_specifications);
                break;
            case "Chen":
                heroImage.setImageResource(R.drawable.chen);
                heroLore.setText(appResource.getString(R.string.chen_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.arcane_orb_icon, R.drawable.astral_imprisonment_icon, R.drawable.essence_aura_icon, R.drawable.sanitys_eclipse_icon};
                skillNames = appResource.getStringArray(R.array.outworld_devourer_skill_names);
                skillDesc = appResource.getStringArray(R.array.outworld_devourer_skill_description);
                skillSpec = appResource.getStringArray(R.array.outworld_devourer_skills_specifications);
                break;
            case "Crystal Maiden":
                heroImage.setImageResource(R.drawable.crystal_maiden);
                heroLore.setText(appResource.getString(R.string.crystal_maiden_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.crystal_nova_icon, R.drawable.frostbite_icon, R.drawable.arcane_aura_icon, R.drawable.freezing_field_icon};
                skillNames = appResource.getStringArray(R.array.crystal_maiden_skill_names);
                skillDesc = appResource.getStringArray(R.array.crystal_maiden_skill_description);
                skillSpec = appResource.getStringArray(R.array.crystal_maiden_skills_specifications);
                break;
            case "Dark Seer":
                heroImage.setImageResource(R.drawable.dark_seer);
                heroLore.setText(appResource.getString(R.string.dark_seer_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.vacuum_icon, R.drawable.ion_shell_icon, R.drawable.surge_icon, R.drawable.wall_of_replica_icon};
                skillNames = appResource.getStringArray(R.array.dark_seer_skill_names);
                skillDesc = appResource.getStringArray(R.array.dark_seer_skill_description);
                skillSpec = appResource.getStringArray(R.array.dark_seer_skills_specifications);
                break;
            case "Dazzle":
                heroImage.setImageResource(R.drawable.dazzle);
                heroLore.setText(appResource.getString(R.string.dazzle_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.poison_touch_icon, R.drawable.shallow_grave_icon, R.drawable.shadow_wave_icon, R.drawable.weave_icon};
                skillNames = appResource.getStringArray(R.array.dazzle_skill_names);
                skillDesc = appResource.getStringArray(R.array.dazzle_skill_description);
                skillSpec = appResource.getStringArray(R.array.dazzle_skills_specifications);
                break;
            case "Death Prophet":
                heroImage.setImageResource(R.drawable.death_prophet);
                heroLore.setText(appResource.getString(R.string.death_prophet_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.crypt_swarm_icon, R.drawable.silence_icon, R.drawable.spirit_siphon_icon, R.drawable.exorcism_icon};
                skillNames = appResource.getStringArray(R.array.death_prophet_skill_names);
                skillDesc = appResource.getStringArray(R.array.death_prophet_skill_description);
                skillSpec = appResource.getStringArray(R.array.death_prophet_skills_specifications);
                break;
            case "Disruptor":
                heroImage.setImageResource(R.drawable.disruptor);
                heroLore.setText(appResource.getString(R.string.disruptor_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.thunder_strike_icon, R.drawable.glimpse_icon, R.drawable.kinetic_field_icon, R.drawable.static_storm_icon};
                skillNames = appResource.getStringArray(R.array.disruptor_skill_names);
                skillDesc = appResource.getStringArray(R.array.disruptor_skill_description);
                skillSpec = appResource.getStringArray(R.array.disruptor_skills_specifications);
                break;
            case "Enchantress":
                heroImage.setImageResource(R.drawable.enchantress);
                heroLore.setText(appResource.getString(R.string.enchantress_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.untouchable_icon, R.drawable.enchant_icon, R.drawable.natures_attendants_icon, R.drawable.impetus_icon};
                skillNames = appResource.getStringArray(R.array.enchantress_skill_names);
                skillDesc = appResource.getStringArray(R.array.enchantress_skill_description);
                skillSpec = appResource.getStringArray(R.array.enchantress_skills_specifications);
                break;
            case "Enigma":
                heroImage.setImageResource(R.drawable.enigma);
                heroLore.setText(appResource.getString(R.string.enigma_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.malefice_icon, R.drawable.demonic_conversion_icon, R.drawable.midnight_pulse_icon, R.drawable.black_hole_icon};
                skillNames = appResource.getStringArray(R.array.enigma_skill_names);
                skillDesc = appResource.getStringArray(R.array.enigma_skill_description);
                skillSpec = appResource.getStringArray(R.array.enigma_skills_specifications);
                break;
            case "Invoker":
                heroImage.setImageResource(R.drawable.invoker);
                heroLore.setText(appResource.getString(R.string.invoker_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.quas_icon, R.drawable.wex_icon, R.drawable.exort_icon, R.drawable.invoke_icon, R.drawable.cold_snap_icon, R.drawable.ghost_walk_icon, R.drawable.tornado_icon, R.drawable.emp_icon, R.drawable.alacrity_icon, R.drawable.chaos_meteor_icon, R.drawable.sun_strike_icon, R.drawable.forge_spirit_icon, R.drawable.ice_wall_icon, R.drawable.deafening_blast_icon};
                skillNames = appResource.getStringArray(R.array.invoker_skill_names);
                skillDesc = appResource.getStringArray(R.array.invoker_skill_description);
                skillSpec = appResource.getStringArray(R.array.invoker_skills_specifications);
                break;
            case "Jakiro":
                heroImage.setImageResource(R.drawable.jakiro);
                heroLore.setText(appResource.getString(R.string.jakiro_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.dual_breath_icon, R.drawable.ice_path_icon, R.drawable.liquid_fire_icon, R.drawable.macropyre_icon};
                skillNames = appResource.getStringArray(R.array.jakiro_skill_names);
                skillDesc = appResource.getStringArray(R.array.jakiro_skill_description);
                skillSpec = appResource.getStringArray(R.array.jakiro_skills_specifications);
                break;
            case "Keeper of the Light":
                heroImage.setImageResource(R.drawable.keeper_of_the_light);
                heroLore.setText(appResource.getString(R.string.keeper_of_the_light_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.illuminate_icon, R.drawable.mana_leak_icon, R.drawable.chakra_magic_icon, R.drawable.recall_icon, R.drawable.blinding_light_icon, R.drawable.spirit_form_icon, R.drawable.release_illuminate_icon, R.drawable.illuminate_spirit_form_icon};
                skillNames = appResource.getStringArray(R.array.keeper_of_the_light_skill_names);
                skillDesc = appResource.getStringArray(R.array.keeper_of_the_light_skill_description);
                skillSpec = appResource.getStringArray(R.array.keeper_of_the_light_skills_specifications);
                break;
            case "Leshrac":
                heroImage.setImageResource(R.drawable.leshrac);
                heroLore.setText(appResource.getString(R.string.leshrac_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.split_earth_icon, R.drawable.diabolic_edict_icon, R.drawable.lightning_storm_icon, R.drawable.pulse_nova_icon};
                skillNames = appResource.getStringArray(R.array.leshrac_skill_names);
                skillDesc = appResource.getStringArray(R.array.leshrac_skill_description);
                skillSpec = appResource.getStringArray(R.array.leshrac_skills_specifications);
                break;
            case "Lich":
                heroImage.setImageResource(R.drawable.lich);
                heroLore.setText(appResource.getString(R.string.lich_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.frost_blast_icon, R.drawable.ice_armor_icon, R.drawable.sacrifice_icon, R.drawable.chain_frost_icon};
                skillNames = appResource.getStringArray(R.array.lich_skill_names);
                skillDesc = appResource.getStringArray(R.array.lich_skill_description);
                skillSpec = appResource.getStringArray(R.array.lich_skills_specifications);
                break;
            case "Lina":
                heroImage.setImageResource(R.drawable.lina);
                heroLore.setText(appResource.getString(R.string.lina_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.dragon_slave_icon, R.drawable.light_strike_array_icon, R.drawable.fiery_soul_icon, R.drawable.laguna_blade_icon};
                skillNames = appResource.getStringArray(R.array.lina_skill_names);
                skillDesc = appResource.getStringArray(R.array.lina_skill_description);
                skillSpec = appResource.getStringArray(R.array.lina_skills_specifications);
                break;
            case "Lion":
                heroImage.setImageResource(R.drawable.lion);
                heroLore.setText(appResource.getString(R.string.lion_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.earth_spike_icon, R.drawable.hex_lion_icon, R.drawable.mana_drain_icon, R.drawable.finger_of_death_icon};
                skillNames = appResource.getStringArray(R.array.lion_skill_names);
                skillDesc = appResource.getStringArray(R.array.lion_skill_description);
                skillSpec = appResource.getStringArray(R.array.lion_skills_specifications);
                break;
            case "Natures Prophet":
                heroImage.setImageResource(R.drawable.natures_prophet);
                heroLore.setText(appResource.getString(R.string.natures_prophet_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.sprout_icon, R.drawable.teleportation_icon, R.drawable.natures_call_icon, R.drawable.wrath_of_nature_icon};
                skillNames = appResource.getStringArray(R.array.natures_prophet_skill_names);
                skillDesc = appResource.getStringArray(R.array.natures_prophet_skill_description);
                skillSpec = appResource.getStringArray(R.array.natures_prophet_skills_specifications);
                break;
            case "Necrophos":
                heroImage.setImageResource(R.drawable.necrophos);
                heroLore.setText(appResource.getString(R.string.necrophos_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.death_pulse_icon, R.drawable.heartstopper_aura_icon, R.drawable.sadist_icon, R.drawable.reapers_scythe_icon};
                skillNames = appResource.getStringArray(R.array.necrophos_skill_names);
                skillDesc = appResource.getStringArray(R.array.necrophos_skill_description);
                skillSpec = appResource.getStringArray(R.array.necrophos_skills_specifications);
                break;
            case "Ogre Magi":
                heroImage.setImageResource(R.drawable.ogre_magi);
                heroLore.setText(appResource.getString(R.string.ogre_magi_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.fireblast_icon, R.drawable.ignite_icon, R.drawable.bloodlust_icon, R.drawable.multicast_icon};
                skillNames = appResource.getStringArray(R.array.ogre_magi_skill_names);
                skillDesc = appResource.getStringArray(R.array.ogre_magi_skill_description);
                skillSpec = appResource.getStringArray(R.array.ogre_magi_skills_specifications);
                break;
            case "Oracle":
                heroImage.setImageResource(R.drawable.oracle);
                heroLore.setText(appResource.getString(R.string.oracle_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.fortunes_end_icon, R.drawable.fates_edict_icon, R.drawable.purifying_flames_icon, R.drawable.false_promise_icon};
                skillNames = appResource.getStringArray(R.array.oracle_skill_names);
                skillDesc = appResource.getStringArray(R.array.oracle_skill_description);
                skillSpec = appResource.getStringArray(R.array.oracle_skills_specifications);
                break;
            case "Outworld Devourer":
                heroImage.setImageResource(R.drawable.outworld_devourer);
                heroLore.setText(appResource.getString(R.string.outworld_devourer_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.arcane_orb_icon, R.drawable.astral_imprisonment_icon, R.drawable.essence_aura_icon, R.drawable.sanitys_eclipse_icon};
                skillNames = appResource.getStringArray(R.array.outworld_devourer_skill_names);
                skillDesc = appResource.getStringArray(R.array.outworld_devourer_skill_description);
                skillSpec = appResource.getStringArray(R.array.outworld_devourer_skills_specifications);
                break;
            case "Puck":
                heroImage.setImageResource(R.drawable.puck);
                heroLore.setText(appResource.getString(R.string.puck_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.illusory_orb_icon, R.drawable.waning_rift_icon, R.drawable.phase_shift_icon, R.drawable.ethereal_jaunt_icon, R.drawable.dream_coil_icon};
                skillNames = appResource.getStringArray(R.array.puck_skill_names);
                skillDesc = appResource.getStringArray(R.array.puck_skills_description);
                skillSpec = appResource.getStringArray(R.array.puck_skill_specifications);
                break;
            case "Pugna":
                heroImage.setImageResource(R.drawable.pugna);
                heroLore.setText(appResource.getString(R.string.pugna_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.nether_blast_icon, R.drawable.decrepify_icon, R.drawable.nether_ward_icon, R.drawable.life_drain_icon};
                skillNames = appResource.getStringArray(R.array.pugna_skill_names);
                skillDesc = appResource.getStringArray(R.array.pugna_skills_description);
                skillSpec = appResource.getStringArray(R.array.pugna_skill_specifications);
                break;
            case "Queen of Pain":
                heroImage.setImageResource(R.drawable.queen_of_pain);
                heroLore.setText(appResource.getString(R.string.queen_of_pain_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.shadow_strike_icon, R.drawable.blink_queen_of_pain_icon, R.drawable.scream_of_pain_icon, R.drawable.sonic_wave_icon};
                skillNames = appResource.getStringArray(R.array.queen_of_pain_skill_names);
                skillDesc = appResource.getStringArray(R.array.queen_of_pain_skill_description);
                skillSpec = appResource.getStringArray(R.array.queen_if_pain_skills_specifications);
                break;
            case "Rubick":
                heroImage.setImageResource(R.drawable.rubick);
                heroLore.setText(appResource.getString(R.string.rubick_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.telekinesis_icon, R.drawable.telekinesis_land_icon, R.drawable.fade_bolt_icon, R.drawable.null_field_icon, R.drawable.spell_steal_icon};
                skillNames = appResource.getStringArray(R.array.rubick_skill_names);
                skillDesc = appResource.getStringArray(R.array.rubick_skill_description);
                skillSpec = appResource.getStringArray(R.array.rubick_skills_specifications);
                break;
            case "Silencer":
                heroImage.setImageResource(R.drawable.silencer);
                heroLore.setText(appResource.getString(R.string.silencer_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.arcane_curse_icon, R.drawable.glaives_of_wisdom_icon, R.drawable.last_word_icon, R.drawable.global_silence_icon};
                skillNames = appResource.getStringArray(R.array.silencer_skill_names);
                skillDesc = appResource.getStringArray(R.array.silencer_skill_description);
                skillSpec = appResource.getStringArray(R.array.silencer_skills_specifications);
                break;
            case "Strom Spirit":
                heroImage.setImageResource(R.drawable.storm_spirit);
                heroLore.setText(appResource.getString(R.string.storm_spirit_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.static_remnant_icon, R.drawable.electric_vortex_icon, R.drawable.overload_icon, R.drawable.ball_lightning_icon};
                skillNames = appResource.getStringArray(R.array.storm_spirit_skill_names);
                skillDesc = appResource.getStringArray(R.array.storm_spirit_skill_description);
                skillSpec = appResource.getStringArray(R.array.storm_spirit_skills_specifications);
                break;
            case "Shadow Demon":
                heroImage.setImageResource(R.drawable.shadow_demon);
                heroLore.setText(appResource.getString(R.string.shadow_demon_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.disruption_icon, R.drawable.soul_catcher_icon, R.drawable.shadow_poison_icon, R.drawable.shadow_poison_release_icon, R.drawable.demonic_purge_icon};
                skillNames = appResource.getStringArray(R.array.shadow_demon_skill_names);
                skillDesc = appResource.getStringArray(R.array.shadow_demon_skill_description);
                skillSpec = appResource.getStringArray(R.array.shadow_demon_skills_specifications);
                break;
            case "Shadow Shaman":
                heroImage.setImageResource(R.drawable.shadow_shaman);
                heroLore.setText(appResource.getString(R.string.shadow_shaman_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.ether_shock_icon, R.drawable.hex_shadow_shaman_icon, R.drawable.shackles_icon, R.drawable.mass_serpent_ward_icon};
                skillNames = appResource.getStringArray(R.array.shadow_shaman_skill_names);
                skillDesc = appResource.getStringArray(R.array.shadow_shaman_skill_description);
                skillSpec = appResource.getStringArray(R.array.shadow_shaman_skills_specifications);
                break;
            case "Tinker":
                heroImage.setImageResource(R.drawable.tinker);
                heroLore.setText(appResource.getString(R.string.tinker_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.laser_icon, R.drawable.heat_seeking_missile_icon, R.drawable.march_of_the_machines_icon, R.drawable.rearm_icon};
                skillNames = appResource.getStringArray(R.array.tinker_skill_names);
                skillDesc = appResource.getStringArray(R.array.tinker_skill_description);
                skillSpec = appResource.getStringArray(R.array.tinker_skills_specifications);
                break;
            case "Warlock":
                heroImage.setImageResource(R.drawable.warlock);
                heroLore.setText(appResource.getString(R.string.warlock_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.fatal_bonds_icon, R.drawable.shadow_word_icon, R.drawable.upheaval_icon, R.drawable.chaotic_offering_icon};
                skillNames = appResource.getStringArray(R.array.warlock_skill_names);
                skillDesc = appResource.getStringArray(R.array.warlock_skill_description);
                skillSpec = appResource.getStringArray(R.array.warlock_skills_specifications);
                break;
            case "Windranger":
                heroImage.setImageResource(R.drawable.windranger);
                heroLore.setText(appResource.getString(R.string.windranger_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.shackleshot_icon, R.drawable.powershot_icon, R.drawable.windrun_icon, R.drawable.focus_fire_icon};
                skillNames = appResource.getStringArray(R.array.windranger_skill_names);
                skillDesc = appResource.getStringArray(R.array.windranger_skill_description);
                skillSpec = appResource.getStringArray(R.array.windranger_skills_specifications);
                break;
            case "Winter Wyvern":
                heroImage.setImageResource(R.drawable.winter_wyvern);
                heroLore.setText(appResource.getString(R.string.winter_wyvern_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.arctic_burn_icon, R.drawable.splinter_blast_icon, R.drawable.cold_embrace_icon, R.drawable.winters_curse_icon};
                skillNames = appResource.getStringArray(R.array.winter_wyvern_skill_names);
                skillDesc = appResource.getStringArray(R.array.winter_wyvern_skill_description);
                skillSpec = appResource.getStringArray(R.array.winter_wyvern_skills_specifications);
                break;
            case "Witch Doctor":
                heroImage.setImageResource(R.drawable.witch_doctor);
                heroLore.setText(appResource.getString(R.string.witch_doctor_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.paralyzing_cask_icon, R.drawable.voodoo_restoration_icon, R.drawable.maledict_icon, R.drawable.death_ward_icon};
                skillNames = appResource.getStringArray(R.array.witch_doctor_skill_names);
                skillDesc = appResource.getStringArray(R.array.witch_doctor_skill_description);
                skillSpec = appResource.getStringArray(R.array.witch_doctor_skills_specifications);
                break;
            case "Zeus":
                heroImage.setImageResource(R.drawable.zeus);
                heroLore.setText(appResource.getString(R.string.zeus_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.arc_lightning_icon, R.drawable.lightning_bolt_icon, R.drawable.static_field_icon, R.drawable.thundergods_wrath_icon};
                skillNames = appResource.getStringArray(R.array.zeus_skill_names);
                skillDesc = appResource.getStringArray(R.array.zeus_skill_description);
                skillSpec = appResource.getStringArray(R.array.zeus_skills_specifications);
                break;
            case "Anti Mage":
                heroImage.setImageResource(R.drawable.anti_mage);
                heroLore.setText(appResource.getString(R.string.anti_mage_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.mana_break_icon, R.drawable.blink_anti_mage_icon, R.drawable.spell_shield_icon, R.drawable.mana_void_icon};
                skillNames = appResource.getStringArray(R.array.anti_mage_skill_names);
                skillDesc = appResource.getStringArray(R.array.anti_mage_skill_description);
                skillSpec = appResource.getStringArray(R.array.anti_mage_skill_specifications);
                break;
            case "Arc Warden":
                heroImage.setImageResource(R.drawable.arc_warden);
                heroLore.setText(appResource.getString(R.string.arc_warden_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.flux_icon, R.drawable.magnetic_field_icon, R.drawable.spark_wraith_icon, R.drawable.tempest_double_icon};
                skillNames = appResource.getStringArray(R.array.arc_warden_skill_names);
                skillDesc = appResource.getStringArray(R.array.arc_warden_skill_description);
                skillSpec = appResource.getStringArray(R.array.arc_warden_skill_specifications);
                break;
            case "Bloodseeker":
                heroImage.setImageResource(R.drawable.bloodseeker);
                heroLore.setText(appResource.getString(R.string.bloodseeker_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.bloodrage_icon, R.drawable.blood_rite_icon, R.drawable.thirst_icon, R.drawable.rupture_icon};
                skillNames = appResource.getStringArray(R.array.bloodseeker_skill_names);
                skillDesc = appResource.getStringArray(R.array.bloodseeker_skill_description);
                skillSpec = appResource.getStringArray(R.array.bloodseeker_skill_specifications);
                break;
            case "Broodmother":
                heroImage.setImageResource(R.drawable.broodmother);
                heroLore.setText(appResource.getString(R.string.broodmother_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.spawn_spiderlings_icon, R.drawable.spin_web_icon, R.drawable.incapacitating_bite_icon, R.drawable.insatiable_hunger_icon};
                skillNames = appResource.getStringArray(R.array.broodmother_skill_names);
                skillDesc = appResource.getStringArray(R.array.broodmother_skill_description);
                skillSpec = appResource.getStringArray(R.array.broodmother_skill_specifications);
                break;
            case "Bounty Hunter":
                heroImage.setImageResource(R.drawable.bounty_hunter);
                heroLore.setText(appResource.getString(R.string.bounty_hunter_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.shuriken_toss_icon, R.drawable.jinada_icon, R.drawable.shadow_walk_icon, R.drawable.track_icon};
                skillNames = appResource.getStringArray(R.array.bounty_hunter_skill_names);
                skillDesc = appResource.getStringArray(R.array.bounty_hunter_skill_description);
                skillSpec = appResource.getStringArray(R.array.bounty_hunter_skill_specifications);
                break;
            case "Clinkz":
                heroImage.setImageResource(R.drawable.clinkz);
                heroLore.setText(appResource.getString(R.string.clinkz_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.strafe_icon, R.drawable.searing_arrows_icon, R.drawable.skeleton_walk_icon, R.drawable.death_pact_icon};
                skillNames = appResource.getStringArray(R.array.clinkz_skill_names);
                skillDesc = appResource.getStringArray(R.array.clinkz_skill_description);
                skillSpec = appResource.getStringArray(R.array.clinkz_skill_specifications);
                break;
            case "Drow Ranger":
                heroImage.setImageResource(R.drawable.drow_ranger);
                heroLore.setText(appResource.getString(R.string.drow_ranger_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.frost_arrows_icon, R.drawable.gust_icon, R.drawable.precision_aura_icon, R.drawable.marksmanship_icon};
                skillNames = appResource.getStringArray(R.array.drow_ranger_skill_names);
                skillDesc = appResource.getStringArray(R.array.drow_ranger_skill_description);
                skillSpec = appResource.getStringArray(R.array.drow_ranger_skill_specifications);
                break;
            case "Ember Spirit":
                heroImage.setImageResource(R.drawable.ember_spirit);
                heroLore.setText(appResource.getString(R.string.ember_spirit_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.searing_chains_icon, R.drawable.sleight_of_fist_icon, R.drawable.flame_guard_icon, R.drawable.activate_fire_remnant_icon, R.drawable.fire_remnant_icon};
                skillNames = appResource.getStringArray(R.array.ember_spirit_skill_names);
                skillDesc = appResource.getStringArray(R.array.ember_spirit_skill_description);
                skillSpec = appResource.getStringArray(R.array.ember_spirit_skill_specifications);
                break;
            case "Faceless Void":
                heroImage.setImageResource(R.drawable.faceless_void);
                heroLore.setText(appResource.getString(R.string.faceless_void_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.time_walk_icon, R.drawable.time_dilation_icon, R.drawable.time_lock_icon, R.drawable.chronosphere_icon};
                skillNames = appResource.getStringArray(R.array.faceless_void_skill_names);
                skillDesc = appResource.getStringArray(R.array.faceless_void_skill_description);
                skillSpec = appResource.getStringArray(R.array.faceless_void_skill_specifications);
                break;
            case "Gyrocopter":
                heroImage.setImageResource(R.drawable.gyrocopter);
                heroLore.setText(appResource.getString(R.string.gyrocopter_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.rocket_barrage_icon, R.drawable.homing_missile_icon, R.drawable.flak_cannon_icon, R.drawable.call_down_icon};
                skillNames = appResource.getStringArray(R.array.gyrocopter_skill_names);
                skillDesc = appResource.getStringArray(R.array.gyrocopter_skill_description);
                skillSpec = appResource.getStringArray(R.array.gyrocopter_skill_specifications);
                break;
            case "Juggernaut":
                heroImage.setImageResource(R.drawable.juggernaut);
                heroLore.setText(appResource.getString(R.string.juggernaut_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.blade_fury_icon, R.drawable.healing_ward_icon, R.drawable.blade_dance_icon, R.drawable.omnislash_icon};
                skillNames = appResource.getStringArray(R.array.juggernaut_skill_names);
                skillDesc = appResource.getStringArray(R.array.juggernaut_skill_description);
                skillSpec = appResource.getStringArray(R.array.juggernaut_skill_specifications);
                break;
            case "Lone Druid":
                heroImage.setImageResource(R.drawable.lone_druid);
                heroLore.setText(appResource.getString(R.string.lone_druid_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.summon_spirit_bear_icon, R.drawable.rabid_icon, R.drawable.savage_roar_icon, R.drawable.battle_cry_icon, R.drawable.true_form_icon, R.drawable.druid_form_icon};
                skillNames = appResource.getStringArray(R.array.lone_druid_skill_names);
                skillDesc = appResource.getStringArray(R.array.lone_druid_skill_description);
                skillSpec = appResource.getStringArray(R.array.lone_druid_skill_specifications);
                break;
            case "Luna":
                heroImage.setImageResource(R.drawable.luna);
                heroLore.setText(appResource.getString(R.string.luna_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.lucent_beam_icon, R.drawable.moon_glaive_icon, R.drawable.lunar_blessing_icon, R.drawable.eclipse_icon};
                skillNames = appResource.getStringArray(R.array.luna_skill_names);
                skillDesc = appResource.getStringArray(R.array.luna_skill_description);
                skillSpec = appResource.getStringArray(R.array.luna_skill_specifications);
                break;
            case "Medusa":
                heroImage.setImageResource(R.drawable.medusa);
                heroLore.setText(appResource.getString(R.string.medusa_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.split_shot_icon, R.drawable.mystic_snake_icon, R.drawable.mana_shield_icon, R.drawable.stone_gaze_icon};
                skillNames = appResource.getStringArray(R.array.medusa_skill_names);
                skillDesc = appResource.getStringArray(R.array.medusa_skill_description);
                skillSpec = appResource.getStringArray(R.array.medusa_skill_specifications);
                break;
            case "Meepo":
                heroImage.setImageResource(R.drawable.meepo);
                heroLore.setText(appResource.getString(R.string.meepo_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.earthbind_icon, R.drawable.poof_icon, R.drawable.geostrike_icon, R.drawable.divided_we_stand_icon};
                skillNames = appResource.getStringArray(R.array.meepo_skill_names);
                skillDesc = appResource.getStringArray(R.array.meepo_skill_description);
                skillSpec = appResource.getStringArray(R.array.meepo_skill_specifications);
                break;
            case "Mirana":
                heroImage.setImageResource(R.drawable.mirana);
                heroLore.setText(appResource.getString(R.string.mirana_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.starstorm_icon, R.drawable.sacred_arrow_icon, R.drawable.leap_icon, R.drawable.moonlight_shadow_icon};
                skillNames = appResource.getStringArray(R.array.mirana_skill_names);
                skillDesc = appResource.getStringArray(R.array.mirana_skill_description);
                skillSpec = appResource.getStringArray(R.array.mirana_skill_specifications);
                break;
            case "Morphling":
                heroImage.setImageResource(R.drawable.morphling);
                heroLore.setText(appResource.getString(R.string.morphling_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.waveform_icon, R.drawable.adaptive_strike_icon, R.drawable.morph_agility_gain_icon, R.drawable.morph_strength_gain_icon, R.drawable.hybrid_icon, R.drawable.replicate_icon, R.drawable.morph_replicate_icon};
                skillNames = appResource.getStringArray(R.array.morphling_skill_names);
                skillDesc = appResource.getStringArray(R.array.morphling_skill_description);
                skillSpec = appResource.getStringArray(R.array.morphling_skill_specifications);
                break;
            case "Naga Siren":
                heroImage.setImageResource(R.drawable.naga_siren);
                heroLore.setText(appResource.getString(R.string.naga_siren_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.mirror_image_icon, R.drawable.ensnare_icon, R.drawable.rip_tide_icon, R.drawable.song_of_the_siren_icon, R.drawable.song_of_the_siren_end_icon};
                skillNames = appResource.getStringArray(R.array.naga_siren_skill_names);
                skillDesc = appResource.getStringArray(R.array.naga_siren_skill_description);
                skillSpec = appResource.getStringArray(R.array.naga_siren_skill_specifications);
                break;
            case "Nyx Assassin":
                heroImage.setImageResource(R.drawable.nyx_assassin);
                heroLore.setText(appResource.getString(R.string.nyx_assassin_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.impale_icon, R.drawable.mana_burn_icon, R.drawable.spiked_carapace_icon, R.drawable.burrow_icon, R.drawable.unburrow_icon, R.drawable.vendetta_icon};
                skillNames = appResource.getStringArray(R.array.nyx_assassin_skill_names);
                skillDesc = appResource.getStringArray(R.array.nyx_assassin_skill_description);
                skillSpec = appResource.getStringArray(R.array.nyx_assassin_skill_specifications);
                break;
            case "Phantom Assassin":
                heroImage.setImageResource(R.drawable.phantom_assassin);
                heroLore.setText(appResource.getString(R.string.phantom_assassin_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.stifling_dagger_icon, R.drawable.phantom_strike_icon, R.drawable.blur_icon, R.drawable.coup_de_grace_icon};
                skillNames = appResource.getStringArray(R.array.phantom_assassin_skill_names);
                skillDesc = appResource.getStringArray(R.array.phantom_assassin_skill_description);
                skillSpec = appResource.getStringArray(R.array.phantom_assassin_skill_specifications);
                break;
            case "Phantom Lancer":
                heroImage.setImageResource(R.drawable.phantom_lancer);
                heroLore.setText(appResource.getString(R.string.phantom_lancer_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.spirit_lance_icon, R.drawable.doppelganger_icon, R.drawable.phantom_rush_icon, R.drawable.juxtapose_icon};
                skillNames = appResource.getStringArray(R.array.phantom_lancer_skill_names);
                skillDesc = appResource.getStringArray(R.array.phantom_lancer_skill_description);
                skillSpec = appResource.getStringArray(R.array.phantom_lancer_skill_specifications);
                break;
            case "Razor":
                heroImage.setImageResource(R.drawable.razor);
                heroLore.setText(appResource.getString(R.string.razor_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.plasma_field_icon, R.drawable.static_link_icon, R.drawable.unstable_current_icon, R.drawable.eye_of_the_storm_icon};
                skillNames = appResource.getStringArray(R.array.razor_skill_names);
                skillDesc = appResource.getStringArray(R.array.razor_skill_description);
                skillSpec = appResource.getStringArray(R.array.razor_skill_specifications);
                break;
            case "Riki":
                heroImage.setImageResource(R.drawable.riki);
                heroLore.setText(appResource.getString(R.string.riki_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.smoke_screen_icon, R.drawable.blink_strike_icon, R.drawable.permanent_invisibility_icon, R.drawable.tricks_of_the_trade};
                skillNames = appResource.getStringArray(R.array.riki_skill_names);
                skillDesc = appResource.getStringArray(R.array.riki_skill_description);
                skillSpec = appResource.getStringArray(R.array.riki_skill_specifications);
                break;
            case "Shadow Fiend":
                heroImage.setImageResource(R.drawable.shadow_fiend);
                heroLore.setText(appResource.getString(R.string.shadow_fiend_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.shadowraze_medium_icon, R.drawable.necromastery_icon, R.drawable.presence_of_the_dark_lord_icon, R.drawable.requiem_of_souls_icon};
                skillNames = appResource.getStringArray(R.array.shadow_fiend_skill_names);
                skillDesc = appResource.getStringArray(R.array.shadow_fiend_skill_description);
                skillSpec = appResource.getStringArray(R.array.shadow_fiend_skill_specifications);
                break;
            case "Slark":
                heroImage.setImageResource(R.drawable.slark);
                heroLore.setText(appResource.getString(R.string.slark_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.dark_pact_icon, R.drawable.pounce_icon, R.drawable.essence_shift_icon, R.drawable.shadow_dance_icon};
                skillNames = appResource.getStringArray(R.array.slark_skill_names);
                skillDesc = appResource.getStringArray(R.array.slark_skill_description);
                skillSpec = appResource.getStringArray(R.array.slark_skill_specifications);
                break;
            case "Sniper":
                heroImage.setImageResource(R.drawable.sniper);
                heroLore.setText(appResource.getString(R.string.sniper_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.shrapnel_icon, R.drawable.headshot_icon, R.drawable.take_aim_icon, R.drawable.assassinate_icon};
                skillNames = appResource.getStringArray(R.array.sniper_skill_names);
                skillDesc = appResource.getStringArray(R.array.sniper_skill_description);
                skillSpec = appResource.getStringArray(R.array.sniper_skill_specifications);
                break;
            case "Spectre":
                heroImage.setImageResource(R.drawable.spectre);
                heroLore.setText(appResource.getString(R.string.spectre_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.spectral_dagger_icon, R.drawable.desolate_icon, R.drawable.dispersion_icon, R.drawable.reality_icon, R.drawable.haunt_icon};
                skillNames = appResource.getStringArray(R.array.spectre_skill_names);
                skillDesc = appResource.getStringArray(R.array.spectre_skill_description);
                skillSpec = appResource.getStringArray(R.array.spectre_skill_specifications);
                break;
            case "Templar Assassin":
                heroImage.setImageResource(R.drawable.templar_assassin);
                heroLore.setText(appResource.getString(R.string.templar_assassin_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.refraction_icon, R.drawable.meld_icon, R.drawable.psi_blades_icon, R.drawable.trap_icon, R.drawable.psionic_trap_icon};
                skillNames = appResource.getStringArray(R.array.templar_assassin_skill_names);
                skillDesc = appResource.getStringArray(R.array.templar_assassin_skill_description);
                skillSpec = appResource.getStringArray(R.array.templar_assassin_skill_specifications);
                break;
            case "Terrorblade":
                heroImage.setImageResource(R.drawable.terrorblade);
                heroLore.setText(appResource.getString(R.string.terrorblade_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.reflection_icon, R.drawable.conjure_image_icon, R.drawable.metamorphosis_icon, R.drawable.sunder_icon};
                skillNames = appResource.getStringArray(R.array.terrorblade_skill_names);
                skillDesc = appResource.getStringArray(R.array.terrorblade_skill_description);
                skillSpec = appResource.getStringArray(R.array.terrorblade_skill_specifications);
                break;
            case "Troll Warlord":
                heroImage.setImageResource(R.drawable.troll_warlord);
                heroLore.setText(appResource.getString(R.string.troll_warlord_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.berserkers_rage_icon, R.drawable.whirling_axes_ranged_icon, R.drawable.whirling_axes_melee_icon, R.drawable.fervor_icon, R.drawable.battle_trance_icon};
                skillNames = appResource.getStringArray(R.array.troll_warlord_skill_names);
                skillDesc = appResource.getStringArray(R.array.troll_warlord_skill_description);
                skillSpec = appResource.getStringArray(R.array.troll_warlord_skill_specifications);
                break;
            case "Ursa":
                heroImage.setImageResource(R.drawable.ursa);
                heroLore.setText(appResource.getString(R.string.ursa_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.earthshock_icon, R.drawable.overpower_icon, R.drawable.fury_swipes_icon, R.drawable.enrage_icon};
                skillNames = appResource.getStringArray(R.array.ursa_skill_names);
                skillDesc = appResource.getStringArray(R.array.ursa_skill_description);
                skillSpec = appResource.getStringArray(R.array.ursa_skill_specifications);
                break;
            case "Vengeful Spirit":
                heroImage.setImageResource(R.drawable.vengeful_spirit);
                heroLore.setText(appResource.getString(R.string.vengeful_spirit_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.magic_missile_icon, R.drawable.wave_of_terror_icon, R.drawable.vengeance_aura_icon, R.drawable.nether_swap_icon};
                skillNames = appResource.getStringArray(R.array.vengeful_spirit_skill_names);
                skillDesc = appResource.getStringArray(R.array.vengeful_spirit_skill_description);
                skillSpec = appResource.getStringArray(R.array.vengeful_spirit_skill_specifications);
                break;
            case "Venomancer":
                heroImage.setImageResource(R.drawable.venomancer);
                heroLore.setText(appResource.getString(R.string.venomancer_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.venomous_gale_icon, R.drawable.poison_sting_icon, R.drawable.plague_ward_icon, R.drawable.poison_nova_icon};
                skillNames = appResource.getStringArray(R.array.venomancer_skill_names);
                skillDesc = appResource.getStringArray(R.array.venomancer_skill_description);
                skillSpec = appResource.getStringArray(R.array.venomancer_skill_specifications);
                break;
            case "Viper":
                heroImage.setImageResource(R.drawable.viper);
                heroLore.setText(appResource.getString(R.string.viper_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.poison_attack_icon, R.drawable.nethertoxin_icon, R.drawable.corrosive_skin_icon, R.drawable.viper_strike_icon};
                skillNames = appResource.getStringArray(R.array.viper_skill_names);
                skillDesc = appResource.getStringArray(R.array.viper_skill_description);
                skillSpec = appResource.getStringArray(R.array.viper_skill_specifications);
                break;
            case "Weaver":
                heroImage.setImageResource(R.drawable.weaver);
                heroLore.setText(appResource.getString(R.string.weaver_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.the_swarm_icon, R.drawable.shukuchi_icon, R.drawable.geminate_attack_icon, R.drawable.time_lapse_icon};
                skillNames = appResource.getStringArray(R.array.weaver_skill_names);
                skillDesc = appResource.getStringArray(R.array.weaver_skill_description);
                skillSpec = appResource.getStringArray(R.array.weaver_skill_specifications);
                break;
            case "Phoenix":
                heroImage.setImageResource(R.drawable.phoenix);
                heroLore.setText(appResource.getString(R.string.phoenix_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.icarus_dive_icon, R.drawable.fire_spirits_icon, R.drawable.sun_ray_icon, R.drawable.supernova_icon, R.drawable.launch_fire_spirit_icon, R.drawable.stop_icarus_dive_icon, R.drawable.stop_sun_ray_icon, R.drawable.toggle_movement_icon};
                skillNames = appResource.getStringArray(R.array.phoenix_skill_names);
                skillDesc = appResource.getStringArray(R.array.phoenix_skill_description);
                skillSpec = appResource.getStringArray(R.array.phoenix_skill_specifications);
                break;
            case "Tusk":
                heroImage.setImageResource(R.drawable.tusk);
                heroLore.setText(appResource.getString(R.string.tusk_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.ice_shards_icon, R.drawable.snowball_icon, R.drawable.frozen_sigil_icon, R.drawable.launch_snowball_icon, R.drawable.walrus_kick_icon, R.drawable.walrus_punch_icon};
                skillNames = appResource.getStringArray(R.array.tusk_skill_names);
                skillDesc = appResource.getStringArray(R.array.tusk_skill_description);
                skillSpec = appResource.getStringArray(R.array.tusk_skill_specifications);
                break;
            case "Elder Titan":
                heroImage.setImageResource(R.drawable.elder_titan);
                heroLore.setText(appResource.getString(R.string.elder_titan_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.echo_stomp_icon, R.drawable.astral_spirit_icon, R.drawable.natural_order_icon, R.drawable.return_astral_spirit_icon, R.drawable.earth_splitter_icon};
                skillNames = appResource.getStringArray(R.array.elder_titan_skill_names);
                skillDesc = appResource.getStringArray(R.array.elder_titan_skill_description);
                skillSpec = appResource.getStringArray(R.array.elder_titan_skill_specifications);
                break;
            case "Legion Commander":
                heroImage.setImageResource(R.drawable.legion_commander);
                heroLore.setText(appResource.getString(R.string.legion_commander_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.overwhelming_odds_icon, R.drawable.press_the_attack_icon, R.drawable.moment_of_courage_icon, R.drawable.duel_icon};
                skillNames = appResource.getStringArray(R.array.legion_commander_skill_names);
                skillDesc = appResource.getStringArray(R.array.legion_commander_skill_description);
                skillSpec = appResource.getStringArray(R.array.legion_commander_skill_specifications);
                break;
            case "Earth Spirit":
                heroImage.setImageResource(R.drawable.earth_spirit);
                heroLore.setText(appResource.getString(R.string.earth_spirit_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.boulder_smash_icon, R.drawable.rolling_boulder_icon, R.drawable.geomagnetic_grip_icon, R.drawable.stone_remnant_icon, R.drawable.enchant_remnant_icon, R.drawable.magnetize_icon};
                skillNames = appResource.getStringArray(R.array.earth_spirit_skill_names);
                skillDesc = appResource.getStringArray(R.array.earth_spirit_skill_description);
                skillSpec = appResource.getStringArray(R.array.earth_spirit_skill_specifications);
                break;
            case "Bristleback":
                heroImage.setImageResource(R.drawable.bristleback);
                heroLore.setText(appResource.getString(R.string.bristleback_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.viscous_nasal_goo_icon, R.drawable.quill_spray_icon, R.drawable.bristleback_ability_icon, R.drawable.warpath_icon};
                skillNames = appResource.getStringArray(R.array.bristleback_skill_names);
                skillDesc = appResource.getStringArray(R.array.bristleback_skill_description);
                skillSpec = appResource.getStringArray(R.array.bristleback_skill_specifications);
                break;
            case "Timbersaw":
                heroImage.setImageResource(R.drawable.timbersaw);
                heroLore.setText(appResource.getString(R.string.timbersaw_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.whirling_death_icon, R.drawable.timber_chain_icon, R.drawable.reactive_armor_icon, R.drawable.chakram_2_icon, R.drawable.return_chakram_2_icon, R.drawable.chakram_icon, R.drawable.return_chakram_icon};
                skillNames = appResource.getStringArray(R.array.timbersaw_skill_names);
                skillDesc = appResource.getStringArray(R.array.timbersaw_skill_description);
                skillSpec = appResource.getStringArray(R.array.timbersaw_skill_specifications);
                break;
            case "Centaur Warrunner":
                heroImage.setImageResource(R.drawable.centaur_warrunner);
                heroLore.setText(appResource.getString(R.string.centaur_warruner_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.hoof_stomp_icon, R.drawable.double_edge_icon, R.drawable.return_centaur_warrunner_icon, R.drawable.stampede_icon};
                skillNames = appResource.getStringArray(R.array.centaur_warruner_skill_names);
                skillDesc = appResource.getStringArray(R.array.centaur_warruner_skill_description);
                skillSpec = appResource.getStringArray(R.array.centaur_warruner_skill_specifications);
                break;
            case "Io":
                heroImage.setImageResource(R.drawable.io);
                heroLore.setText(appResource.getString(R.string.io_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.tether_icon, R.drawable.break_tether_icon, R.drawable.spirits_icon, R.drawable.overcharge_icon, R.drawable.relocate_icon, R.drawable.spirits_in_icon, R.drawable.spirits_out_icon};
                skillNames = appResource.getStringArray(R.array.io_skill_names);
                skillDesc = appResource.getStringArray(R.array.io_skill_description);
                skillSpec = appResource.getStringArray(R.array.io_skill_specifications);
                break;
            case "Huskar":
                heroImage.setImageResource(R.drawable.huskar);
                heroLore.setText(appResource.getString(R.string.huskar_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.inner_vitality_icon, R.drawable.burning_spear_icon, R.drawable.berserkers_blood_icon, R.drawable.life_break_icon};
                skillNames = appResource.getStringArray(R.array.huskar_skill_names);
                skillDesc = appResource.getStringArray(R.array.huskar_skill_description);
                skillSpec = appResource.getStringArray(R.array.huskar_skill_specifications);
                break;
            case "Alchemist":
                heroImage.setImageResource(R.drawable.alchemist);
                heroLore.setText(appResource.getString(R.string.alchemist_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.acid_spray_icon, R.drawable.unstable_concoction_icon, R.drawable.greevils_greed_icon, R.drawable.chemical_rage_icon, R.drawable.unstable_concoction_throw_icon};
                skillNames = appResource.getStringArray(R.array.alchemist_skill_names);
                skillDesc = appResource.getStringArray(R.array.alchemist_skill_description);
                skillSpec = appResource.getStringArray(R.array.alchemist_skill_specifications);
                break;
            case "Brewmaster":
                heroImage.setImageResource(R.drawable.brewmaster);
                heroLore.setText(appResource.getString(R.string.brewmaster_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.thunder_clap_icon, R.drawable.drunken_haze_icon, R.drawable.drunken_brawler_icon, R.drawable.primal_split_icon};
                skillNames = appResource.getStringArray(R.array.brewmaster_skill_names);
                skillDesc = appResource.getStringArray(R.array.brewmaster_skill_description);
                skillSpec = appResource.getStringArray(R.array.brewmaster_skill_specifications);
                break;
            case "Treant Protector":
                heroImage.setImageResource(R.drawable.treant_protector);
                heroLore.setText(appResource.getString(R.string.treant_protector_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.natures_guise_icon, R.drawable.leech_seed_icon, R.drawable.living_armor_icon, R.drawable.eyes_in_the_forest_icon, R.drawable.overgrowth_icon};
                skillNames = appResource.getStringArray(R.array.treant_protector_skill_names);
                skillDesc = appResource.getStringArray(R.array.treant_protector_skill_description);
                skillSpec = appResource.getStringArray(R.array.treant_protector_skill_specifications);
                break;
            case "Omniknight":
                heroImage.setImageResource(R.drawable.omniknight);
                heroLore.setText(appResource.getString(R.string.omniknight_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.purification_icon, R.drawable.repel_icon, R.drawable.degen_aura_icon, R.drawable.guardian_angel_icon};
                skillNames = appResource.getStringArray(R.array.omniknight_skill_names);
                skillDesc = appResource.getStringArray(R.array.omniknight_skill_description);
                skillSpec = appResource.getStringArray(R.array.omniknight_skill_specifications);
                break;
            case "Clockwerk":
                heroImage.setImageResource(R.drawable.clockwerk);
                heroLore.setText(appResource.getString(R.string.clockwerk_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.battery_assault_icon, R.drawable.power_cogs_icon, R.drawable.rocket_flare_icon, R.drawable.hookshot_icon};
                skillNames = appResource.getStringArray(R.array.clockwerk_skill_names);
                skillDesc = appResource.getStringArray(R.array.clockwerk_skill_description);
                skillSpec = appResource.getStringArray(R.array.clockwerk_skill_specifications);
                break;
            case "Dragon Knight":
                heroImage.setImageResource(R.drawable.dragon_knight);
                heroLore.setText(appResource.getString(R.string.dragon_knight_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.breathe_fire_icon, R.drawable.dragon_tail_icon, R.drawable.dragon_blood_icon, R.drawable.elder_dragon_form_icon};
                skillNames = appResource.getStringArray(R.array.dragon_knight_skill_names);
                skillDesc = appResource.getStringArray(R.array.dragon_knight_skill_description);
                skillSpec = appResource.getStringArray(R.array.dragon_knight_skill_specifications);
                break;
            case "Beastmaster":
                heroImage.setImageResource(R.drawable.beastmaster);
                heroLore.setText(appResource.getString(R.string.beastmaster_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.wild_axes_icon, R.drawable.call_of_the_wild_hawk_icon, R.drawable.call_of_the_wild_boar_icon, R.drawable.inner_beast_icon, R.drawable.primal_roar_icon};
                skillNames = appResource.getStringArray(R.array.beastmaster_skill_names);
                skillDesc = appResource.getStringArray(R.array.beastmaster_skill_description);
                skillSpec = appResource.getStringArray(R.array.beastmaster_skill_specifications);
                break;
            case "Earthshaker":
                heroImage.setImageResource(R.drawable.earthshaker);
                heroLore.setText(appResource.getString(R.string.earthshaker_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.fissure_icon, R.drawable.enchant_totem_icon, R.drawable.aftershock_icon, R.drawable.echo_slam_icon};
                skillNames = appResource.getStringArray(R.array.earthshaker_skill_names);
                skillDesc = appResource.getStringArray(R.array.earthshaker_skill_description);
                skillSpec = appResource.getStringArray(R.array.earthshaker_skill_specifications);
                break;
            case "Sven":
                heroImage.setImageResource(R.drawable.sven);
                heroLore.setText(appResource.getString(R.string.sven_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.storm_hammer_icon, R.drawable.great_cleave_icon, R.drawable.warcry_icon, R.drawable.gods_strength_icon};
                skillNames = appResource.getStringArray(R.array.sven_skill_names);
                skillDesc = appResource.getStringArray(R.array.sven_skill_description);
                skillSpec = appResource.getStringArray(R.array.sven_skill_specifications);
                break;
            case "Tiny":
                heroImage.setImageResource(R.drawable.tiny);
                heroLore.setText(appResource.getString(R.string.tiny_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.avalanche_icon, R.drawable.toss_icon, R.drawable.craggy_exterior_icon, R.drawable.grow_icon};
                skillNames = appResource.getStringArray(R.array.tiny_skill_names);
                skillDesc = appResource.getStringArray(R.array.tiny_skill_description);
                skillSpec = appResource.getStringArray(R.array.tiny_skill_specifications);
                break;
            case "Kunkka":
                heroImage.setImageResource(R.drawable.kunkka);
                heroLore.setText(appResource.getString(R.string.kunkka_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.torrent_icon, R.drawable.tidebringer_icon, R.drawable.x_marks_the_spot_icon, R.drawable.ghostship_icon, R.drawable.return_kunkka_icon};
                skillNames = appResource.getStringArray(R.array.kunkka_skill_names);
                skillDesc = appResource.getStringArray(R.array.kunkka_skill_description);
                skillSpec = appResource.getStringArray(R.array.kunkka_skill_specifications);
                break;
            case "Axe":
                heroImage.setImageResource(R.drawable.axe);
                heroLore.setText(appResource.getString(R.string.axe_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.berserkers_call_icon, R.drawable.battle_hunger_icon, R.drawable.counter_helix_icon, R.drawable.culling_blade_icon};
                skillNames = appResource.getStringArray(R.array.axe_skill_names);
                skillDesc = appResource.getStringArray(R.array.axe_skill_description);
                skillSpec = appResource.getStringArray(R.array.axe_skill_specifications);
                break;
            case "Pudge":
                heroImage.setImageResource(R.drawable.pudge);
                heroLore.setText(appResource.getString(R.string.pudge_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.meat_hook_icon, R.drawable.rot_icon, R.drawable.flesh_heap_icon, R.drawable.dismember_icon};
                skillNames = appResource.getStringArray(R.array.pudge_skill_names);
                skillDesc = appResource.getStringArray(R.array.pudge_skill_description);
                skillSpec = appResource.getStringArray(R.array.pudge_skill_specifications);
                break;
            case "Sand King":
                heroImage.setImageResource(R.drawable.sand_king);
                heroLore.setText(appResource.getString(R.string.sand_king_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.burrowstrike_icon, R.drawable.sand_storm_icon, R.drawable.caustic_finale_icon, R.drawable.epicenter_icon};
                skillNames = appResource.getStringArray(R.array.sand_king_skill_names);
                skillDesc = appResource.getStringArray(R.array.sand_king_skill_description);
                skillSpec = appResource.getStringArray(R.array.sand_king_skill_specifications);
                break;
            case "Slardar":
                heroImage.setImageResource(R.drawable.slardar);
                heroLore.setText(appResource.getString(R.string.slardar_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.sprint_icon, R.drawable.slithereen_crush_icon, R.drawable.bash_icon, R.drawable.amplify_damage_icon};
                skillNames = appResource.getStringArray(R.array.slardar_skill_names);
                skillDesc = appResource.getStringArray(R.array.slardar_skill_description);
                skillSpec = appResource.getStringArray(R.array.slardar_skill_specifications);
                break;
            case "Night Stalker":
                heroImage.setImageResource(R.drawable.night_stalker);
                heroLore.setText(appResource.getString(R.string.night_stalker_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.void_icon, R.drawable.crippling_fear_icon, R.drawable.hunter_in_the_night_icon, R.drawable.darkness_icon};
                skillNames = appResource.getStringArray(R.array.night_stalker_skill_names);
                skillDesc = appResource.getStringArray(R.array.night_stalker_skill_description);
                skillSpec = appResource.getStringArray(R.array.night_stalker_skill_specifications);
                break;
            case "Lifestealer":
                heroImage.setImageResource(R.drawable.lifestealer);
                heroLore.setText(appResource.getString(R.string.lifestealer_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.rage_icon, R.drawable.feast_icon, R.drawable.open_wounds_icon, R.drawable.assimilate_icon, R.drawable.eject_icon, R.drawable.infest_icon, R.drawable.control_icon, R.drawable.consume_icon};
                skillNames = appResource.getStringArray(R.array.lifestealer_skill_names);
                skillDesc = appResource.getStringArray(R.array.lifestealer_skill_description);
                skillSpec = appResource.getStringArray(R.array.lifestealer_skill_specifications);
                break;
            case "Wraith King":
                heroImage.setImageResource(R.drawable.wraith_king);
                heroLore.setText(appResource.getString(R.string.wraith_king_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.wraithfire_blast_icon, R.drawable.vampiric_aura_icon, R.drawable.mortal_strike_icon, R.drawable.reincarnation_icon};
                skillNames = appResource.getStringArray(R.array.wraith_king_skill_names);
                skillDesc = appResource.getStringArray(R.array.wraith_king_skill_description);
                skillSpec = appResource.getStringArray(R.array.wraith_king_skill_specifications);
                break;
            case "Tidehunter":
                heroImage.setImageResource(R.drawable.tidehunter);
                heroLore.setText(appResource.getString(R.string.tidehunter_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.gush_icon, R.drawable.kraken_shell_icon, R.drawable.anchor_smash_icon, R.drawable.ravage_icon};
                skillNames = appResource.getStringArray(R.array.tidehunter_skill_names);
                skillDesc = appResource.getStringArray(R.array.tidehunter_skill_description);
                skillSpec = appResource.getStringArray(R.array.tidehunter_skill_specifications);
                break;
            case "Doom":
                heroImage.setImageResource(R.drawable.doom_bringer);
                heroLore.setText(appResource.getString(R.string.zeus_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.devour_icon, R.drawable.scorched_earth_icon, R.drawable.infernal_blade_icon, R.drawable.doom_icon};
                skillNames = appResource.getStringArray(R.array.doom_bringer_skill_names);
                skillDesc = appResource.getStringArray(R.array.doom_bringer_skill_description);
                skillSpec = appResource.getStringArray(R.array.doom_bringer_skill_specifications);
                break;
            case "Spirit Breaker":
                heroImage.setImageResource(R.drawable.spirit_breaker);
                heroLore.setText(appResource.getString(R.string.spirit_breaker_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.charge_of_darkness_icon, R.drawable.empowering_haste_icon, R.drawable.greater_bash_icon, R.drawable.nether_strike_icon};
                skillNames = appResource.getStringArray(R.array.spirit_breaker_skill_names);
                skillDesc = appResource.getStringArray(R.array.spirit_breaker_skill_description);
                skillSpec = appResource.getStringArray(R.array.spirit_breaker_skill_specifications);
                break;
            case "Lycan":
                heroImage.setImageResource(R.drawable.lycan);
                heroLore.setText(appResource.getString(R.string.lycan_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.summon_wolves_icon, R.drawable.howl_icon, R.drawable.feral_impulse_icon, R.drawable.shapeshift_icon};
                skillNames = appResource.getStringArray(R.array.lycan_skill_names);
                skillDesc = appResource.getStringArray(R.array.lycan_skill_description);
                skillSpec = appResource.getStringArray(R.array.lycan_skill_specifications);
                break;
            case "Chaos Knight":
                heroImage.setImageResource(R.drawable.chaos_knight);
                heroLore.setText(appResource.getString(R.string.chaos_knight_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.chaos_bolt_icon, R.drawable.reality_rift_icon, R.drawable.chaos_strike_icon, R.drawable.phantasm_icon};
                skillNames = appResource.getStringArray(R.array.chaos_knight_skill_names);
                skillDesc = appResource.getStringArray(R.array.chaos_knight_skill_description);
                skillSpec = appResource.getStringArray(R.array.chaos_knight_skill_specifications);
                break;
            case "Abaddon":
                heroImage.setImageResource(R.drawable.abaddon);
                heroLore.setText(appResource.getString(R.string.zeus_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.mist_coil_icon, R.drawable.aphotic_shield_icon, R.drawable.curse_of_avernus_icon, R.drawable.borrowed_time_icon};
                skillNames = appResource.getStringArray(R.array.abaddon_skill_names);
                skillDesc = appResource.getStringArray(R.array.abaddon_skill_description);
                skillSpec = appResource.getStringArray(R.array.abaddon_skill_specifications);
                break;
            case "Magnus":
                heroImage.setImageResource(R.drawable.magnus);
                heroLore.setText(appResource.getString(R.string.magnus_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.shockwave_icon, R.drawable.empower_icon, R.drawable.skewer_icon, R.drawable.reverse_polarity_icon};
                skillNames = appResource.getStringArray(R.array.magnus_skill_names);
                skillDesc = appResource.getStringArray(R.array.magnus_skill_description);
                skillSpec = appResource.getStringArray(R.array.magnus_skill_specifications);
                break;
            case "Undying":
                heroImage.setImageResource(R.drawable.undying);
                heroLore.setText(appResource.getString(R.string.undying_lore));
                heroLore.setTextColor(Color.DKGRAY);
                skillIcons = new int[]{R.drawable.decay_icon, R.drawable.soul_rip_icon, R.drawable.tombstone_icon, R.drawable.flesh_golem_icon};
                skillNames = appResource.getStringArray(R.array.undying_skill_names);
                skillDesc = appResource.getStringArray(R.array.undying_skill_description);
                skillSpec = appResource.getStringArray(R.array.undying_skill_specifications);
                break;
        }

    }

}
