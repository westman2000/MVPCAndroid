package corp.wmsoft.android.examples.mvpc.third;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import corp.wmsoft.android.examples.mvpc.R;
import corp.wmsoft.android.examples.mvpc.databinding.ActivityThirdBinding;
import corp.wmsoft.android.examples.mvpc.third.pager.ViewPagerFragment;
import corp.wmsoft.android.lib.mvpcrx.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpcrx.support.v4.MVPCFragmentActivity;


public class ThirdActivity extends MVPCFragmentActivity<ThirdContract.View, ThirdContract.Presenter> implements ThirdContract.View {

    /**/
    private ActivityThirdBinding binding;


    @Override
    public IMVPCPresenterFactory<ThirdContract.View, ThirdContract.Presenter> providePresenterFactory() {
        return new ThirdPresenterFactory();
    }

    @Override
    public void onInitializePresenter(ThirdContract.Presenter presenter) {
        binding.setPresenter(getPresenter());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_third);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, ViewPagerFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCounter(String count) {
        binding.counter.setText(count);
    }

}