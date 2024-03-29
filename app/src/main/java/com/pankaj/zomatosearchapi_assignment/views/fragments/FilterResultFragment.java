package com.pankaj.zomatosearchapi_assignment.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.pankaj.zomatosearchapi_assignment.R;
import com.pankaj.zomatosearchapi_assignment.utilities.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pankaj.zomatosearchapi_assignment.utilities.Utils.getOrderBy;
import static com.pankaj.zomatosearchapi_assignment.utilities.Utils.getSortBy;
import static com.pankaj.zomatosearchapi_assignment.utilities.Utils.setOrderBy;
import static com.pankaj.zomatosearchapi_assignment.utilities.Utils.setSortBy;


/**
 * This fragments represents filtered result
 *
 * @author Pankaj Nirban.
 * @since 16-06-2019
 */

public class FilterResultFragment extends DialogFragment {

    @BindView(R.id.radio_group_order_by)
    RadioGroup orderByRadioGroup;
    @BindView(R.id.radio_group_sort_by)
    RadioGroup sortByRadioGroup;
    @BindView(R.id.apply_button)
    Button applyButton;
    @BindView(R.id.close_fragment_IV)
    ImageView closeFragment;
    private IFilterCallBacks iFilterCallBacks;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        iFilterCallBacks = (IFilterCallBacks) getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.filter_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setListeners();
        prefillSelected();
        super.onViewCreated(view, savedInstanceState);
    }

    private void prefillSelected() {
        if (getSortBy().equals(Constants.SORT_BY_RATING)) {
            sortByRadioGroup.check(R.id.rating);
        } else {
            sortByRadioGroup.check(R.id.cost);
        }
        if (getOrderBy().equals(Constants.ORDER_BY_ASC)) {
            orderByRadioGroup.check(R.id.ascending);
        } else {
            orderByRadioGroup.check(R.id.descending);
        }
    }

    private void setListeners() {
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (orderByRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.ascending:
                        setOrderBy(Constants.ORDER_BY_ASC);
                        break;

                    case R.id.descending:
                        setOrderBy(Constants.ORDER_BY_DESC);
                }

                switch (sortByRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.rating:
                        setSortBy(Constants.SORT_BY_RATING);
                        break;

                    case R.id.cost:
                        setSortBy(Constants.SORT_BY_COST);
                }

                iFilterCallBacks.onApplyFilter();
                dismiss();
            }
        });

        closeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iFilterCallBacks.onDismissFilter();
                dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            window.setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
        }
    }

    public interface IFilterCallBacks {
        public void onApplyFilter();

        public void onDismissFilter();
    }
}
