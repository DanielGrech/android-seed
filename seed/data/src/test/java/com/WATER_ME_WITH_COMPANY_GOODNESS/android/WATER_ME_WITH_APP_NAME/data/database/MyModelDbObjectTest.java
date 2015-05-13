package com.{company_name}.android.{app_package_name_prefix}.data.database;

import com.{company_name}.android.{app_package_name_prefix}.data.{app_package_name_prefix}DataTestRunner;
import com.{company_name}.{app_package_name_prefix}.model.MyModel;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.{company_name}.android.{app_package_name_prefix}.data.database.MyModelDbObjectTest.MyModelDbObjectAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith({app_class_prefix}DataTestRunner.class)
public class MyModelDbObjectTest {

    @Test
    public void testFromMyModelPopulatesFieldsCorrectly() {
        final MyModel model = MyModel.newMyModel()
                .build();

        assertThat(MyModelDbObject.fromMyModel(model));
    }

    @Test
    public void testToMyModelPopulatesFieldsCorrectly() {
        MyModelDbObject mockDbObject = mock(MyModelDbObject.class);
       
        final MyModel model = MyModelDbObject.toMyModel(mockDbObject);
    }

    static class MyModelAssert extends AbstractAssert<MyModelAssert, MyModel> {
        protected MyModelAssert(MyModel actual) {
            super(actual, MyModelAssert.class);
        }

        public static MyModelAssert assertThat(MyModel actual) {
            return new MyModelAssert(actual);
        }
    }
}
