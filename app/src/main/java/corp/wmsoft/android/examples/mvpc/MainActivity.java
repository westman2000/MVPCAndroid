package corp.wmsoft.android.examples.mvpc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import corp.wmsoft.android.lib.mvpcandroid.support.v7.app.MVPCAppCompatActivity;


public class MainActivity extends MVPCAppCompatActivity<IMainView, IMainPresenter> implements IMainView {

    /**/
    TextView mTextView;
    /**/
    Menu mMenu;


    @NonNull
    @Override
    protected IMainPresenter providePresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(android.R.id.text1);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onMessageTap();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        this.mMenu = menu;
        return true;
    }

    @Override
    public void showMessage(String message) {
        mTextView.setText(message);
    }

    @Override
    public void setTest1Visibility(boolean isVisible) {
        mMenu.findItem(R.id.action_test1).setVisible(isVisible);
        mMenu.findItem(R.id.action_test2).setVisible(!isVisible);
    }
}
