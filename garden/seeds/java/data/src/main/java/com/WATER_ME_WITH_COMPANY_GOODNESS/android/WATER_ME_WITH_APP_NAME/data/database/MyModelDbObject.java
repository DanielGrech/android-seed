package com.{company_name}.android.{app_package_name_prefix}.data.database;

import com.{company_name}.{app_package_name_prefix}.model.MyModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MyModelDbObject extends RealmObject {

    private int id;

    public static MyModelDbObject fromMyModel(MyModel myModel) {
        final MyModelDbObject object = new MyModelDbObject();
      
        return object;
    }

    public static MyModel toMyModel(MyModelDbObject object) {
        return MyModel.newMyModel()
                .build();
    }

    public MyModelDbObject() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
