package com.{company_name}.android.{app_package_name_prefix}.data.database;

import com.{company_name}.android.{app_package_name_prefix}.data.DataSource;
import com.{company_name}.{app_package_name_prefix}.model.MyModel;

public interface DatabaseDataSource extends DataSource {

    void save(MyModel model);
}
