package corp.wmsoft.android.examples.mvpc.second.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

import corp.wmsoft.android.examples.mvpc.R;
import corp.wmsoft.android.examples.mvpc.app.MainApplication;
import corp.wmsoft.android.examples.mvpc.databinding.FragmentTestBinding;
import corp.wmsoft.android.examples.mvpc.third.ThirdActivity;
import corp.wmsoft.android.lib.mvpcandroid.base.IPresenterFactory;
import corp.wmsoft.android.lib.mvpcandroid.base.MVPLoaderBaseFragment;


/**
 * Created by westman on 8/5/16.
 *
 */
public class TestFragment extends MVPLoaderBaseFragment<TestContract.View, TestContract.Presenter> implements TestContract.View {

    /**/
    private static final String ARG_NAME = "name";
    /**/
    private FragmentTestBinding binding;


    public static TestFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected IPresenterFactory<TestContract.View, TestContract.Presenter> providePresenterFactory() {
        return new TestPresenterFactory(getArguments() != null ? getArguments().getString(ARG_NAME) : null);
    }

    @Override
    protected void onInitializePresenter(TestContract.Presenter presenter) {
        binding.setPresenter(getPresenter());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);
        return binding.getRoot();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MainApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    @Override
    public void showCounter(String counter) {
        binding.counter.setText(counter);
    }

    @Override
    public void showThirdView() {
        startActivity(new Intent(getActivity(), ThirdActivity.class));
    }

    @Override
    public void showCurrentViewName(String name) {
        binding.name.setText(name);
    }

    @Override
    public void setGoToThirdVisibility(boolean isVisible) {
        binding.buttonThird.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        binding.testFarme.setVisibility(isVisible ? View.VISIBLE : View.GONE); // hide, because to use custom MVP view need to provide UniqueIdentifier for each view
    }

}