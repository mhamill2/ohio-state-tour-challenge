package osu.mobile_apps.ohiostatetourchallenge;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class InformationActivityFragment extends Fragment {

    public InformationActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TESTING", "InformationFragment onCreate()");
        //TextView Test = (TextView) getView().findViewById(R.id.test);
        //Log.d("TESTING", "DATA FROM GET " + Test.getText());
        return inflater.inflate(R.layout.fragment_information, container, false);
    }
}
