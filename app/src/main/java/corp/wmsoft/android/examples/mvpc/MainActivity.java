package corp.wmsoft.android.examples.mvpc;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;

import corp.wmsoft.android.examples.mvpc.databinding.ActivityMainBinding;
import corp.wmsoft.android.examples.mvpc.second.SecondActivity;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpc.support.v7.app.MVPCAppCompatActivity;


public class MainActivity extends MVPCAppCompatActivity<MainContract.View, MainContract.Presenter> implements MainContract.View {

    /**/
    private ActivityMainBinding binding;


    @Override
    public IMVPCPresenterFactory<MainContract.View, MainContract.Presenter> providePresenterFactory() {
        return new MainPresenterFactory();
    }

    @Override
    protected void onInitializePresenter(MainContract.Presenter presenter) {
        binding.setPresenter(getPresenter());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
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
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void showFabEvent() {
        Snackbar.make(binding.fab, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public void showCounter(String count) {
        binding.mainContent.counter.setText(count);
    }

    @Override
    public void showSecondView() {
        startActivity(new Intent(this, SecondActivity.class));
    }

}