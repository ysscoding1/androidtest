package dnr.capitalone.com.dealandreward;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import static com.google.android.gms.wearable.PutDataRequest.WEAR_URI_SCHEME;

/**
 * Created by Divya on 7/25/2015.
 */
public class NotificationUpdateService extends WearableListenerService
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<DataApi.DeleteDataItemsResult> {

    private static final String TAG = "NotificationUpdate";
    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        buildWearableOnlyNotification("hello","",null,false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null != intent) {
            String action = intent.getAction();
            if (Constants.ACTION_DISMISS.equals(action)) {
                // We need to dismiss the wearable notification. We delete the data item that
                // created the notification and that is how we inform the phone
                int notificationId = intent.getIntExtra(Constants.KEY_NOTIFICATION_ID, -1);
                if (notificationId == Constants.BOTH_ID) {
                    dismissPhoneNotification(notificationId);
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Dismisses the phone notification, via a {@link android.app.PendingIntent} that is triggered
     * when the user dismisses the local notification. Deleting the corresponding data item notifies
     * the {@link com.google.android.gms.wearable.WearableListenerService} on the phone that the
     * matching notification on the phone side should be removed.
     */
    private void dismissPhoneNotification(int id) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() == DataEvent.TYPE_CHANGED) {
                DataMap dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap();
                String content = dataMap.getString(Constants.KEY_CONTENT);
                String title = dataMap.getString(Constants.KEY_TITLE);
                Asset profileAsset = dataMap.getAsset("cimage");
                Bitmap bitmap = loadBitmapFromAsset(profileAsset);
              //  if (Constants.WATCH_ONLY_PATH.equals(dataEvent.getDataItem().getUri().getPath())) {
                    buildWearableOnlyNotification(title, content, bitmap, false);
               // } else if (Constants.BOTH_PATH.equals(dataEvent.getDataItem().getUri().getPath())) {
               //     buildWearableOnlyNotification(title, content, true);
               // }
            } else if (dataEvent.getType() == DataEvent.TYPE_DELETED) {
                if (Log.isLoggable(TAG, Log.DEBUG)) {
                    Log.d(TAG, "DataItem deleted: " + dataEvent.getDataItem().getUri().getPath());
                }
                if (Constants.BOTH_PATH.equals(dataEvent.getDataItem().getUri().getPath())) {
                    // Dismiss the corresponding notification
                    ((NotificationManager) getSystemService(NOTIFICATION_SERVICE))
                            .cancel(Constants.WATCH_ONLY_ID);
                }
            }
        }
    }


    public Bitmap loadBitmapFromAsset(Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("Asset must be non-null");
        }
        ConnectionResult result =
                mGoogleApiClient.blockingConnect(100000, TimeUnit.MILLISECONDS);
        if (!result.isSuccess()) {
            return null;
        }
        // convert asset into a file descriptor and block until it's ready
        InputStream assetInputStream = Wearable.DataApi.getFdForAsset(
                mGoogleApiClient, asset).await().getInputStream();
        mGoogleApiClient.disconnect();

        if (assetInputStream == null) {
            Log.w(TAG, "Requested an unknown Asset.");
            return null;
        }
        // decode the stream into a bitmap
        return BitmapFactory.decodeStream(assetInputStream);
    }

    /**
     * Builds a simple notification on the wearable.
     */
    private void buildWearableOnlyNotification(String title, String content, Bitmap bm,
                                               boolean withDismissal) {

       /* // Create a pending intent that starts this wearable app
        Intent startIntent = new Intent(this, dealMainActivity.class).setAction(Intent.ACTION_MAIN);
        // Add extra data for app startup or initialization, if available
        startIntent.putExtra("extra", title);
        PendingIntent startPendingIntent =
                PendingIntent.getActivity(this, 0, startIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notify = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.walleticon)
                .setAutoCancel(true)
                .setLargeIcon(bm)
                .setContentIntent(startPendingIntent)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(001, notify);*/


        // Main Notification Object
        NotificationCompat.Builder wearNotificaiton = new NotificationCompat.Builder(this)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.walleticon)

                .setTicker("Wallet Icon")
                .setContentTitle("Text 1")
                .setContentText("Hello")
                .setGroup("COUPONLIST");

        bm = BitmapFactory.decodeResource(getResources(), R.drawable.walleticon);
        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.walleticon);

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setBackground(bm);

        // Create a NotificationCompat.Builder to build a standard notification
// then extend it with the WearableExtender
        Notification notif = new NotificationCompat.Builder(this)
                .setContentTitle("New mail from srikanth from wearable")
                .setContentText("Hello srikanth")
                .setSmallIcon(R.drawable.walleticon)
                .extend(wearableExtender)
                .build();

        // Create second page
        Notification TrendPage =
                new NotificationCompat.Builder(this)
                        .setLargeIcon(bm)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bm))
                        .build();

        // Create third page
        Notification ChartPage =
                new NotificationCompat.Builder(this)
                        .setLargeIcon(bm1)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bm1))
                        .setContentTitle("test title 1")
                        .build();

        // Extend the notification builder with the second page
        Notification notification = wearNotificaiton
                .extend(new NotificationCompat.WearableExtender()
                        .addPage(TrendPage).addPage(ChartPage))
                .build();

        // Issue the notification
        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(getApplicationContext());
        notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(01, notif);

      /*  if (withDismissal) {
            Intent dismissIntent = new Intent(Constants.ACTION_DISMISS);
            dismissIntent.putExtra(Constants.KEY_NOTIFICATION_ID, Constants.BOTH_ID);
            PendingIntent pendingIntent = PendingIntent
                    .getService(this, 0, dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setDeleteIntent(pendingIntent);
        }

        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE))
                .notify(Constants.WATCH_ONLY_ID, builder.build());*/
    }

    @Override
    public void onConnected(Bundle bundle) {
        buildWearableOnlyNotification("hello","",null,false);
        final Uri dataItemUri =
                new Uri.Builder().scheme(WEAR_URI_SCHEME).path(Constants.BOTH_PATH).build();
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "Deleting Uri: " + dataItemUri.toString());
        }
        Wearable.DataApi.deleteDataItems(
                mGoogleApiClient, dataItemUri).setResultCallback(this);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onResult(DataApi.DeleteDataItemsResult deleteDataItemsResult) {
        if (!deleteDataItemsResult.getStatus().isSuccess()) {
            Log.e(TAG, "dismissWearableNotification(): failed to delete DataItem");
        }
        mGoogleApiClient.disconnect();
    }
}

