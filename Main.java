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
    public static ListView skillLV;

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

    public static void slideIn() {
        if (!isPanelShown()) {
            // Show the panel
            Animation rightIn = AnimationUtils.loadAnimation(applicationContext,
                    R.anim.right_in);

            heroPreview.startAnimation(rightIn);
            tabLayout.setVisibility(View.GONE);
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

        skillIcons = null;

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

        }

    }

}
