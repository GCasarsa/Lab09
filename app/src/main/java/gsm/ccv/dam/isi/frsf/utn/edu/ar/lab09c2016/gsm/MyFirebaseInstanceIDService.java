package gsm.ccv.dam.isi.frsf.utn.edu.ar.lab09c2016.gsm;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{
    private static final String TAG = "MyFirebaseIIDService";
    @Override public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Refreshedtoken: SERVICIO CREADO!!!!");
    }
    @Override public void onTokenRefresh() {
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshedtoken: " + refreshedToken);
        saveTokenToPrefs(refreshedToken);
    }
    private void saveTokenToPrefs(String _token){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("registration_id", _token);
        editor.apply();
    }
    private String getTokenFromPrefs(){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("registration_id", null);
    }

}
