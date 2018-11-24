package com.codingchallenge.features.message.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codingchallenge.app.base.view.fragment.message.BaseMessageRecyclerViewFragment;
import com.codingchallenge.data.model.Message;
import com.codingchallenge.databinding.RefreshRecyclerViewBinding;
import com.codingchallenge.features.message.view.adapters.MessageRecycleViewAdapter;
import com.codingchallenge.features.message.viewmodel.MessageListViewModel;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
public class MessageListFragment extends BaseMessageRecyclerViewFragment<RefreshRecyclerViewBinding, Message, MessageRecycleViewAdapter, MessageListViewModel>  {

    @Nullable
    protected Toolbar toolbar;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    public MessageListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    protected Class<MessageListViewModel> getViewModelClass() {
        return MessageListViewModel.class;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.setMLifecycleOwner(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public boolean onLoadData() {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
            Message item = adapter.getItem(viewHolder.getAdapterPosition());
            if (item != null) {
                adapter.getItemClickListener().onItemClick(viewHolder.itemView, item);
                adapter.notifyItemChanged(viewHolder.getAdapterPosition());
            }
    }
}