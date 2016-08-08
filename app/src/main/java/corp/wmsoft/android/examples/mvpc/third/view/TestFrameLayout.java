package corp.wmsoft.android.examples.mvpc.third.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import corp.wmsoft.android.examples.mvpc.R;
import corp.wmsoft.android.lib.mvpcandroid.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpcandroid.base.MVPCFrameLayout;


/**
 * Created by admin on 8/6/16.
 *
 */
public class TestFrameLayout extends MVPCFrameLayout<FrameLayoutContract.View, FrameLayoutContract.Presenter> implements FrameLayoutContract.View {

    private TextView mTextView;


    public TestFrameLayout(Context context) {
        super(context);
        init();
    }

    public TestFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TestFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected IMVPCPresenterFactory<FrameLayoutContract.View, FrameLayoutContract.Presenter> providePresenterFactory() {
        return new FrameLayoutPresenterFactory();
    }

    @Override
    protected void onInitializePresenter(FrameLayoutContract.Presenter presenter) {
    }

    @Override
    protected int provideUniqueIdentifier() {
        return 1234;
    }

    @Override
    public void showCounter(String count) {
        mTextView.setText(count);
    }

    private void init() {
        View rootView = inflate(getContext(), R.layout.frame_layout_test, null);

        mTextView = (TextView) rootView.findViewById(R.id.counter);
        Button button = (Button) rootView.findViewById(R.id.buttonCount);

        Log.d("life_cycle", "mTextView="+mTextView);
        Log.d("life_cycle", "button="+button);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("life_cycle", "onClick("+view+") -- "+getPresenter());
                getPresenter().onCount();
            }
        });

        addView(rootView);
    }
}