package com.fahmy.testproject.ui.main;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.widget.TextView;
import com.fahmy.testproject.R;
import com.fahmy.testproject.data.network.model.AdviceResponse;
import com.fahmy.testproject.ui.base.BaseActivity;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    // MainActivity presenter
    @Inject
    MainMvpPresenter<MainMvpView> presenter;
    @BindView(R.id.tv_fortune)
    TextView tvFortune;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // initialize views
        init();
    }

    @Override
    protected void init() {
        // initialize presenter injection
        getActivityComponent().inject(this);

        presenter.onAttach(this);
        presenter.decideToSaveCached(this);
        presenter.getFortuneMessage();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void renderAdviceMessageToUI(AdviceResponse response) {
        if (response != null) {
            tvFortune.setText(getSpannableStringBuilder(response.getFortune()));
        }
    }

    @Override
    public void retryLoadingAdviceMessage() {
        presenter.getFortuneMessage();
    }

    @Override
    public void onErrorMessageReceived(String advice) {
        //render cashed data
        tvFortune.setText(getSpannableStringBuilder(Arrays.asList(advice.split("-"))));
    }

    private SpannableStringBuilder getSpannableStringBuilder(List<String> adviceList) {
        SpannableStringBuilder descriptionSpannable = new SpannableStringBuilder().append("");
        for (String fortune : adviceList) {
            descriptionSpannable.append(fortune.trim()).append("\n");
        }
        return descriptionSpannable;
    }
}
