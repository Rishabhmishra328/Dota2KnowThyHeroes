package com.echo.primestudio.dota2knowthyheroes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.echo.primestudio.dota2knowthyheroes.R.*;

/**
 * Created by Rishabh Mishra on 2/16/2016.
 */
public class heroesAdapter extends ArrayAdapter {

    Context context;
    String[] heroName;
    int[] heroIcon;

    public heroesAdapter(Context context, String[] heroes, int[] heroIcon) {

        super(context, layout.list_template, id.hero_name, heroes);

        this.context = context ;
        this.heroIcon = heroIcon;
        this.heroName = heroes;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(layout.list_template,parent,false);

        ImageView heroIconIV = (ImageView) row.findViewById(id.hero_icon);
        TextView heroNameTV = (TextView) row.findViewById(id.hero_name);

        heroIconIV.setImageResource(heroIcon[position]);
        heroNameTV.setText(heroName[position]);

        return row;
    }
}
