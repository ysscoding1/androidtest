package dnr.capitalone.com.dealandreward;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class WalletActivity extends ActionBarActivity {

    Drawable drawable;
    Button button;
    ScaleDrawable sd;
    ImageButton imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_wallet);
        //setContentView(R.layout.activity_wallet);

        /* Body */
        drawable = getResources().getDrawable(R.drawable.hm_icon);
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
