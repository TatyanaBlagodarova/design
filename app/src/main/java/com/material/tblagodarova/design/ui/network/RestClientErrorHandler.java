package com.material.tblagodarova.design.ui.network;

import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;

import java.util.Timer;

import timber.log.Timber;

/**
 * Created by tblagodarova on 7/17/15.
 */
@EBean
public class RestClientErrorHandler implements RestErrorHandler {

    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        Timber.v("" + e.getMessage());
    }
}