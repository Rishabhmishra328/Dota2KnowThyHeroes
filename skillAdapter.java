package com.echo.primestudio.dota2knowthyheroes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rishabh Mishra on 2/17/2016.
 */
public class skillAdapter extends ArrayAdapter {

    Context context;
    String[] skillName;
    String[] skillSpecs;
    String[] skillDescription;
    int[] skillIcon;


    public skillAdapter(Context context, String[] skillName, int[] skillIcon, String[] skillDescription, String[] skillSpecs) {

        super(context, R.layout.skill_template, R.id.skill_name, skillName);

        this.context = context ;
        this.skillIcon = skillIcon;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
        this.skillSpecs = skillSpecs;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.skill_template,parent,false);

        ImageView skillIconIV = (ImageView) row.findViewById(R.id.skill_image);
        TextView skillNameTV = (TextView) row.findViewById(R.id.skill_name);
        TextView skillDescriptionTV = (TextView) row.findViewById(R.id.skill_description);
        TextView skillSpecsTV = (TextView) row.findViewById(R.id.skill_specifications);

        skillIconIV.setImageResource(skillIcon[position]);
        skillNameTV.setText(skillName[position]);
        skillDescriptionTV.setText(skillDescription[position]);
        skillSpecsTV.setText(skillSpecs[position]);

        return row;
    }
}
