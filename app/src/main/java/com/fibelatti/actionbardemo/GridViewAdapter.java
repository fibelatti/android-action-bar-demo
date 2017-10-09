package com.fibelatti.actionbardemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface Callback {
        void onItemClicked(String item);
    }

    private Callback callback;
    private List<String> items;

    private class GridViewHolder
            extends RecyclerView.ViewHolder {

        private TextView textViewDescription;

        public GridViewHolder(View view) {
            super(view);
            textViewDescription = view.findViewById(R.id.textView_description);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callback != null) {
                        callback.onItemClicked(textViewDescription.getText().toString());
                    }
                }
            });
        }
    }

    public GridViewAdapter() {
        this.items = new ArrayList<>();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setItems(List<String> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid, parent, false);

        return new GridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String item = items.get(position);
        GridViewHolder listViewHolder = (GridViewHolder) holder;

        listViewHolder.textViewDescription.setText(item);
    }
}
