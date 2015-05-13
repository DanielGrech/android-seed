package com.{company_name}.android.{app_package_name_prefix}.data.database;

import android.content.Context;

import com.{company_name}.{app_package_name_prefix}.model.MyModel;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func0;

public class RealmDbDataSource implements DatabaseDataSource {

    private final Context context;

    public RealmDbDataSource(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public Observable<MyModel> getMyModel(final int number) {
        return Observable.defer(new Func0<Observable<MyModel>>() {
            @Override
            public Observable<MyModel> call() {
                return Observable.empty();
            }
        });
    }

    @Override
    public Observable<MyModel> getLatestMyModel() {
        return Observable.defer(new Func0<Observable<MyModel>>() {
            @Override
            public Observable<MyModel> call() {
                return Observable.empty();
            }
        });
    }

    @Override
    public void save(final MyModel model) {
        final MyModelDbObject dbObject = MyModelDbObject.fromMyModel(model);
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(dbObject);
            }
        });
    }

    private Realm getRealm() {
        return Realm.getInstance(context);
    }
}
