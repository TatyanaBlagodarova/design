package com.material.tblagodarova.design.ui;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;

import com.material.tblagodarova.design.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

import timber.log.Timber;

/**
 * Created by tblagodarova on 7/21/15.
 */
@EActivity
public class GoogleSignInActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    @ViewById
    SignInButton sign_in_button;

    private static final int REQUEST_CODE_RESOLVE_ERR = 9000;

    private ProgressDialog mConnectionProgressDialog;

    private ConnectionResult mConnectionResult;
    private GoogleApiClient mGoogleApiClient;
    private String userEmail
            ;

    @Click({R.id.sign_in_button})
    void signIn() {
        if (!mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlesignin);
        mGoogleApiClient = buildGoogleApiClient();


    }

    private GoogleApiClient buildGoogleApiClient() {
        // When we build the GoogleApiClient we specify where connected and
        // connection failed callbacks should be returned, which Google APIs our
        // app uses and which OAuth 2.0 scopes our app requests.
        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN);

        return builder.build();
    }

    @Override
    public void onConnected(Bundle bundle) {

        Person currentUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
         userEmail = Plus.AccountApi.getAccountName(mGoogleApiClient);
        Timber.v("onConnected user=" + currentUser.getDisplayName() + "userEmail " + userEmail);

        AsyncTask task = new GetUsernameTask(GoogleSignInActivity.this,
                userEmail, "oauth2:https://mail.google.com/")
                .execute();


    }

    @Override
    public void onConnectionSuspended(int i) {
        Timber.v("onConnectionSuspended i=" + i);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Timber.v("onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode() + connectionResult.toString());
        PendingIntent mSignInIntent = connectionResult.getResolution();
        if (mSignInIntent != null) {
            // We have an intent which will allow our user to sign in or
            // resolve an error.  For example if the user needs to
            // select an account to sign in with, or if they need to consent
            // to the permissions your app is requesting.

            try {
                // Send the pending intent that we stored on the most recent
                // OnConnectionFailed callback.  This will allow the user to
                // resolve the error currently preventing our connection to
                // Google Play services.

                startIntentSenderForResult(mSignInIntent.getIntentSender(),
                        0, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                Log.i("Actib", "Sign in intent could not be sent: "
                        + e.getLocalizedMessage());
                // The intent was canceled before it was sent.  Attempt to connect to
                // get an updated ConnectionResult.

                mGoogleApiClient.connect();
            }
        } else {
            // Google Play services wasn't able to provide an intent for some
            // error types, so we show the default Google Play services error
            // dialog which may still start an intent on our behalf if the
            // user can resolve the issue.
            Log.i("Actib", "Google Play services wasn't able to provide an intent");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        switch (requestCode) {
            case 0:
//                if (resultCode == RESULT_OK) {
//                    // If the error resolution was successful we should continue
//                    // processing errors.
//                    mSignInProgress = STATE_SIGN_IN;
//                } else {
//                    // If the error resolution was not successful or the user canceled,
//                    // we should stop processing errors.
//                    mSignInProgress = STATE_DEFAULT;
//                }

                if (!mGoogleApiClient.isConnecting()) {
                    // If Google Play services resolved the issue with a dialog then
                    // onStart is not called so we need to re-attempt connection here.
                    mGoogleApiClient.connect();
                }

            case 125:
                AsyncTask task = new GetUsernameTask(GoogleSignInActivity.this,
                        userEmail, "oauth2:https://mail.google.com/")
                        .execute();
                break;
        }
    }

    public class GetUsernameTask extends AsyncTask {
        Activity mActivity;
        String mScope;
        String mEmail;

        GetUsernameTask(Activity activity, String name, String scope) {
            this.mActivity = activity;
            this.mScope = scope;
            this.mEmail = name;
        }


        /**
         * Gets an authentication token from Google and handles any
         * GoogleAuthException that may occur.
         */
        protected String fetchToken() throws IOException {
            try {
                return GoogleAuthUtil.getToken(mActivity, mEmail, mScope);
            } catch (UserRecoverableAuthException userRecoverableException) {
                // GooglePlayServices.apk is either old, disabled, or not present
                // so we need to show the user some UI in the activity to recover.
//                mActivity.handleException(userRecoverableException);
            } catch (GoogleAuthException fatalException) {
                // Some other type of unrecoverable exception has occurred.
                // Report and log the error as appropriate for your app.
                fatalException.printStackTrace();
            }
            return null;
        }

        /**
         * Executes the asynchronous job. This runs when you call execute()
         * on the AsyncTask instance.
         */
        @Override
        protected Object doInBackground(Object[] params) {
            String accessToken = "";
            try {
                URL url = new URL("https://www.googleapis.com/oauth2/v1/userinfo");
                // get Access Token with Scopes.PLUS_PROFILE

                String scope = "oauth2:" + "https://www.googleapis.com/auth/books" + " " + "https://www.googleapis.com/auth/plus.login" + " "
                        + "https://www.googleapis.com/auth/calendar";

                String sAccessToken;
                sAccessToken = GoogleAuthUtil.getToken(
                        GoogleSignInActivity.this,
                        mEmail + "", scope);

                Timber.v("token = we got" + sAccessToken.length() + " length" + " " + sAccessToken);

            } catch (UserRecoverableAuthException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Intent recover = e.getIntent();
                startActivityForResult(recover, 125);
                return "";
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (GoogleAuthException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }
    }

}
