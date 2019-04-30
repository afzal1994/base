package com.example.baseproject.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.baseproject.BR;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding mBinding;

    public BaseViewHolder(@NonNull ViewDataBinding itemView) {
        super(itemView.getRoot());
        this.mBinding = itemView;
    }

    public void bind(Object object, BaseViewModel viewModel, int position) {
        mBinding.setVariable(BR.object, object);
        mBinding.setVariable(BR.viewModel, viewModel);
        mBinding.setVariable(BR.position, position);
        mBinding.executePendingBindings();
    }

}
