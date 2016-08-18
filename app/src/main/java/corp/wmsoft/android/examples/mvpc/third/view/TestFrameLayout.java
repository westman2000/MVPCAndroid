package corp.wmsoft.android.examples.mvpc.third.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.FrameLayout;

import corp.wmsoft.android.examples.mvpc.R;
import corp.wmsoft.android.lib.mvpc.delegate.IMVPCDelegate;
import corp.wmsoft.android.lib.mvpc.delegate.MVPCDelegate;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;


/**
 * Created by admin on 8/6/16.
 *
 */
public class TestFrameLayout extends FrameLayout implements FrameLayoutContract.View, IMVPCDelegate.ICallback<FrameLayoutContract.View, FrameLayoutContract.Presenter> {

    /**/
    private TextView mTextView;
    /**/
    private MVPCDelegate<FrameLayoutContract.View, FrameLayoutContract.Presenter> mMvpcDelegate;


    public TestFrameLayout(Context context) {
        super(context);
        init(context);
    }

    public TestFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TestFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TestFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    public void onInitializePresenter(FrameLayoutContract.Presenter presenter) {
        // hook
    }

    @Override
    public void onDestroyPresenter() {
        // hook
    }

    @CallSuper
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getMvpсDelegate().onAttachView(this, this);
    }

    @CallSuper
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getMvpсDelegate().onDetachView();
    }

    @Override
    public void showCounter(String count) {
        mTextView.setText(count);
    }

    private IMVPCPresenterFactory<FrameLayoutContract.View, FrameLayoutContract.Presenter> providePresenterFactory() {
        return new FrameLayoutPresenterFactory();
    }

    private FrameLayoutContract.Presenter getPresenter() {
        return getMvpсDelegate().getPresenter();
    }

    /**
     * @return The {@link MVPCDelegate} being used by this Fragment.
     */
    private MVPCDelegate<FrameLayoutContract.View, FrameLayoutContract.Presenter> getMvpсDelegate() {
        if (mMvpcDelegate == null) {
            mMvpcDelegate = new MVPCDelegate<>();
        }
        return mMvpcDelegate;
    }

    private void init(Context context) {

        View rootView = inflate(getContext(), R.layout.frame_layout_test, null);

        mTextView = (TextView) rootView.findViewById(R.id.counter);
        Button button = (Button) rootView.findViewById(R.id.buttonCount);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onCount();
            }
        });

        addView(rootView);

        getMvpсDelegate().onCreate(context, ((AppCompatActivity)context).getSupportLoaderManager(), providePresenterFactory(), 123, this);
    }

}