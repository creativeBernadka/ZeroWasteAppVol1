package com.kotlin.zerowasteappvol1.activities.helpers

import android.arch.lifecycle.Observer
import android.view.View
import android.widget.ProgressBar
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.activities.SearchActivity
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel
import com.kotlin.zerowasteappvol1.viewModel.ShortPlaceWithAddress

class CreateObservers {

    lateinit var fiveBestFittingPlaces: List<ShortPlaceWithAddress?>

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