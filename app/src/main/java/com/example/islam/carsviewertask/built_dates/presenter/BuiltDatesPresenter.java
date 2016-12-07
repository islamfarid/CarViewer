package com.example.islam.carsviewertask.built_dates.presenter;

import android.support.annotation.NonNull;

import com.example.islam.carsviewertask.built_dates.BuildDataContract;
import com.example.islam.carsviewertask.built_dates.bussiness.BuiltDatesBusiness;
import com.example.islam.carsviewertask.common.Constants;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by islam on 04/12/16.
 */

public class BuiltDatesPresenter implements BuildDataContract.Presenter{
    @NonNull
    private BuildDataContract.View mBuiltDatesView;
    @NonNull
    private BuiltDatesBusiness mBuiltDatesBusiness;
    @NonNull
    private CompositeSubscription mSubscriptions;

    @Inject
    public BuiltDatesPresenter(BuildDataContract.View mBuiltDatesView) {
        this.mBuiltDatesView = mBuiltDatesView;
        mSubscriptions = new CompositeSubscription();
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Inject
    public void setBusiness(BuiltDatesBusiness builtDatesBusiness) {
        this.mBuiltDatesBusiness = builtDatesBusiness;
    }

    @Override
    public void getBuiltDates(String manufacture, String mainType) {
        mBuiltDatesView.showLoading();
        mSubscriptions.add(mBuiltDatesBusiness
                .getBuitDates(manufacture, mainType, Constants.WK_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        carModel -> {
                            mBuiltDatesView.showBuiltDates(carModel);
                        }, // onError
                        exception -> {
                            mBuiltDatesView.showErrorMessage(exception.getMessage());
                        },//onComplete
                        () -> mBuiltDatesView.hideLoading()));
    }
}
