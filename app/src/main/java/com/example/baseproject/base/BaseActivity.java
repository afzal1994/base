package com.example.baseproject.base;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.baseproject.R;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    private T mBinding;
    private V mViewModel;

    public abstract V getViewModel();

    public abstract int getContentLayout();

    public abstract int getBindingVariable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        mViewModel.getSwitch().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s!=null && s.equals("Back")) onBackPressed();
            }
        });
    }

    public void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, getContentLayout());
        mViewModel = getViewModel();
        mBinding.setVariable(getBindingVariable(), mViewModel);
    }

    public T getViewDataBinding() {
        return mBinding;
    }

    public void switchToFragment(Bundle bundle, String fragmentTag, Fragment fragment) {
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().
                add(R.id.layout_child_container,
                        fragment, fragmentTag).addToBackStack(null).commit();


    }
}
