package dnr.capitalone.com.dealandreward;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by RichardYan on 6/27/15.
 */
public class MoneyEarnedActivity extends Activity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_earned);

        /*textView = (TextView) findViewById(R.id.totalEarned);
        textView.setText(Html.fromHtml(
                "<sup>$</sup>1,069<sup>92</sup>"
        ));*/

    }
}
