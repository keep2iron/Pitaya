package io.github.keep2iron.api.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;

import io.github.keep2iron.api.Pitaya;
import io.github.keep2iron.api.RequestWrapper;
import io.github.keep2iron.api.ResultEventBus;
import io.github.keep2iron.api.ResultWrapper;
import io.github.keep2iron.pitaya.annntation.RouteAnim;

/**
 * @author keep2iron <a href="http://keep2iron.github.io">Contract me.</a>
 * @version 1.0
 * @since 2017/11/14 16:00
 */
public class SupportFragment extends Fragment {

    private RequestWrapper mRequestWrapper;
    private ResultEventBus mBus;

    public void setBus(ResultEventBus bus) {
        mBus = bus;
    }

    public void setRequestWrapper(RequestWrapper requestWrapper) {
        mRequestWrapper = requestWrapper;
    }


    public static SupportFragment getInstance(RequestWrapper requestWrapper, ResultEventBus bus) {
        SupportFragment fragment = new SupportFragment();
        fragment.setRequestWrapper(requestWrapper);
        fragment.setBus(bus);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mRequestWrapper.mPostcard
                .navigation(this, Pitaya.DEFAULT_REQUEST_CODE, new NavCallback() {
                    @Override
                    public void onLost(Postcard postcard) {
                        // Do nothing
                        mBus.post(new ResultWrapper(null, mRequestWrapper.requestCode, -1));
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        // Do nothing
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mBus.post(new ResultWrapper(data, requestCode, resultCode));
    }


}
