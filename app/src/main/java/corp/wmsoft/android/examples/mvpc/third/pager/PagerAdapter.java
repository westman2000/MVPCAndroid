package corp.wmsoft.android.examples.mvpc.third.pager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import corp.wmsoft.android.examples.mvpc.second.fragment.TestFragment;


/**
 * Created by admin on 8/6/16.
 *
 */
public class PagerAdapter extends FragmentPagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:return TestFragment.newInstance("first page");
            case 1:return TestFragment.newInstance("second page");
            case 2:return TestFragment.newInstance("third page");
            case 3:return TestFragment.newInstance("four page");
            default:return TestFragment.newInstance("strange behaviour");
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
