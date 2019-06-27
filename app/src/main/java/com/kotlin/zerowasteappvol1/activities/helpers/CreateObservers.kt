package com.kotlin.zerowasteappvol1.activities.helpers

import android.arch.lifecycle.Observer
import android.view.View
import android.widget.ProgressBar
import com.google.android.gms.maps.GoogleMap
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.activities.MapsActivity
import com.kotlin.zerowasteappvol1.activities.SearchActivity
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel
import com.kotlin.zerowasteappvol1.repository.ShortPlaceWithAddress

class CreateObservers {

    private lateinit var fiveBestFittingPlaces: List<ShortPlaceWithAddress?>

    fun createObserversForMapsActivity(activity: MapsActivity, viewModel: PlacesViewModel){
        val progressBar = activity.findViewById<ProgressBar>(R.id.progressBarMarkers)
        val mapOperator = MapOperations(activity.mMap)

        viewModel.allPlaces.observe(activity, Observer { places ->
            if(places != null){
                activity.points = places
//                if (::map.isInitialized){
                    mapOperator.addMarkersToMap(activity, places)
                    progressBar.visibility = View.GONE
//                }
            }
        })

//        val ratingBar = activity.findViewById<Rating>()
//
//        viewModel.placeDescription.observe(activity, Observer { place ->
//            if(place != null) {
//                ratingBar.rating = place.rating!!.toFloat()
//                textView_type_of_place.text = place.typeOfPlace
//                textView_open_hours.text = "${place.startHour} - ${place.endHour}"
//                textView_address.text = place.address
//                if(place.phoneNumber != null){
//                    phoneNumber = place.phoneNumber
//                    button_call.visibility = View.VISIBLE
//                }
//                if(place.website != null){
//                    website = place.website
//                    button_website.visibility = View.VISIBLE
//                }
//                progressBar_description.visibility = View.GONE
//                ratingBar.visibility = View.VISIBLE
//                textView_type_of_place.visibility = View.VISIBLE
//                textView_open_hours.visibility = View.VISIBLE
//                textView_address.visibility = View.VISIBLE
//            }
//        })
    }

    fun createObserversForSearchActivtiy(activity: SearchActivity, viewModel: PlacesViewModel){
        val progressBar = activity.findViewById<ProgressBar>(R.id.progressBar_search_activity)
        val searchResultsOperator = SearchResultsOperations(activity)

        viewModel.fiveNearestPlaces.observe(activity, Observer { places ->
            if (places != null && !::fiveBestFittingPlaces.isInitialized) {
                progressBar.visibility = View.GONE
                searchResultsOperator.addPlaces(places)
            }
        })

        viewModel.fiveBestFittingPlaces.observe(activity, Observer { places ->
            if(places != null){
                progressBar.visibility = View.GONE
                fiveBestFittingPlaces = places
                searchResultsOperator.addPlaces(places)
            }
        })
    }
}