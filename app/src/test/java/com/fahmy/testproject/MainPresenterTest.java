package com.fahmy.testproject;

import com.fahmy.testproject.data.DataManager;
import com.fahmy.testproject.data.network.ApiHelper;
import com.fahmy.testproject.data.network.model.AdviceResponse;
import com.fahmy.testproject.ui.main.MainMvpView;
import com.fahmy.testproject.ui.main.MainPresenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainMvpView mMockMainMvpView;

    @Mock
    DataManager mMockDataManager;

    private MainPresenter<MainMvpView> mainPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mainPresenter = new MainPresenter<>(mMockDataManager);
        mMockMainMvpView = mock(MainMvpView.class);
        mMockDataManager = mock(DataManager.class);

        mainPresenter.onAttach(mMockMainMvpView);
    }

    @Test
    public void presentDataFromApiTest() {
        ApiHelper mockedApiInterface = Mockito.mock(ApiHelper.class);
        Call<AdviceResponse> mockedCall = Mockito.mock(Call.class);

        Mockito.when(mockedApiInterface.getFortuneMessage()).thenReturn(mockedCall);

        Mockito.doAnswer(invocation -> {
            Callback<AdviceResponse> callback = invocation.getArgument(0);

            callback.onResponse(mockedCall, Response.success(new AdviceResponse()));
            // or callback.onFailure(mockedCall, new IOException());

            return null;
        }).when(mockedCall).enqueue(any(Callback.class));
    }

    @After
    public void destroyPresenterInstance() throws Exception {
        mainPresenter.onDetach();
    }
}
