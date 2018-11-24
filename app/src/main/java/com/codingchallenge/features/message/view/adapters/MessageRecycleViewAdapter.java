package com.codingchallenge.features.message.view.adapters;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codingchallenge.R;
import com.codingchallenge.app.base.adapter.BaseRecyclerViewModelAdapter;
import com.codingchallenge.data.model.Message;
import com.codingchallenge.databinding.MessageListItemBinding;
import com.codingchallenge.features.message.viewmodel.MessageDiffCallBack;
import com.codingchallenge.features.message.viewmodel.MessageItemViewModel;
import com.codingchallenge.helper.injection.qualifier.ActivityContext;

import java.util.Collections;

import javax.inject.Inject;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
public class MessageRecycleViewAdapter extends BaseRecyclerViewModelAdapter<Message, MessageItemViewModel, MessageDiffCallBack> {
    @Inject
    public MessageRecycleViewAdapter(@ActivityContext Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseItemViewHolder<Message, MessageItemViewModel> onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_item, parent, false);

        MessageItemViewModel viewModel = new MessageItemViewModel();
        MessageListItemBinding binding = MessageListItemBinding.bind(itemView);
        binding.setMessageItem(viewModel);
        MessageItemViewHolder holder = new MessageItemViewHolder(itemView, binding, viewModel);
        itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                Message item = getItem(holder.getAdapterPosition());
                if (item != null) {
                    itemClickListener.onItemClick(holder.itemView, item);
                }
            }
        });
        return holder;
    }


    static class MessageItemViewHolder
            extends BaseItemViewHolder<Message, MessageItemViewModel> {

        public MessageItemViewHolder(View itemView, ViewDataBinding binding,
                                     MessageItemViewModel viewModel) {
            super(itemView, binding, viewModel);
        }
    }


}
