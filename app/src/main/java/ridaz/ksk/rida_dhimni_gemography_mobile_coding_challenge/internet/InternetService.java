package ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.Calendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Rida Dhimni
 * 25/01/2021
 **/


public class InternetService {

    private MutableLiveData<Boolean> NetworkState = new MutableLiveData<>();
    private Context context;
    private NetWorkInfoUtility netWorkInfoUtility;
    private static final String TAG = "InternetService";

    private static final InternetService instance = new InternetService();

    public void init(Context context) {
        this.context = context;
        netWorkInfoUtility = new NetWorkInfoUtility();
    }

    public static synchronized InternetService getInstance() {
        return instance;
    }

    public void execute() {
        /*
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                checkInternet();
                handler.postDelayed(this, 1000);
            }
        };

        runnable.run();

         */

        checkInternet();

    }

    public void checkInternet() {

        netWorkInfoUtility.isNetWorkAvailableNow(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> NetworkState.setValue(res),
                        err -> Log.d(TAG, "getPhotos: " + err)
                );

    }

    public MutableLiveData<Boolean> getNetworkState() {
        return NetworkState;
    }
}

/**
 * ~~~~~~~~~~~~NetWorkInfoUtility~~~~~~~~~~~~
 */

class NetWorkInfoUtility {

    public boolean isWifiEnable() {
        return isWifiEnable;
    }

    public void setIsWifiEnable(boolean isWifiEnable) {
        this.isWifiEnable = isWifiEnable;
    }

    public boolean isMobileNetworkAvailable() {
        return isMobileNetworkAvailable;
    }

    public void setIsMobileNetworkAvailable(boolean isMobileNetworkAvailable) {
        this.isMobileNetworkAvailable = isMobileNetworkAvailable;
    }

    private boolean isWifiEnable = false;
    private boolean isMobileNetworkAvailable = false;

    public Observable<Boolean> isNetWorkAvailableNow(Context context) {
        boolean isNetworkAvailable = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        setIsWifiEnable(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected());
        setIsMobileNetworkAvailable(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected());

        if (isWifiEnable() || isMobileNetworkAvailable()) {
            /*Sometime wifi is connected but service provider never connected to internet
            so cross check one more time*/
            if (isOnline())
                isNetworkAvailable = true;
        }

        return Observable.just(isNetworkAvailable);
    }

    public boolean isOnline() {
        /*Just to check Time delay*/
        long t = Calendar.getInstance().getTimeInMillis();

        Runtime runtime = Runtime.getRuntime();
        try {
            /*Pinging to Google server*/
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            long t2 = Calendar.getInstance().getTimeInMillis();
            Log.i("NetWork check Time", (t2 - t) + "");
        }
        return false;
    }
}