package corp.wmsoft.android.examples.mvpc.longrunning;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import corp.wmsoft.android.examples.mvpc.R;
import corp.wmsoft.android.examples.mvpc.databinding.ActivityLongRunningBinding;
import corp.wmsoft.android.lib.mvpc.predefined.MVPCAppCompatActivity;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;


public class LongRunningActivity extends MVPCAppCompatActivity<LongRunningContract.View, LongRunningContract.Presenter> implements LongRunningContract.View {

    /**/
    private ActivityLongRunningBinding binding;


    @Override
    public IMVPCPresenterFactory<LongRunningContract.View, LongRunningContract.Presenter> providePresenterFactory() {
        return new LongRunningPresenterFactory();
    }

    @Override
    public void onInitializePresenter(LongRunningContract.Presenter presenter) {
        binding.setPresenter(getPresenter());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_long_running);
        setSupportActionBar(binding.toolbar);
    }

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.textView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        binding.textView.setVisibility(View.VISIBLE);
        binding.textView.setText(message);
    }
}