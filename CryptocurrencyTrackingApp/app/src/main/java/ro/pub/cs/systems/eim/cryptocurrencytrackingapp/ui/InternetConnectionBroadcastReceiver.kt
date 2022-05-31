package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class InternetConnectionBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isInternetConnectionDisabled =
            intent?.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, true) ?: return

        if (isInternetConnectionDisabled) {
            Toast.makeText(context, "Internet Connection Disabled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Internet Connection Enabled", Toast.LENGTH_LONG).show()
        }
    }
}