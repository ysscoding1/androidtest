package dnr.capitalone.com.dealandreward;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by RichardYan on 7/21/15.
 */
public class RewardsInfoActivity extends Activity {
    TextView textView;
    ImageButton imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_info);

        imgButton = (ImageButton) findViewById(R.id.totalEarnedInfoButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RewardsInfoActivity.this, MoneyEarnedActivity.class);
                startActivity(i);
            }
        });
    }
}
