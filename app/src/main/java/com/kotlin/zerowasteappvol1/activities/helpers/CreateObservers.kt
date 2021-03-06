package com.kotlin.zerowasteappvol1.activities.helpers

import android.arch.lifecycle.Observer
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.activities.MapsActivity
import com.kotlin.zerowasteappvol1.activities.SearchActivity
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel
import com.kotlin.zerowasteappvol1.repository.ShortPlaceWithAddress

class CreateObservers {

    private lateinit var fiveBestFittingPlaces: List<ShortPlaceWithAddress?>

    fun createObserversForMapsActivity(activity: MapsActivity, viewModel: PlacesViewModel, mapOperator: MapOperations){
        createAllPlacesObserver(activity, viewModel, mapOperator)
        createPlaceDescriptionObserver(activity, viewModel)
        createPlaceImageObserver(activity, viewModel)
    }

    fun createObserversForSearchActivity(activity: SearchActivity, viewModel: PlacesViewModel){
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

    private fun createAllPlacesObserver(activity: MapsActivity, viewModel: PlacesViewModel, mapOperator: MapOperations){
        val progressBar = activity.findViewById<ProgressBar>(R.id.progressBarMarkers)

        viewModel.allPlaces.observe(activity, Observer { places ->
            if(places != null){
                activity.points = places
                mapOperator.addMarkersToMap(places)
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun createPlaceDescriptionObserver(activity: MapsActivity, viewModel: PlacesViewModel){
        val ratingBar = activity.findViewById<RatingBar>(R.id.ratingBar)
        val textViewTypeOfPlace = activity.findViewById<TextView>(R.id.textView_type_of_place)
        val textViewOpenHours = activity.findViewById<TextView>(R.id.textView_open_hours)
        val textViewAddress = activity.findViewById<TextView>(R.id.textView_address)
        val buttonCall = activity.findViewById<Button>(R.id.button_call)
        val buttonWebsite = activity.findViewById<Button>(R.id.button_website)
        val progressBarDescription = activity.findViewById<ProgressBar>(R.id.progressBar_description)

        viewModel.placeDescription.observe(activity, Observer { place ->
            if(place != null) {
                ratingBar.rating = place.rating!!.toFloat()
                textViewTypeOfPlace.text = place.typeOfPlace
                textViewOpenHours.text = "${place.startHour} - ${place.endHour}"
                textViewAddress.text = place.address
                if(place.phoneNumber != null){
                    val phoneNumber = place.phoneNumber
                    putOnSharedPreferences(activity, "phoneNumber", phoneNumber)
                    buttonCall.visibility = View.VISIBLE
                }
                if(place.website != null){
                    val website = place.website
                    putOnSharedPreferences(activity, "website", website)
                    buttonWebsite.visibility = View.VISIBLE
                }
                progressBarDescription.visibility = View.GONE
                ratingBar.visibility = View.VISIBLE
                textViewTypeOfPlace.visibility = View.VISIBLE
                textViewOpenHours.visibility = View.VISIBLE
                textViewAddress.visibility = View.VISIBLE
            }
        })
    }

    private fun createPlaceImageObserver(activity: MapsActivity, viewModel: PlacesViewModel){
        val descriptionOperator = DescriptionOperations(activity)

        viewModel.placeImages.observe(activity, Observer { images ->
            if(images != null){
                descriptionOperator.displayImages(images)
            }
        })
    }

    private fun putOnSharedPreferences(activity: MapsActivity, key: String, value: String?){
        val pref = activity.getSharedPreferences("MyPreferences", 0)
        val editor = pref.edit()
        editor.remove(key)
        editor.putString(key, value)
        editor.apply()
    }
}