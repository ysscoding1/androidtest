package dnr.capitalone.com.dealandreward;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.graphics.Color;
import java.util.Map;
import android.util.Log;

public class WalletActivity extends ActionBarActivity {

    Drawable drawable;
    Button button;
    ScaleDrawable sd;
    ImageButton imgButton;
    LinearLayout mainLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_wallet);

        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(
                "walletPrefFiles", Context.MODE_PRIVATE);
        Map <String, ?> prefFilesMap = sharedPref.getAll();
        String couponName;
        String couponDescription;
        String couponImage;
        /*mainLinearLayout = (LinearLayout) findViewById(R.id.mainLevelWallet);

        Button buttonToAdd = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1000, 200);
        params.gravity=Gravity.CENTER;
        buttonToAdd.setLayoutParams(params);
        buttonToAdd.setBackgroundResource(R.drawable.red_coupon_top);
        buttonToAdd.setText("Hello");
        buttonToAdd.setGravity(Gravity.RIGHT);
        buttonToAdd.setTextSize(20);
        buttonToAdd.setAllCaps(false);
        buttonToAdd.setTextColor(Color.parseColor("#FFFFFF"));

        mainLinearLayout.addView(buttonToAdd, 0);*/

        mainLinearLayout = (LinearLayout) findViewById(R.id.mainLevelWallet);
        Button buttonToAdd = new Button(this);
        if (prefFilesMap.isEmpty() != true) {
            Log.d("MyApp", "Inside!");
            couponName = sharedPref.getString("couponID_Name", "0");
            couponDescription = sharedPref.getString("couponID_Description", "0");
            couponImage = sharedPref.getString("couponID_Image", "0");
            //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1000, 200);
            Log.d("MyApp", "Inside!");

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1000, 200);
            params.gravity=Gravity.CENTER;
            buttonToAdd.setLayoutParams(params);
            buttonToAdd.setBackgroundResource(R.drawable.red_coupon_top);
            buttonToAdd.setText(couponDescription);
            buttonToAdd.setGravity(Gravity.RIGHT);
            buttonToAdd.setTextSize(20);
            buttonToAdd.setAllCaps(false);
            buttonToAdd.setTextColor(Color.parseColor("#FFFFFF"));

            mainLinearLayout.addView(buttonToAdd, 0);
        }

        //setContentView(R.layout.activity_wallet);

        /* Body */
        /*drawable = getResources().getDrawable(R.drawable.hm_icon);
        drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * 0.25),
                (int) (drawable.getIntrinsicHeight() * 0.25));
        sd = new ScaleDrawable(drawable, 0, 70, 70);
        button = (Button) findViewById(R.id.hmButton);
        button.setCompoundDrawables(sd.getDrawable(), null, null, null); //set drawableLeft for example

        drawable = getResources().getDrawable(R.drawable.starbucks_icon);
        drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * 0.25),
                (int) (drawable.getIntrinsicHeight() * 0.25));
        sd = new ScaleDrawable(drawable, 0, 70, 70);
        button = (Button) findViewById(R.id.starbucksButton);
        button.setCompoundDrawables(sd.getDrawable(), null, null, null); //set drawableLeft for example

        drawable = getResources().getDrawable(R.drawable.walmart_icon);
        drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * 0.25),
                (int) (drawable.getIntrinsicHeight() * 0.25));
        sd = new ScaleDrawable(drawable, 0, 70, 70);
        button = (Button) findViewById(R.id.walmartButton);
        button.setCompoundDrawables(sd.getDrawable(), null, null, null); //set drawableLeft for example

        drawable = getResources().getDrawable(R.drawable.pizzahut_icon);
        drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * 0.25),
                (int) (drawable.getIntrinsicHeight() * 0.25));
        sd = new ScaleDrawable(drawable, 0, 70, 70);
        button = (Button) findViewById(R.id.pizzahutButton);
        button.setCompoundDrawables(sd.getDrawable(), null, null, null); //set drawableLeft for example

        drawable = getResources().getDrawable(R.drawable.bestbuy_icon);
        drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * 0.25),
                (int) (drawable.getIntrinsicHeight() * 0.25));
        sd = new ScaleDrawable(drawable, 0, 70, 70);
        button = (Button) findViewById(R.id.bestbuyButton);
        button.setCompoundDrawables(sd.getDrawable(), null, null, null); //set drawableLeft for example
        */

        /* Footer */
        imgButton =(ImageButton)findViewById(R.id.shareButton);
        imgButton.setOnTouchListener(new ButtonHighlighterOnTouchListener(imgButton));
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Toast.makeText(getApplicationContext(), "You download is resumed", Toast.LENGTH_LONG).show();*/

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                i.putExtra(Intent.EXTRA_TEXT, "http://www.url.com");
                startActivity(Intent.createChooser(i, "Share URL"));
            }
        });

        imgButton = (ImageButton)findViewById(R.id.moneyButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletActivity.this, MoneyEarnedActivity.class);
                startActivity(i);
            }
        });

        imgButton = (ImageButton)findViewById(R.id.homeButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), dealMainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wallet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
