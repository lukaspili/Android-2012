package com.siu.android.twok.adaptater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.siu.android.twok.R;
import com.siu.android.twok.model.Comment;
import com.siu.android.twok.model.Idea;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 15/07/12
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public class CommentAdapter extends ArrayAdapter<Comment>
{
    public CommentAdapter(Context context,  List<Comment> objects) {
        super(context, R.layout.row_comment, objects);
    }

    @Override
    public View getView(int position, View row2, ViewGroup parent) {
        if (row2 == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row2 = inflater.inflate(R.layout.row_comment, null);
        }

        Comment comment = getItem(position);

        ViewHolder viewHolder = (ViewHolder) row2.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder(row2);
            row2.setTag(viewHolder);
        }

        viewHolder.content.setText( comment.getContent());
        viewHolder.info.setText("Par " + comment.getAuteur() + " le: "+comment.getDate());

        return row2;
    }

    private static class ViewHolder {

        protected TextView content;
        protected TextView info;

        ViewHolder(View row2) {
            content = (TextView) row2.findViewById(R.id.row_comment_content);
            info = (TextView) row2.findViewById(R.id.row_comment_info);

        }
    }
}
