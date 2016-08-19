package corp.wmsoft.android.examples.mvpc.second;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;

import corp.wmsoft.android.examples.mvpc.R;
import corp.wmsoft.android.examples.mvpc.databinding.ActivitySecondBinding;
import corp.wmsoft.android.lib.mvpc.predefined.MVPCAppCompatActivity;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;


public class SecondActivity extends MVPCAppCompatActivity<SecondContract.View, SecondContract.Presenter> implements SecondContract.View {

    /**/
    private ActivitySecondBinding binding;


    @Override
    public IMVPCPresenterFactory<SecondContract.View, SecondContract.Presenter> providePresenterFactory() {
        return new SecondPresenterFactory();
    }

    @Override
    public void onInitializePresenter(SecondContract.Presenter presenter) {
        binding.setPresenter(getPresenter());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        setSupportActionBar(binding.toolbar);
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
    public void showFabEvent() {
        Snackbar.make(binding.fab, "Second activity snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

}