package com.echo.primestudio.knowthyheroes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Rishabh Mishra on 2/16/2016.
 */
public class heroesAdapter extends ArrayAdapter {

    Context context;
    String[] heroName;
    int[] heroIcon;

    public heroesAdapter(Context context, String[] heroes, int[] heroIcon) {

        super(context, R.layout.list_template, R.id.hero_name, heroes);

        this.context = context ;
        this.heroIcon = heroIcon;
        this.heroName = heroes;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.list_template,parent,false);

        ImageView heroIconIV = (ImageView) row.findViewById(R.id.hero_icon);
        TextView heroNameTV = (TextView) row.findViewById(R.id.hero_name);

        heroIconIV.setImageResource(heroIcon[position]);
        heroNameTV.setText(heroName[position]);

        return row;
    }
}
