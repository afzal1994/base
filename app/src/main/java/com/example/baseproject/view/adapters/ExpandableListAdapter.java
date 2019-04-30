package com.example.baseproject.view.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.baseproject.R;
import com.example.baseproject.models.MoviesEntity;
import com.example.baseproject.view.activities.MoviesListActivity;
import com.example.baseproject.view.fragments.MovieDetailsFragment;

import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {


    private final MoviesListActivity context;
    private final List<MoviesEntity> list;



    public ExpandableListAdapter(MoviesListActivity moviesListActivity, List<MoviesEntity> list) {
        this.context=moviesListActivity;
        this.list=list;
    }

    @Override
    public int getGroupCount() {
        return this.list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getMovieList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return this.list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return list.get(groupPosition).getMovieList().get(childPosititon);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String text=list.get(groupPosition).getGenre();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_parent, null);
        }
        TextView lblListHeader = convertView.findViewById(R.id.textParent);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(text);

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String text=list.get(groupPosition).getMovieList().get(childPosition).getName();
        LayoutInflater infalInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.row_child, null);
        TextView txtListChild = convertView.findViewById(R.id.textChild);
        txtListChild.setText(text);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailsFragment movieDetailsFragment=new MovieDetailsFragment();
                Bundle bundle=new Bundle();
                bundle.putParcelable("list",list.get(groupPosition).getMovieList().get(childPosition));
                movieDetailsFragment.setArguments(bundle);
                context.switchToFragment(bundle,null,movieDetailsFragment);
            }
        });
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
