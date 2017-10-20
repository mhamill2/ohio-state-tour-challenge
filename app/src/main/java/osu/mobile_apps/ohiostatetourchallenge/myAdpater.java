package osu.mobile_apps.ohiostatetourchallenge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by trevo on 10/18/2017.
 */

public class myAdpater extends RecyclerView.Adapter<myAdpater.ViewHolder> {
    private String[] mDataset;

    public myAdpater(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    private List<ListItem> listItems;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextViewHead;
        public TextView mTextViewDescription;
        public ViewHolder(TextView v) {
            super(v);
            mTextViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            mTextViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public myAdpater(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public myAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list, parent, false);
        // set the view's size, margins, paddings and layout parameter
        return new ViewHolder(v);
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