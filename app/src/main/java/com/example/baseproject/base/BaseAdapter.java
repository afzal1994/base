package com.example.baseproject.base;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


public abstract class BaseAdapter<T extends BaseViewModel> extends RecyclerView.Adapter<BaseViewHolder> {

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), viewType, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        Object object=getObjectForPosition(i);
        BaseViewModel viewModel=getViewModel();
        baseViewHolder.bind(object,viewModel,i);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutForPosition(position);
    }

    public abstract Object getObjectForPosition(int position);
    public abstract T getViewModel();
    public abstract int getLayoutForPosition(int position);
}
