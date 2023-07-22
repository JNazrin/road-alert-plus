package com.example.groupproject_ict602;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DataAdapter extends ArrayAdapter<Maklumat> {

    private Context context;
    private List<Maklumat> maklumats;

    public DataAdapter(Context context, List<Maklumat> crowdInfos) {
        super(context, 0, crowdInfos);
        this.context = context;
        this.maklumats = crowdInfos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hazard_grouping, parent, false);
        }

        Maklumat maklumat = maklumats.get(position);

        TextView typeTextView = convertView.findViewById(R.id.txtType);
        TextView descTextView = convertView.findViewById(R.id.txtDesc);
        TextView dateTimeTextView = convertView.findViewById(R.id.txtDateTime);

        typeTextView.setText(maklumat.getLocation() + " - " +maklumat.getType());
        descTextView.setText(maklumat.getDescription());
        dateTimeTextView.setText(maklumat.getDateTime());

        return convertView;
    }
}
