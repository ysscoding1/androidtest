package dnr.capitalone.com.dealandreward;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.transition.TransitionManager;
import android.view.animation.Animation;
import android.content.SharedPreferences;


public class dealMainActivity extends ActionBarActivity {

    ImageButton imgButton;
    Button button;
    ImageButton couponButton;
    ViewGroup restaurantsLayout;
    Typeface buttonTypeface;
    TextView textview;
    Typeface headerTypeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Facebook.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_deal_main);

        /* TODO: CHANGE~~   Clear When Coupon is Used or Deleted */
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(
                "walletPrefFiles", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();

        /* Header */
        textview = (TextView) findViewById(R.id.headerText);
        headerTypeface= Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
        textview.setTypeface(headerTypeface);

        /* Body */
        button = (Button)findViewById(R.id.restaurantButton);
        buttonTypeface = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
        button.setTypeface(buttonTypeface);
        button.setOnTouchListener(new ButtonPushedOnTouchListener(button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dealMainActivity.this, RestaurantCouponActivity.class);
                startActivity(i);
            }
        });

        button = (Button)findViewById(R.id.retailersButton);
        buttonTypeface = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
        button.setTypeface(buttonTypeface);
        button.setOnTouchListener(new ButtonPushedOnTouchListener(button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dealMainActivity.this, RetailerCouponActivity.class);
                startActivity(i);
            }
        });


        button = (Button)findViewById(R.id.servicesButton);
        buttonTypeface = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
        button.setTypeface(buttonTypeface);
        button.setOnTouchListener(new ButtonPushedOnTouchListener(button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dealMainActivity.this, ServiceCouponActivity.class);
                startActivity(i);
            }
        });


        button = (Button)findViewById(R.id.otherButton);
        buttonTypeface = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
        button.setTypeface(buttonTypeface);
        button.setOnTouchListener(new ButtonPushedOnTouchListener(button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dealMainActivity.this, OtherCouponActivity.class);
                startActivity(i);
            }
        });

        button = (Button)findViewById(R.id.rewardsButton);
        buttonTypeface = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
        button.setTypeface(buttonTypeface);
        button.setOnTouchListener(new ButtonPushedOnTouchListener(button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dealMainActivity.this, MoneyEarnedActivity.class);
                startActivity(i);
            }
        });

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

        /*imgButton = (ImageButton)findViewById(R.id.moneyButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dealMainActivity.this, MoneyEarnedActivity.class);
                startActivity(i);
            }
        });*/

        imgButton = (ImageButton)findViewById(R.id.walletButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dealMainActivity.this, WalletActivity.class);
                startActivity(i);
            }
        });

        /*restaurantsLayout = (ViewGroup)findViewById(R.id.restaurantsLayout);
        restaurantsLayout.setOnTouchListener(new RelativeLayout.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                moveButton();
                return true;
            }
        });*/
    }

    /*public void moveButton(){
        View button = findViewById(R.id.imageButton);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.push_button);
        button.startAnimation(anim);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deal_main, menu);
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
