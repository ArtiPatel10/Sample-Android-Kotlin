package com.sample.helper

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.ContentLoadingProgressBar
import java.io.InputStream
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object AppMethods {

    /**
     * This method is used to enable the button, change alpha and progress
     */
    fun setInputButtonEnable(view: View, progress: ContentLoadingProgressBar) {
        progress.visibility = View.GONE
        view.isEnabled = true
        view.alpha = 1f
    }

    /**
     * This method is used to disable the button, change alpha and progress
     */
    fun setInputButtonDisable(view: View, progress: ContentLoadingProgressBar) {
        progress.visibility = View.VISIBLE
        view.isEnabled = false
        view.alpha = 0.5f
    }

    /**
     * This method is used to only enable the button and change alpha
     * @param view
     */
    fun setInputButtonEnable(view: View) {
        view.isEnabled = true
        view.alpha = 1f
    }

    /**
     * This method is used to only disable the button and change alpha
     * @param view
     */
    fun setInputButtonDisable(view: View) {
        view.isEnabled = false
        view.alpha = 0.5f
    }

    /**
     * This method is used to enable the loader and disable the button with alpha
     */
    fun setLoaderEnable(btnView: View, progress: View) {
        progress.visibility = View.VISIBLE
        btnView.isEnabled = false
        btnView.alpha = 0.5f
    }

    /**
     * This method is used to disable the loader and enable button with alpha
     */
    fun setLoaderDisable(btnView: View, progress: View) {
        progress.visibility = View.GONE
        btnView.isEnabled = true
        btnView.alpha = 1f
    }

    /**
     * This method is used to enable the loader only
     */
    fun setOnlyLoaderEnable(progress: View) {
        progress.visibility = View.VISIBLE
    }

    /**
     * This method is used to disable the loader only
     */
    fun setOnlyLoaderDisable(progress: View) {
        progress.visibility = View.GONE
    }

    /**
     * This method is used to hide soft keyboard
     */
    fun hideKeyboardFrom(activity: Activity) {
        val imm =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(getRootView(activity).windowToken, 0)
    }

    /**
     * This method is used to get root view for activity
     */
    fun getRootView(activity: Activity): View {
        return activity.findViewById<View>(android.R.id.content)
    }

    /**
     * This method is used to restrict user from copy paste feature in edit text.
     */
    fun disableCopyPaste(editText: EditText) {
        editText.isLongClickable = false
        editText.setTextIsSelectable(false)

        editText.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(actionMode: ActionMode?, item: MenuItem?): Boolean {
                return false
            }

            override fun onDestroyActionMode(actionMode: ActionMode?) {}
        }
    }

    /**
     * Get Screen Width
     * @return int
     */
    val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels

    /**
     * Get Screen height
     * @return int
     */
    val screenHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels

    /**
     * This method is used for hiding soft keyboard
     */
    fun hideSoftInput(view: View, activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * This method is used for showing softkeyboard
     */
    fun showSoftInput(view: View, activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

   /* *//**
     * Check if internet connection available.
     * @param context - context
     * @return - true if network is availalbe
     *//*
    fun isNetworkConnected(context: Context): Boolean {
        val isConnectionAvailable = isNetworkAvailable(context)

        if (!isConnectionAvailable) {
            context.showToast(R.string.msg_offline)
        }

        return isConnectionAvailable
    }
*/
   /* *//**
     * This method is used to check internet connection
     *//*
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }*/


    fun setEnableView(view: View, progress: ContentLoadingProgressBar) {
        progress.visibility = View.GONE
        view.isEnabled = true
        view.alpha = 1f
    }


    fun setDisableView(view: View, progress: ContentLoadingProgressBar) {
        progress.visibility = View.VISIBLE
        view.isEnabled = false
        view.alpha = 0.5f
    }


    /*fun getReplierName(replies: Replies): String? {
        return if (!replies.name.isNullOrEmpty() && !replies.lname.isNullOrEmpty()) {
            replies.name.trim() + " " + replies.lname.trim()
        } else if (!replies.name.isNullOrEmpty()) {
            replies.name.trim()
        } else if (!replies.lname.isNullOrEmpty()) {
            replies.lname.trim()
        } else if (!replies.username.isNullOrEmpty()) {
            replies.username.trim()
        } else if (!replies.email.isNullOrEmpty()) {
            val currentString = replies.email
            val separated = currentString.split("@".toRegex()).toTypedArray()
            if (separated.count() > 0) {
                separated[0].trim()
            } else {
                "-"
            }
        } else {
            "-"
        }
    }


    fun getRaterName(reviews: Reviews): String? {
        return if (!reviews.name.isNullOrEmpty() && !reviews.lname.isNullOrEmpty()) {
            reviews.name.trim() + " " + reviews.lname.trim()
        } else if (!reviews.name.isNullOrEmpty()) {
            reviews.name.trim()
        } else if (!reviews.lname.isNullOrEmpty()) {
            reviews.lname.trim()
        } else if (!reviews.username.isNullOrEmpty()) {
            reviews.username.trim()
        } else if (!reviews.email.isNullOrEmpty()) {
            val currentString = reviews.email
            val separated = currentString.split("@".toRegex()).toTypedArray()
            if (separated.count() > 0) {
                separated[0].trim()
            } else {
                "-"
            }
        } else {
            "-"
        }
    }*/

    /**
     * This method is used to get zoom level from bounds
     */
    private const val LN2 = 0.6931471805599453
    private const val WORLD_PX_HEIGHT = 256
    private const val WORLD_PX_WIDTH = 256
    val PADDING_ZOOM_MAP_PER = 0.12f;

    /**
     * This method is used to get the zoom level from bounds
     * @param bounds      - latlngbounds
     * @param mapWidthPx  - map width
     * @param mapHeightPx - map height
     * @return - zoom level
     *//*
    fun getBoundsZoomLevel(bounds: LatLngBounds, mapWidthPx: Int, mapHeightPx: Int): Int {
        val ne = bounds.northeast
        val sw = bounds.southwest
        val latFraction = (latRad(ne.latitude) - latRad(sw.latitude)) / Math.PI
        val lngDiff = ne.longitude - sw.longitude
        val lngFraction = (if (lngDiff < 0) lngDiff + 360 else lngDiff) / 360
        val latZoom =
                zoom(mapHeightPx, WORLD_PX_HEIGHT, latFraction)
        val lngZoom =
                zoom(mapWidthPx, WORLD_PX_WIDTH, lngFraction)
        val result = Math.min(latZoom.toInt(), lngZoom.toInt())
        return Math.min(result, AppConstant.DefaultValues.MaxZoomLevel)
    }*/

    /**
     * This method is used to get the zoom level from bounds helper method
     */
    private fun latRad(lat: Double): Double {
        val sin = Math.sin(lat * Math.PI / 180)
        val radX2 = Math.log((1 + sin) / (1 - sin)) / 2
        return Math.max(Math.min(radX2, Math.PI), -Math.PI) / 2
    }

    /**
     * This is helper method for get zoom level
     */
    private fun zoom(mapPx: Int, worldPx: Int, fraction: Double): Double {
        return Math.floor(Math.log(mapPx / worldPx / fraction) / LN2)
    }

    fun getCurrentDayInUTC(): String? {
        val df: DateFormat = DateFormat.getTimeInstance()
        df.timeZone = TimeZone.getTimeZone("gmt")
        val gmtTime: String = df.format(Date())
//        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK)
        try {
            val date = df.parse(gmtTime)
            val outFormat = SimpleDateFormat("EEEE")
            return outFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * This method is used to read json file and get json object to save in local db.
     */
    fun readJSONFromAsset(context: Context): String {
        val json: String?
        try {
            val inputStream: InputStream = context.assets.open("data.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

}