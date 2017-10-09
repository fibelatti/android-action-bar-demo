package com.fibelatti.actionbardemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ScrollViewFragment
        extends Fragment {

    public static final String TAG = ScrollViewFragment.class.getSimpleName();

    private GridViewAdapter adapter;
    private RecyclerView recyclerView;

    public static ScrollViewFragment newInstance() {
        return new ScrollViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new GridViewAdapter();
        adapter.setItems(initItems());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scroll_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpLayout(view);
    }

    private void setUpLayout(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_grid);

        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), GridLayoutManager.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), GridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private List<String> initItems() {
        List<String> items = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            items.add("Item " + i);
        }

        return items;
    }
}
