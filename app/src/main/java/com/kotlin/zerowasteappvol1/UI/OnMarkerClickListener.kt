package com.kotlin.zerowasteappvol1.UI

import android.support.v7.widget.CardView
import android.view.View
import android.widget.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.database.ShortPlace
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class OnMarkerClickListener(
    private val eventMarkerMap: HashMap<Marker, ShortPlace>,
    private val placesViewModel: PlacesViewModel,
    private val activity: MapsActivity):
    CoroutineScope, GoogleMap.OnMarkerClickListener {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onMarkerClick(marker: Marker?): Boolean {
        val place: ShortPlace? = eventMarkerMap[marker]

        activity.latLng = place?.coordinates
        activity.findViewById<RatingBar>(R.id.ratingBar).visibility = View.GONE
        activity.findViewById<TextView>(R.id.textView_type_of_place).visibility = View.GONE
        activity.findViewById<TextView>(R.id.textView_open_hours).visibility = View.GONE
        activity.findViewById<CardView>(R.id.cardView_carousel_images).visibility = View.GONE
        activity.findViewById<TextView>(R.id.textView_address).visibility = View.GONE
        activity.findViewById<Button>(R.id.button_call).visibility = View.GONE
        activity.findViewById<Button>(R.id.button_website).visibility = View.GONE
        activity.findViewById<LinearLayout>(R.id.linearLayout_short_description).visibility = View.VISIBLE
        activity.findViewById<ProgressBar>(R.id.progressBar_description).visibility = View.VISIBLE
        activity.findViewById<TextView>(R.id.textView_name).text = place?.name
        launch {
            placesViewModel.getPlaceDescription(place, activity)
        }
        return true //musi byc true, zeby nie pokazywalo infoWindow
    }
}