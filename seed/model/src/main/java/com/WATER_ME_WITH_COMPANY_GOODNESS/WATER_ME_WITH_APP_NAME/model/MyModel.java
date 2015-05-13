package com.{company_name}.{app_package_name_prefix}.model;

import com.google.gson.annotations.SerializedName;

public class MyModel {

    private MyModel(Builder builder) {

    }

    public static Builder newMyModel() {
        return new Builder();
    }


    public static final class Builder {

        Builder() {
        }

        public MyModel build() {
            return new MyModel(this);
        }
    }
}
