package com.fibelatti.actionbardemo;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class ActionBarItem
        extends ConstraintLayout {

    private TextView textViewDesciption;
    private TextView buttonEdit;

    private String associatedFragmentName;
    private Callback callback;

    public interface Callback {
        void onItemClicked(String associatedFragmentName, ActionBarItem actionBarItem);
    }

    public ActionBarItem(Context context) {
        super(context);
        setUp();
    }

    public ActionBarItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public ActionBarItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    private void setUp() {
        inflate(getContext(), R.layout.item_action_bar, this);
        textViewDesciption = findViewById(R.id.textView_description);
        buttonEdit = findViewById(R.id.button_edit);

        buttonEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onItemClicked(associatedFragmentName, ActionBarItem.this);
                }
            }
        });
    }

    public void setAssociatedFragmentName(String associatedFragmentName) {
        this.associatedFragmentName = associatedFragmentName;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
