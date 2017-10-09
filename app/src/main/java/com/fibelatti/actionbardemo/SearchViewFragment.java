package com.fibelatti.actionbardemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SearchViewFragment
        extends Fragment
        implements ListViewAdapter.Callback {

    public interface Callback {
        void onSearchViewItemSelected(String searchViewItem);
    }

    public static final String TAG = SearchViewFragment.class.getSimpleName();

    private Callback callback;

    private ListViewAdapter adapter;
    private RecyclerView recyclerView;

    public static SearchViewFragment newInstance() {
        return new SearchViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ListViewAdapter();
        adapter.setCallback(this);
        adapter.setItems(initItems());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (Callback) context;
        } catch (ClassCastException castException) {
            /** The activity does not implement the callback. */
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpLayout(view);
    }

    private void setUpLayout(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private List<String> initItems() {
        List<String> items = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            items.add("Item " + i);
        }

        return items;
    }

    @Override
    public void onItemClicked(String item) {
        if (callback != null) {
            callback.onSearchViewItemSelected(item);
        }
    }
}
