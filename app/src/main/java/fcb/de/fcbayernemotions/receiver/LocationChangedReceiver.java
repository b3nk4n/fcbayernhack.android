package fcb.de.fcbayernemotions.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fcb.de.fcbayernemotions.notification.SimpleNotification;

/**
 * We use the location changed event to trigger a notification for testing purposes.
 */

public class LocationChangedReceiver extends BroadcastReceiver {
    SimpleNotification simpleNotification;

    @Override
    public void onReceive(Context context, Intent intent) {
        simpleNotification = new SimpleNotification(context);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        simpleNotification.show(1, "Müller scored 1:0 against Bremen.\nThe world is freaking out!");
    }
}
