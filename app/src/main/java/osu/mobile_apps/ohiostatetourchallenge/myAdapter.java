package osu.mobile_apps.ohiostatetourchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by trevor on 10/18/2017.
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private  List<ListItem> listItems;


    myAdapter(List<ListItem> listItems) {
        this.listItems = listItems;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextViewHead;
        TextView mTextViewDescription;
        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item, parent, false));
            mTextViewHead = itemView.findViewById(R.id.textViewHead);
            mTextViewDescription = itemView.findViewById(R.id.textViewDescription);

        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public myAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // create a new view
        return new ViewHolder(layoutInflater, parent);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        holder.mTextViewHead.setText(listItem.getHead());
        holder.mTextViewDescription.setText(listItem.getDesc());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listItems.size();
    }
}