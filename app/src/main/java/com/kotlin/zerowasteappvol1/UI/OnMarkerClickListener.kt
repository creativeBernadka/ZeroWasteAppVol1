package com.kotlin.zerowasteappvol1.UI

import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.kotlin.zerowasteappvol1.PlacesViewModel
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.database.ShortPlace
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class OnMarkerClickListener(val eventMarkerMap: HashMap<Marker, ShortPlace>,
                            val placesViewModel: PlacesViewModel,
                            val activity: MapsActivity):
    CoroutineScope, GoogleMap.OnMarkerClickListener {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onMarkerClick(marker: Marker?): Boolean {
        val place: ShortPlace? = eventMarkerMap[marker]
        activity.findViewById<RatingBar>(R.id.ratingBar).visibility = View.GONE
        activity.findViewById<TextView>(R.id.textView_type_of_place).visibility = View.GONE
        activity.findViewById<TextView>(R.id.textView_open_hours).visibility = View.GONE
        activity.findViewById<LinearLayout>(R.id.carousel_images).visibility = View.GONE
        activity.findViewById<LinearLayout>(R.id.layout_short_description).visibility = View.VISIBLE
        activity.findViewById<ProgressBar>(R.id.progressBar_description).visibility = View.VISIBLE
        activity.findViewById<TextView>(R.id.textView_name).text = place?.name
        launch {
            placesViewModel.getPlaceDescription(place)
        }
        return true //musi byc true, zeby nie pokazywalo infoWindow
    }
}