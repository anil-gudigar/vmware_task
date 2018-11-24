package com.codingchallenge.app.base.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.codingchallenge.app.base.interfaces.OnItemClickListener;
import com.codingchallenge.app.base.view.widget.DiffCallBack;
import com.codingchallenge.app.base.viewmodel.ItemModel;
import com.codingchallenge.helper.injection.qualifier.ActivityContext;

import java.util.List;
/**
 * Created by Anil Gudigar on 11/10/18.
 */

public abstract class BaseRecyclerViewModelAdapter<T, VIEW_MODEL_T extends ItemModel<T>, D extends DiffCallBack<T>>
        extends RecyclerView.Adapter<BaseRecyclerViewModelAdapter.BaseItemViewHolder<T, VIEW_MODEL_T>> {


    @NonNull
    protected final Context mContext;
    @Nullable
    protected List<T> data;
    @Nullable
    protected OnItemClickListener<T> itemClickListener;
    @NonNull
    protected LayoutInflater mInflater;

    public BaseRecyclerViewModelAdapter(@ActivityContext Context context) {
        setHasStableIds(true);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        setHasStableIds(true);
    }

    @Override
    public final void onBindViewHolder(BaseItemViewHolder<T, VIEW_MODEL_T> holder, int position) {
        holder.setItem(data.get(position));
    }

    @Nullable
    public T getItem(int adapterPosition) {
        if (data != null) {
            if (adapterPosition >= 0 && adapterPosition < data.size()) {
                return data.get(adapterPosition);
            }
        }
        return null;
    }

    @Nullable
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> newData) {
        if (this.data != newData) {
            this.data = newData;
        }
        notifyDataSetChanged();
    }

    @Nullable
    public OnItemClickListener<T> getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(@Nullable OnItemClickListener<T> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    @NonNull
    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(@NonNull LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }


    @NonNull
    public Context getmContext() {
        return mContext;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public void updateList(@NonNull D diffutils) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffutils);
        diffResult.dispatchUpdatesTo(this);
    }

    public static class BaseItemViewHolder<I, VT extends ItemModel<I>>
            extends RecyclerView.ViewHolder {

        protected final VT viewModel;
        private final ViewDataBinding binding;

        public BaseItemViewHolder(@NonNull View itemView, ViewDataBinding binding, VT viewModel) {
            super(itemView);
            this.binding = binding;
            this.viewModel = viewModel;
        }

        void setItem(I item) {
            viewModel.setItem(item);
            binding.invalidateAll();
            binding.executePendingBindings();
        }
    }
}
