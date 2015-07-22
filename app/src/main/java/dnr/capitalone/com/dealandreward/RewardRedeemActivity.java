package dnr.capitalone.com.dealandreward;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

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
        String value = "";

        Bundle extras = getIntent().getExtras();
        String urlString = "http://52.5.81.122:8080/retreive/image/grocery/";
        if (extras != null) {
            value = extras.getString("couponToRedeem");
            urlString += value;
            ImageView imgView = (ImageView) findViewById(R.id.couponstodisplay);
            new DownloadImageTask(imgView)
                    .execute(urlString);
        }

        Button button =(Button) findViewById(R.id.clipCouponButton);
        button.setOnClickListener(new SCListener(value));
    }


    public class SCListener implements View.OnClickListener
    {

        String selectedCoupon;
        public SCListener(String selectedCoupon) {
            this.selectedCoupon = selectedCoupon;
        }

        @Override
        public void onClick(View v)
        {
            try{
            //call the redeem service
            String urlString = "http://52.5.81.122:8080/retreive/image/grocery/"+selectedCoupon;
            InputStream in = null;
            java.net.URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            String SetServerString = "";
            in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (Throwable t) {
                // just end the background thread
                Log.i("Animation", "Thread  exception " + t);
            }
        }

    }
}
