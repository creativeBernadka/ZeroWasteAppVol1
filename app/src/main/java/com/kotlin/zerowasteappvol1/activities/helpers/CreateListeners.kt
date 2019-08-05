package com.kotlin.zerowasteappvol1.activities.helpers

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ResolveInfo
import android.net.ConnectivityManager
import android.net.Network
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.activities.MapsActivity
import com.kotlin.zerowasteappvol1.activities.SearchActivity
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel

class CreateListeners {

    fun createListenersForMapsActivity(activity: MapsActivity){
        val searchPanel = activity.findViewById<TextView>(R.id.SearchPanel)

        searchPanel.setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)

            val pref = activity.getSharedPreferences("MyPreferences", 0)

            val locationJson = pref.getString("currentLocation", "")

            val gson = Gson()
            val location: LatLng = gson.fromJson(locationJson, LatLng::class.java)

            if (location != null){
                intent.putExtra("location", location)
            }
            activity.startActivity(intent)
        }

        createNetworkListener(activity)
        createShortDescriptionListeners(activity)
    }

    private fun createNetworkListener(activity: MapsActivity){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
            val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            cm.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    activity.onNetworkChange(true)
                }
                override fun onLost(network: Network?) {
                    super.onLost(network)
                    activity.onNetworkChange(false)
                }
                override fun onUnavailable() {
                    super.onUnavailable()
                    activity.onNetworkChange(false)
                }
            })

            val activeNetwork = cm.activeNetworkInfo
            val isConnected = activeNetwork != null && activeNetwork.isConnected

            if (isConnected) {
                activity.onNetworkChange(true)
            }
            else {
                activity.onNetworkChange(false)
            }
        }
        else {
            val networkChangeReceiver = NetworkChangeReceiver()
            networkChangeReceiver.setListener(activity)
            activity.registerReceiver(
                networkChangeReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
            activity.isNetworkConnected = networkChangeReceiver.isConnected()
            if(activity.isNetworkConnected) activity.placesViewModel.getAllPlaces()
        }
    }

    private fun createShortDescriptionListeners(activity: MapsActivity){
        createCallButtonListener(activity)
        createNavigateButtonListener(activity)
        createWebsiteButtonListener(activity)
    }

    private fun createCallButtonListener(activity: MapsActivity){
        val buttonCall = activity.findViewById<Button>(R.id.button_call)
        buttonCall.setOnClickListener {
            val phoneNumber = getFromSharedPreferences(activity, "phoneNumber")
            val uri = "tel:$phoneNumber"
            val callIntent: Intent = Uri.parse(uri).let { number ->
                Intent(Intent.ACTION_DIAL, number)
            }
            val activities: List<ResolveInfo> = activity.packageManager.queryIntentActivities(callIntent, 0)
            if (activities.isNotEmpty()){
                activity.startActivity(callIntent)
            }
        }
    }

    private fun createNavigateButtonListener(activity: MapsActivity){
        val buttonNavigation = activity.findViewById<Button>(R.id.button_navigation)

        buttonNavigation.setOnClickListener {
            val coordinatesJson = getFromSharedPreferences(activity, "coordinates")
            val latLng: LatLng = Gson().fromJson(coordinatesJson, LatLng::class.java)
            val uri = "https://www.google.com/maps/dir/?api=1" +
                    "&destination=${latLng.latitude},${latLng.longitude}" +
                    "&travelmode=walking"
            val navigationIntent: Intent = Uri.parse(uri).let { location ->
                Intent(Intent.ACTION_VIEW, location)
            }
            val activities: List<ResolveInfo> = activity.packageManager.queryIntentActivities(navigationIntent, 0)
            if (activities.isNotEmpty()){
                activity.startActivity(navigationIntent)
            }
        }
    }

    private fun createWebsiteButtonListener(activity: MapsActivity){
        val buttonWebsite = activity.findViewById<Button>(R.id.button_website)
        buttonWebsite.setOnClickListener {
            val website = getFromSharedPreferences(activity, "website")
            val uri = "$website"
            val webIntent: Intent = Uri.parse(uri).let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            val activities: List<ResolveInfo> = activity.packageManager.queryIntentActivities(webIntent, 0)
            if (activities.isNotEmpty()){
                activity.startActivity(webIntent)
            }
        }
    }

    fun createListenersForSearchActivity(activity: SearchActivity, viewModel: PlacesViewModel){
        createSearchPanelListener(activity, viewModel)
        createSearchResultsListener(activity)
    }

    private fun createSearchPanelListener(activity: SearchActivity, viewModel: PlacesViewModel){
        val searchPanel = activity.findViewById<EditText>(R.id.editText_search_panel)

        searchPanel.addTextChangedListener(
            SearchPanelTextWatcher(
                activity,
                viewModel
            )
        )
    }

    private fun createSearchResultsListener(activity: SearchActivity){
        val firstResult = activity.findViewById<LinearLayout>(R.id.linearLayout_first_place)

        firstResult.setOnClickListener {
            putOnSharedPreferences(activity, 2)
            activity.finish()
        }

        val secondResult = activity.findViewById<LinearLayout>(R.id.linearLayout_second_place)
        secondResult.setOnClickListener {
            putOnSharedPreferences(activity, 2)
            activity.finish()
        }

        val thirdResult = activity.findViewById<LinearLayout>(R.id.linearLayout_third_place)
        thirdResult.setOnClickListener {
            putOnSharedPreferences(activity, 3)
            activity.finish()
        }

        val fourthResult = activity.findViewById<LinearLayout>(R.id.linearLayout_fourth_place)
        fourthResult.setOnClickListener {
            putOnSharedPreferences(activity, 4)
            activity.finish()
        }

        val fifthResult = activity.findViewById<LinearLayout>(R.id.linearLayout_fifth_place)
        fifthResult.setOnClickListener {
            putOnSharedPreferences(activity, 5)
            activity.finish()
        }
    }

    private fun putOnSharedPreferences(activity: SearchActivity, whichWasClicked: Int){
        val pref = activity.getSharedPreferences("MyPreferences", 0)
        val editor = pref.edit()
        editor.remove("whichOneWasClicked")
        editor.putInt("whichOneWasClicked", whichWasClicked)
        editor.apply()
    }

    private fun getFromSharedPreferences(activity: MapsActivity, key: String): String?{
        val pref = activity.getSharedPreferences("MyPreferences", 0)
        return pref.getString(key, "")
    }
}