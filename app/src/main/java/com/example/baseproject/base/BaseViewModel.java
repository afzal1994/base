package com.example.baseproject.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableInt;
import android.view.View;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {
    public CompositeDisposable compositeDisposable=new CompositeDisposable();
    private MutableLiveData<String> switchComponent=new MutableLiveData<String>();

    public ObservableInt showLoader=new ObservableInt(View.GONE);
    public LiveData<String> getSwitch(){
        return switchComponent;
    }
    public void switchToComponent(String componentName){
        switchComponent.setValue(componentName);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
    }
}
