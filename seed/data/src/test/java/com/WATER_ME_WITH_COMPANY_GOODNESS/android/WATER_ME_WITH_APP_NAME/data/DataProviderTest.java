package com.{company_name}.android.{app_package_name_prefix}.data;

import com.{company_name}.android.{app_package_name_prefix}.data.database.DatabaseDataSource;
import com.{company_name}.{app_package_name_prefix}.model.MyModel;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import rx.Observable;
import rx.schedulers.TestScheduler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith({app_class_prefix}DataTestRunner.class)
public class DataProviderTest {

    @After
    public void teardown() {
        Mockito.validateMockitoUsage();
    }

    @Test
    public void testSaveNetworkDataToDbFunctionSavesMyModel() {
        final MyModel mockMyModel = mock(MyModel.class);
        final DatabaseDataSource mockDbSource = mock(DatabaseDataSource.class);
        final DataProvider.SaveNetworkDataToDbFunction function
                = new DataProvider.SaveNetworkDataToDbFunction(mockDbSource);

        function.call(mockMyModel);

        verify(mockDbSource).save(eq(mockMyModel));
    }

    @Test
    public void testSaveNetworkDataToDbFunctionReturnsSameMyModel() {
        final MyModel mockMyModel = mock(MyModel.class);
        final DataProvider.SaveNetworkDataToDbFunction function
                = new DataProvider.SaveNetworkDataToDbFunction(mock(DatabaseDataSource.class));

        final MyModel retval = function.call(mockMyModel);

        assertThat(retval).isSameAs(mockMyModel);
    }

    @Test
    public void testNullCheckFilterForNullInput() {
        final DataProvider.NullCheckFilter<Object> filter = new DataProvider.NullCheckFilter<>();
        assertThat(filter.call(null)).isFalse();
    }

    @Test
    public void testNullCheckFilterForNonNullInput() {
        final DataProvider.NullCheckFilter<Object> filter = new DataProvider.NullCheckFilter<>();
        assertThat(filter.call(new Object())).isTrue();
    }

    @Test
    public void testGetMyModelReturnsDbResultIfPresent() {
        final MyModel mockMyModel = mock(MyModel.class);
        final Observable<MyModel> dbReturnObservable = Observable.just(mockMyModel);

        final DataSource networkDataSource = mock(DataSource.class);
        when(networkDataSource.getMyModel(anyInt())).thenReturn(Observable.<MyModel>empty());

        final DatabaseDataSource dbDataSource = mock(DatabaseDataSource.class);
        when(dbDataSource.getMyModel(anyInt())).thenReturn(dbReturnObservable);

        final DataProvider dataProvider = new DataProvider(networkDataSource, dbDataSource);

        final MyModel retval = dataProvider.getMyModel(1).toBlocking().single();

        assertThat(retval).isSameAs(mockMyModel);
    }


    @Test
    public void testGetMyModelReturnsNetworkResultIfDbResultHasError() {
        final MyModel mockMyModel = mock(MyModel.class);
        final Observable<MyModel> dbReturnObservable = Observable.error(mock(Throwable.class));

        final DataSource networkDataSource = mock(DataSource.class);
        when(networkDataSource.getMyModel(anyInt())).thenReturn(Observable.just(mockMyModel));

        final DatabaseDataSource dbDataSource = mock(DatabaseDataSource.class);
        when(dbDataSource.getMyModel(anyInt())).thenReturn(dbReturnObservable);

        final DataProvider dataProvider = new DataProvider(networkDataSource, dbDataSource);

        final MyModel retval = dataProvider.getMyModel(1).toBlocking().single();

        assertThat(retval).isSameAs(mockMyModel);
    }

    @Test
    public void testGetMyModelReturnsNetworkResultIfNotInDb() {
        final MyModel mockMyModel = mock(MyModel.class);
        final Observable<MyModel> dbReturnObservable = Observable.just(null);

        final DataSource networkDataSource = mock(DataSource.class);
        when(networkDataSource.getMyModel(anyInt())).thenReturn(Observable.just(mockMyModel));

        final DatabaseDataSource dbDataSource = mock(DatabaseDataSource.class);
        when(dbDataSource.getMyModel(anyInt())).thenReturn(dbReturnObservable);

        final DataProvider dataProvider = new DataProvider(networkDataSource, dbDataSource);

        final MyModel retval = dataProvider.getMyModel(1).toBlocking().single();

        assertThat(retval).isSameAs(mockMyModel);
    }

    @Test
    public void testGetLatestMyModelReturnsFromDbAndNetworkIfDifferent() {
        final MyModel dbMyModel = mock(MyModel.class);
        final MyModel networkMyModel = mock(MyModel.class);

        final Observable<MyModel> dbReturnObservable = Observable.just(dbMyModel);
        final Observable<MyModel> networkReturnObservable = Observable.just(networkMyModel);

        final DataSource networkDataSource = mock(DataSource.class);
        doReturn(networkReturnObservable).when(networkDataSource).getLatestMyModel();

        final DatabaseDataSource dbDataSource = mock(DatabaseDataSource.class);
        doReturn(dbReturnObservable).when(dbDataSource).getLatestMyModel();

        final DataProvider dataProvider = new DataProvider(networkDataSource, dbDataSource);

        final Observable<MyModel> latestMyModel = dataProvider.getLatestMyModel();

        final Iterable<MyModel> models = latestMyModel.toBlocking().toIterable();
        assertThat(models).isNotNull().hasSize(2);
    }

    @Test
    public void testGetLatestMyModelDoesNotUpdateDbResultsIfNotChanged() {
        final MyModel dbMyModel = MyModel.newMyModel().number(1).build();
        final MyModel networkMyModel = dbMyModel;

        final Observable<MyModel> dbReturnObservable = Observable.just(dbMyModel);
        final Observable<MyModel> networkReturnObservable = Observable.just(networkMyModel);

        final DataSource networkDataSource = mock(DataSource.class);
        doReturn(networkReturnObservable).when(networkDataSource).getLatestMyModel();

        final DatabaseDataSource dbDataSource = mock(DatabaseDataSource.class);
        doReturn(dbReturnObservable).when(dbDataSource).getLatestMyModel();

        final DataProvider dataProvider = new DataProvider(networkDataSource, dbDataSource);

        final Observable<MyModel> latestMyModel = dataProvider.getLatestMyModel();

        final Iterable<MyModel> models = latestMyModel.toBlocking().toIterable();
        assertThat(models).isNotNull().hasSize(1);
    }

}
