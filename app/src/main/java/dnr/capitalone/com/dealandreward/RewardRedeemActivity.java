package dnr.capitalone.com.dealandreward;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ZGP046 on 7/22/2015.
 */
public class RewardRedeemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_redeem);
        String clickedCouponID = "";

        Intent i = getIntent();

        Bundle extras = getIntent().getExtras();
        String urlString = "http://52.5.81.122:8080/retreive/image/grocery/";
        if (extras != null) {
            clickedCouponID = i.getStringExtra("clickedCouponID");
            urlString += clickedCouponID;
            ImageView imgView = (ImageView) findViewById(R.id.couponstoRedeem);
            new DownloadImageTask(imgView)
                    .execute(urlString);
        }

        Button button = (Button) findViewById(R.id.clipCouponButton);
        button.setOnClickListener(new SCListener(clickedCouponID));
    }


    public class SCListener implements View.OnClickListener {

        String clickedCouponID;

        public SCListener(String clickedCouponID) {
            this.clickedCouponID = clickedCouponID;
        }

        @Override
        public void onClick(View v) {
            try {


                SharedPreferences sharedPref = getBaseContext().getSharedPreferences(
                        "dnrLoginPrefFiles", Context.MODE_PRIVATE);

                String email = sharedPref.getString("username", "bkadali@gmail.com");

                // Create Inner Thread Class
                Thread background = new Thread(new UIRedeemThread(clickedCouponID, email));
                background.start();
            /*
            String SetServerString = "";
            in = new BufferedInputStream(urlConnection.getInputStream());*/
            } catch (Throwable t) {
                // just end the background thread
                Log.i("Animation", "Thread  exception " + t);
            }
        }

    }

    public class UIRedeemThread implements Runnable {
        private LayoutInflater inflater;
        private String couponId;
        private String email;

        public UIRedeemThread(String _couponId, String _email) {
            this.couponId = _couponId;
            this.email = _email;
        }


        // After call for background.start this run method call
        public void run() {


            //call the redeem service
            String urlString = "http://52.5.81.122:8080/redeem/coupon/" + couponId + "/" + email;

            try{
                java.net.URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                Log.d("myURL response code", String.valueOf(urlConnection.getResponseCode()));
                Log.d("myURL", urlString);
            }catch (Throwable t) {
                // just end the background thread
                Log.i("Animation", "Thread  exception " + t);
            }


        }
    }
}
