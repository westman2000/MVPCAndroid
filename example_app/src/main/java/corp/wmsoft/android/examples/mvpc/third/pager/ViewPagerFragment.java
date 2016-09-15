package corp.wmsoft.android.examples.mvpc.third.pager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

import corp.wmsoft.android.examples.mvpc.R;
import corp.wmsoft.android.examples.mvpc.app.MainApplication;
import corp.wmsoft.android.examples.mvpc.databinding.FragmentPagerBinding;


/**
 * Created by admin on 8/6/16.
 *
 */
public class ViewPagerFragment extends Fragment {


    public static ViewPagerFragment newInstance() {
        Bundle args = new Bundle();
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentPagerBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pager, container, false);
        binding.viewpager.setAdapter(new PagerAdapter(getFragmentManager()));
        return binding.getRoot();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MainApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
