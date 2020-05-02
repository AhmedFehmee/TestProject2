package com.fahmy.testproject.ui.main;

import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fahmy.testproject.R;
import com.fahmy.testproject.data.network.model.CarsResponse;
import com.fahmy.testproject.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    // MainActivity presenter
    @Inject
    MainMvpPresenter<MainMvpView> presenter;
    @BindView(R.id.refresh_view)
    SwipeRefreshLayout refreshView;
    @BindView(R.id.cars_list)
    RecyclerView cars_list;

    private RecyclerView.Adapter carsAdapter;
    private List<CarsResponse.Data> carsLists;
    private int pageNo = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // initialize views
        init();

        handleViews();
    }

    @Override
    protected void init() {
        // initialize presenter injection
        getActivityComponent().inject(this);

        cars_list.setHasFixedSize(true);
        cars_list.setLayoutManager(new LinearLayoutManager(this));
        cars_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        carsLists = new ArrayList<>();
        carsAdapter = new CarsAdapter(carsLists, getApplicationContext());
        cars_list.setAdapter(carsAdapter);

        presenter.onAttach(this);
        presenter.getCarsFromApi(pageNo, false);
    }

    private void handleViews() {
        pageNo = 1;
        refreshView.setOnRefreshListener(() -> presenter.getCarsFromApi(pageNo, false));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void renderCarsListToUI(CarsResponse response) {
        refreshView.setRefreshing(false);
        if (response != null) {
            carsLists = response.getData();
            cars_list.setAdapter(carsAdapter);
            carsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void retryLoadingCarsList() {
        refreshView.setRefreshing(false);
        refreshView.setOnRefreshListener(() -> presenter.getCarsFromApi(pageNo, false));
    }

    @Override
    public void onErrorMessageReceived(String advice) {
        refreshView.setRefreshing(false);
        //render Error Message
    }
}
