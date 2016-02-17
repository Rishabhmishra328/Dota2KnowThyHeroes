package com.echo.primestudio.dota2knowthyheroes;

import android.content.Context;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        //Declaration
        heroPreview = (ScrollView) findViewById(R.id.hero_preview);
        heroLore = (TextView) findViewById(R.id.hero_lore);
        heroImage = (ImageView) findViewById(R.id.hero_image);
        heroIntro = (LinearLayout) findViewById(R.id.hero_introduction);

        if (skillTemplate == null){
            Log.d("SKILLTEMPLATE"," FROM MAIN IS NULL");
        }

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        tabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        tabLayout.setViewPager(pager);

        //Setting Context
        Context appContext = getApplicationContext();
        applicationContext = appContext;


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

                    for(int i = 0 ; i < skillNames.length ; i ++) {

                        if (skillTemplate == null){

                            Log.d("SKILLTEMPLATE", " IS NULL");
                        }

                        LayoutInflater layoutInflater = (LayoutInflater)  applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        skillTemplate = layoutInflater.inflate(R.layout.skill_template, heroIntro, false);

                        ImageView skillIV = (ImageView) skillTemplate.findViewById(R.id.skill_image);
                        TextView skillNameTV = (TextView) skillTemplate.findViewById(R.id.skill_name);
                        TextView skillDesTV = (TextView) skillTemplate.findViewById(R.id.skill_description);
                        TextView skillSpecTV = (TextView) skillTemplate.findViewById(R.id.skill_specifications);

                        skillIV.setImageResource(skillIcons[i]);
                        skillNameTV.setText(skillNames[i]);
                        skillDesTV.setText(skillDesc[i]);
                        skillSpecTV.setText(skillSpec[i]);

                        skillDesTV.setTextColor(Color.BLACK);
                        skillNameTV.setTextColor(Color.BLACK);
                        skillSpecTV.setTextColor(Color.BLACK);

                        heroIntro.addView(skillTemplate);

                    }

                }
            });

            return layout;
        }
    }

    public static void getHeroDetails(String hero) {

        switch (hero) {
            case "Rubick":
                heroImage.setImageResource(R.drawable.rubick);
                heroLore.setText("Any mage can cast a spell or two, and a few may even study long enough to become a wizard, but only the most talented are allowed to be recognized as a Magus. Yet as with any sorcerer’s circle, a sense of community has never guaranteed competitive courtesy.\n" +
                        "Already a renowned duelist and scholar of the grander world of sorcery, it had never occurred to Rubick that he might perhaps be Magus material until he was in the midst of his seventh assassination attempt. As he casually tossed the twelfth of a string of would-be killers from a high balcony, it dawned on him how utterly unimaginative the attempts on his life had become. Where once the interruption of a fingersnap or firehand might have put a cheerful spring in his step, it had all become so very predictable. He craved greater competition. Therefore, donning his combat mask, he did what any wizard seeking to ascend the ranks would do: he announced his intention to kill a Magus.\n" +
                        "\n" +
                        "Rubick quickly discovered that to threaten one Magus is to threaten them all, and they fell upon him in force. Each antagonist's spell was an unstoppable torrent of energy, and every attack a calculated killing blow. But very soon something occurred that Rubick's foes found unexpected: their arts appeared to turn against them. Inside the magic maelstrom, Rubick chuckled, subtly reading and replicating the powers of one in order to cast it against another, sowing chaos among those who had allied against him. Accusations of betrayal began to fly, and soon the sorcerers turned one upon another without suspecting who was behind their undoing.\n" +
                        "\n" +
                        "When the battle finally drew to a close, all were singed and frozen, soaked and cut and pierced. More than one lay dead by an ally’s craft. Rubick stood apart, sore but delighted in the week’s festivities. None had the strength to argue when he presented his petition of assumption to the Hidden Council, and the Insubstantial Eleven agreed as one to grant him the title of Grand Magus.\n" +
                        "\n");
                skillIcons = new int[]{R.drawable.telekinesis_icon, R.drawable.telekinesis_land_icon, R.drawable.fade_bolt_icon, R.drawable.null_field_icon, R.drawable.spell_steal_icon};
                skillNames = new String[]{"Telekinesis", "Telekinesis Land", "Fade Bolt", "Null Field", "Spell Steal"};
                skillDesc = new String[]{"Rubick uses his telekinetic powers to lift the enemy into the air briefly and then hurls them back at the ground. The unit lands on the ground with such force that it stuns nearby enemies.", "Chooses the location the target will land when Telekinesis finishes.", "Rubick creates a powerful stream of arcane energy that travels between enemy units, dealing damage and reducing their attack damage. Each jump deals less damage.", "Rubick's mastery of the arcane protects nearby allies against weaker magics, granting them magic resistance.", "Rubick studies the trace magical essence of one enemy hero, learning the secrets of the last spell the hero cast. Rubick can use this spell as his own for several minutes or until he dies. Upgradable by Aghanim's Scepter."};
                skillSpec = new String[]{"ABILITY: UNIT TARGET\n" +
                        "AFFECTS: ENEMY UNITS\n" +
                        "PIERCES SPELL IMMUNITY: NO\n" +
                        "LIFT DURATION: 1.5 / 1.75 / 2 / 2.25\n" +
                        "STUN DURATION: 1 / 1.25 / 1.5 / 1.75\n" +
                        "IMPACT RADIUS: 325 / 325 / 325 / 325\n" +
                        "CAST RANGE: 550 / 575 / 600 / 625\n" + "Mana CostMANA COST: 120/120/120/120\n" +
                        "CooldownCOOLDOWN: 22", "", "ABILITY: UNIT TARGET\n" +
                        "AFFECTS: ENEMY UNITS\n" +
                        "DAMAGE TYPE: MAGICAL\n" +
                        "PIERCES SPELL IMMUNITY: NO\n" +
                        "DAMAGE: 70 / 140 / 210 / 280\n" +
                        "JUMP REDUCTION: 4% / 4% / 4% / 4%\n" +
                        "HERO DAMAGE REDUCTION: 20 / 25 / 30 / 35\n" +
                        "CREEP DAMAGE REDUCTION: 10 / 13 / 15 / 17\n" +
                        "DEBUFF DURATION: 10 / 10 / 10 / 10\n" + "Mana CostMANA COST: 120/130/140/150\n" +
                        "CooldownCOOLDOWN: 16/14/12/10", "ABILITY: PASSIVE\n" +
                        "MAGIC RESISTANCE: 5% / 10% / 15% / 20%\n" +
                        "RADIUS: 900", "ABILITY: UNIT TARGET\n" +
                        "AFFECTS: ENEMY HEROES\n" +
                        "PIERCES SPELL IMMUNITY: YES\n" +
                        "DURATION: 180 / 240 / 300\n" +
                        "SCEPTER COOLDOWN: 2\n" + "Mana CostMANA COST: 25/25/25\n" +
                        "CooldownCOOLDOWN: 20/18/16"};

                break;
        }

    }

}
