package com.codingchallenge.app.base.view.fragment.message;

import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.RelativeLayout;

import com.codingchallenge.R;
import com.codingchallenge.app.base.adapter.BaseRecyclerViewModelAdapter;
import com.codingchallenge.app.base.interfaces.UiRefreshable;
import com.codingchallenge.app.base.view.fragment.BaseViewModelFragment;
import com.codingchallenge.app.base.view.widget.RecyclerItemTouchHelper;
import com.codingchallenge.features.message.view.adapters.MessageRecycleViewAdapter;
import com.codingchallenge.features.message.viewmodel.MessageListViewModel;

import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Anil Gudigar on 11/10/18.
 */

public abstract class BaseMessageRecyclerViewFragment<
        B extends ViewDataBinding,
        T,
        A extends BaseRecyclerViewModelAdapter,
        VM extends MessageListViewModel>
        extends BaseViewModelFragment<B, MessageListViewModel> implements UiRefreshable, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    protected RecyclerView recyclerView;
    protected RelativeLayout emptyView;
    protected RelativeLayout dataView;
    protected RelativeLayout progressView;
    protected boolean isRefreshing;

    @Inject
    protected A adapter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        emptyView = view.findViewById(R.id.emptyView);
        dataView = view.findViewById(R.id.dataView);
        progressView = view.findViewById(R.id.progressView);
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        recyclerView.addItemDecoration(horizontalDecoration);

        viewModel.initAdapter((MessageRecycleViewAdapter) adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                showEmptyView();
            }
        });
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }


    @Override
    protected int getLayout() {
        return R.layout.refresh_recycler_view;
    }

    @Override
    public void setLoading(boolean loading) {
        if (!loading) {
            doneRefresh();
            progressView.setVisibility(GONE);
        } else {
            progressView.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void refresh() {
        if (!isRefreshing) {
            viewModel.refresh();
            isRefreshing = true;
        }
    }

    @Override
    public void doneRefresh() {
        showEmptyView();
    }

    private void showEmptyView() {
        if (null != viewModel) {
            if (adapter != null) {
                if (emptyView != null) {
                    if (adapter.getItemCount() == 0) {
                        showParentOrSelf(false);
                    } else {
                        showParentOrSelf(true);
                    }
                }
            } else {
                if (emptyView != null) {
                    showParentOrSelf(false);
                }
            }
        }
    }

    private void showParentOrSelf(boolean showRecyclerView) {
        if (recyclerView != null)
            dataView.setVisibility(showRecyclerView ? VISIBLE : GONE);
        emptyView.setVisibility(!showRecyclerView ? VISIBLE : GONE);
    }
}