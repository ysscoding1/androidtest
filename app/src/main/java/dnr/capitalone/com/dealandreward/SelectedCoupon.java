package dnr.capitalone.com.dealandreward;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class SelectedCoupon extends ActionBarActivity {
    ImageView imageView;
    ImageButton imgButton;
    LinearLayout mainLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_coupons);


        Bundle extras = getIntent().getExtras();
        String urlString = "http://52.5.81.122:8080/retreive/image/grocery/";
        if (extras!=null)
        {
            String value = extras.getString("couponSelected");
            urlString+=value;
            ImageView imgView = (ImageView) findViewById(R.id.couponstodisplay);
            new DownloadImageTask(imgView)
                    .execute(urlString);


            // imgView.setImageResource(getResources().getIdentifier(value,"drawable", getPackageName()));
        }
      /*  mainLinearLayout = (LinearLayout) findViewById(R.id.mainLevel);

        imgButton = (ImageButton) findViewById(R.id.clipCouponButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getBaseContext().getSharedPreferences(
                        "walletPrefFiles", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("couponID_Name", "T.G.I.F.");   // NEED INPUT FROM DB
                editor.putString("couponID_Description", "$20 off Purchase of $10"); // NEED INPUT FROM DB
                editor.putString("couponID_Image", "tgifcoupon");
                editor.commit();
            }
        });
*/

        imgButton =(ImageButton) findViewById(R.id.walletButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectedCoupon.this, WalletActivity.class);
                startActivity(i);
            }
        });

        imgButton =(ImageButton) findViewById(R.id.homeButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectedCoupon.this, dealMainActivity.class);
                startActivity(i);
            }
        });

        imgButton =(ImageButton)findViewById(R.id.shareButton);
        imgButton.setOnTouchListener(new ButtonHighlighterOnTouchListener(imgButton));
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Toast.makeText(getApplicationContext(), "You download is resumed", Toast.LENGTH_LONG).show();*/

               /* Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                i.putExtra(Intent.EXTRA_TEXT, "http://www.url.com");
                startActivity(Intent.createChooser(i, "Share URL"));*/

                ArrayList<Uri> imageUris = new ArrayList<Uri>();
                // imageUris.add(R.drawable.c1);

                Resources resources = getResources();
                Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(R.drawable.c1) + '/' + resources.getResourceTypeName(R.drawable.c1) + '/' + resources.getResourceEntryName(R.drawable.c1));
                imageUris.add(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(R.drawable.c1) + '/' + resources.getResourceTypeName(R.drawable.c1) + '/' + resources.getResourceEntryName(R.drawable.c1)));

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, imageUris);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_TEXT, "Download CapitalOne Deals 'n Rewards App \n http://capitalone.com/Apps/DealsnRewards/U1CX7D");
                startActivity(Intent.createChooser(intent,"compatible apps:"));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selected_coupon, menu);
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
