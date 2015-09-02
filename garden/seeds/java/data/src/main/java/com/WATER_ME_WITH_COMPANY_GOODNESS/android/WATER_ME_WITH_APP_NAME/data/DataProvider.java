package com.{company_name}.android.{app_package_name_prefix}.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.{company_name}.android.{app_package_name_prefix}.data.database.DatabaseDataSource;
import com.{company_name}.android.{app_package_name_prefix}.data.database.DummyDatabaseDataSource;
import com.{company_name}.android.{app_package_name_prefix}.data.network.NetworkDataSource;
import com.{company_name}.{app_package_name_prefix}.model.MyModel;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class DataProvider implements DataSource {

    private static DataProvider instance;

    public static DataProvider getInstance(Context context) {
        if (instance == null) {
            instance = new DataProvider(context);
        }

        return instance;
    }

    private final DataSource networkDataSource;

    private final DatabaseDataSource dbDataSource;

    DataProvider(Context context) {
        this(new NetworkDataSource(), new DummyDatabaseDataSource());
    }

    DataProvider(@NonNull DataSource networkDataSource, @NonNull DatabaseDataSource dbDataSource) {
        this.networkDataSource = networkDataSource;
        this.dbDataSource = dbDataSource;
    }

    @Override
    public Observable<MyModel> getMyModel(int number) {
        final Observable<MyModel> networkObservable = networkDataSource.getMyModel(number)
                .compose(applyNetworkFunctions(dbDataSource));

        final Observable<MyModel> dbObservable = dbDataSource.getMyModel(number);

        return dbObservable
                .onErrorResumeNext(networkObservable)
                .flatMap(new Func1<MyModel, Observable<MyModel>>() {
                    @Override
                    public Observable<MyModel> call(MyModel model) {
                        if (model == null) {
                            return networkObservable;
                        } else {
                            return Observable.just(model);
                        }
                    }
                });
    }

    @Override
    public Observable<MyModel> getLatestMyModel() {
        final Observable<MyModel> networkObservable = networkDataSource.getLatestMyModel()
                .compose(applyNetworkFunctions(dbDataSource));
        final Observable<MyModel> dbObservable = dbDataSource.getLatestMyModel();

        return Observable.mergeDelayError(dbObservable, networkObservable).distinct();
    }

    static class SaveNetworkDataToDbFunction implements Func1<MyModel, MyModel> {

        final DatabaseDataSource dbDataSource;

        SaveNetworkDataToDbFunction(DatabaseDataSource dbDataSource) {
            this.dbDataSource = dbDataSource;
        }

        @Override
        public MyModel call(MyModel model) {
            dbDataSource.save(model);
            return model;
        }
    }

    static class NullCheckFilter<T> implements Func1<T, Boolean> {

        @Override
        public Boolean call(T t) {
            return t != null;
        }
    }

    static Observable.Transformer<MyModel, MyModel> applyNetworkFunctions(final DatabaseDataSource dbDataSource) {
        return new Observable.Transformer<MyModel, MyModel>() {
            @Override
            public Observable<MyModel> call(Observable<MyModel> observable) {
                return observable.filter(new NullCheckFilter<>())
                        .map(new SaveNetworkDataToDbFunction(dbDataSource));
            }
        };
    }
}
