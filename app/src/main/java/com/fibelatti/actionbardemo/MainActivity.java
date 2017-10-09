package com.fibelatti.actionbardemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity
        extends AppCompatActivity
        implements ActionBarItem.Callback, SearchViewFragment.Callback, GridViewFragment.Callback {

    private AppBarLayout layoutAppBar;
    private CollapsingToolbarLayout layoutCollapsingToolbar;
    private LinearLayout layoutActionBarContainer;
    private Toolbar layoutToolbar;
    private FrameLayout layoutFragmentContainer;

    private FragmentManager fragmentManager;

    private Fragment searchViewFragment;
    private Fragment gridViewFragment;
    private Fragment scrollViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getSupportFragmentManager();

        setUpLayout();
        initFragments();

        layoutActionBarContainer.addView(createActionBarItem(""));

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(layoutFragmentContainer.getId(), searchViewFragment, SearchViewFragment.TAG).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        layoutActionBarContainer.removeView(layoutActionBarContainer
                .getChildAt(layoutActionBarContainer.getChildCount() - 1));
    }

    private void setUpLayout() {
        setContentView(R.layout.activity_main);

        layoutAppBar = findViewById(R.id.layout_appBar);
        layoutCollapsingToolbar = findViewById(R.id.layout_collapsingToolbar);
        layoutActionBarContainer = findViewById(R.id.layout_actionBarContainer);
        layoutToolbar = findViewById(R.id.layout_toolbar);
        layoutFragmentContainer = findViewById(R.id.layout_fragmentContainer);

        layoutToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutAppBar.setExpanded(true);
            }
        });
    }

    private void initFragments() {
        searchViewFragment = SearchViewFragment.newInstance();
        gridViewFragment = GridViewFragment.newInstance();
        scrollViewFragment = ScrollViewFragment.newInstance();
    }

    private ActionBarItem createActionBarItem(String associatedFragmentName) {
        ActionBarItem actionBarItem = new ActionBarItem(this);
        actionBarItem.setAssociatedFragmentName(associatedFragmentName);
        actionBarItem.setCallback(this);

        return actionBarItem;
    }

    @Override
    public void onItemClicked(String associatedFragmentName, ActionBarItem actionBarItem) {
        layoutActionBarContainer.removeView(actionBarItem);
        fragmentManager.popBackStack();
    }

    @Override
    public void onSearchViewItemSelected(String searchViewItem) {
        layoutActionBarContainer.addView(createActionBarItem(SearchViewFragment.TAG));

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(layoutFragmentContainer.getId(), gridViewFragment, GridViewFragment.TAG)
                .addToBackStack(SearchViewFragment.TAG)
                .commit();
    }

    @Override
    public void onGridViewItemSelected(String gridViewItem) {
        layoutActionBarContainer.addView(createActionBarItem(GridViewFragment.TAG));

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(layoutFragmentContainer.getId(), scrollViewFragment, ScrollViewFragment.TAG)
                .addToBackStack(GridViewFragment.TAG)
                .commit();
    }
}
