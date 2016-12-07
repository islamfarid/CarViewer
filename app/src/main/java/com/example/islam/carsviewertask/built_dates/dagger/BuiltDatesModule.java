package com.example.islam.carsviewertask.built_dates.dagger;



import com.example.islam.carsviewertask.built_dates.BuildDataContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 03/12/16.
 */
@Module
public class BuiltDatesModule {
    private final BuildDataContract.View mView;


    public BuiltDatesModule(BuildDataContract.View view) {
        this.mView = view;
    }

    @Provides
    BuildDataContract.View provideManufactureView() {
        return mView;
    }

}
