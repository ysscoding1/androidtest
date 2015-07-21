package dnr.capitalone.com.dealandreward;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;

/**
 * Created by RichardYan on 6/27/15.
 */
public class MoneyEarnedActivity extends Activity {

    TextView textView;
    ImageButton imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_earned);

        imgButton = (ImageButton) findViewById(R.id.totalEarnedInfoButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoneyEarnedActivity.this, RewardsInfoActivity.class);
                startActivity(i);
            }
        });
        /*textView = (TextView) findViewById(R.id.totalEarned);
        textView.setText(Html.fromHtml(
                "<sup>$</sup>1,069<sup>92</sup>"
        ));*/

    }
}
