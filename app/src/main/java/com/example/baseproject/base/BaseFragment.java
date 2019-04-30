package com.example.baseproject.base;


import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment  {


    private T mBinding;

    public abstract V getViewModel();

    public abstract int getFragmentLayout();

    public abstract int getBindingVariable();

    public View initBinding(LayoutInflater inflater, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false);
        V mViewModel = getViewModel();
        mBinding.setVariable(getBindingVariable(), mViewModel);
        mViewModel.getSwitch().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    if (s.equals("Back")) getActivity().onBackPressed();


                }
            }
        });
        return mBinding.getRoot();
    }

    /**
     * returns the binding variable to use further
     *
     * @return
     */
    public T getViewDataBinding() {
        return mBinding;
    }

    public boolean onBackPressed() {
        return false;
    }

    public void clearAllBackStack(FragmentManager supportFragmentManager) {
        for (int i = 0; i < supportFragmentManager.getBackStackEntryCount(); ++i) {
            supportFragmentManager.popBackStack();
        }
    }


    public void switchToFragment(Bundle bundle, String fragmentTag, BaseFragment... baseFragments) {
        baseFragments[0].setArguments(bundle);
        getFragmentManager().beginTransaction().
                add(R.id.layout_child_container,
                        baseFragments[0], fragmentTag).addToBackStack(null).commit();
    }



}
