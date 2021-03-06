package com.example.eyal.recycleview.bl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eyal.recycleview.R;
import com.example.eyal.recycleview.common.TaskItem;
import com.example.eyal.recycleview.common.User;

import java.util.List;

/**
 * Created by Eyal on 11/7/2015.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>
{
    private List<User> users;
    public UsersAdapter(List<User> users){
        this.users = users;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        User usr = users.get(position);
        holder.mTvDescription.setText(usr.getUserName());


    }

    public void UpdateDataSource(List<User> user)
    {
        if(user ==null) return; //TODO Decide how to deal with it (Maybe an exception??)
        this.users= user;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Each item is a view in the card.
        private TextView mTvDescription;

        public ViewHolder(View parentView) {
            super(parentView);
            mTvDescription = (TextView) parentView.findViewById(R.id.textView_description);
        }
    }


}
