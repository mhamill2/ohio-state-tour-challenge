package osu.mobile_apps.ohiostatetourchallenge;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

class Login {
    static GoogleApiClient mGoogleApiClient;
    static GoogleSignInOptions mGoogleSignInOptions;
    static GoogleSignInAccount mGoogleSignInAccount;
    static GoogleSignInResult mGoogleSignInResult;
}
