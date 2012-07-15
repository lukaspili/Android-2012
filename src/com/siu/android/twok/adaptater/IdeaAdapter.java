package com.siu.android.twok.adaptater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.siu.android.twok.R;
import com.siu.android.twok.model.Idea;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 27/06/12
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public class IdeaAdapter extends ArrayAdapter<Idea>
{
    public IdeaAdapter(Context context, List<Idea> items){
        super(context, R.layout.row_idea, items);
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_idea, null);
        }

        Idea idea = getItem(position);

        ViewHolder viewHolder = (ViewHolder) row.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        }

        viewHolder.name.setText(idea.getName());
        viewHolder.wannaDo.setText("Je veux faire: " + idea.getWant());
        viewHolder.dontCare.setText("inninteressant : " + idea.getDontwant());
        viewHolder.hadDone.setText("Déjà fait: " + idea.getAlready());

        return row;
    }

    private static class ViewHolder {

        protected TextView name;
        protected TextView wannaDo;
        protected TextView dontCare;
        protected TextView hadDone;

        ViewHolder(View row) {
            name = (TextView) row.findViewById(R.id.row_idea_name);
            wannaDo = (TextView) row.findViewById(R.id.row_idea_wanna_do);
            dontCare = (TextView) row.findViewById(R.id.row_idea_not_care);
            hadDone = (TextView) row.findViewById(R.id.row_idea_had_done);
        }
    }
}
