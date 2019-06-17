package com.kotlin.zerowasteappvol1.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.application.ZeroWasteApplication
import com.kotlin.zerowasteappvol1.database.ShortPlace
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel
import kotlinx.android.synthetic.main.search_activity.*
import javax.inject.Inject

class SearchActivity: AppCompatActivity() {

    @Inject lateinit var placesViewModel: PlacesViewModel

    lateinit var fiveBestFittingPlaces: List<ShortPlace>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        supportActionBar?.title = "Zero Waste App"

        (application as ZeroWasteApplication).appComponent.inject(this)

        placesViewModel.getFiveNearestPlaces(
            intent.getParcelableExtra("location")
        )

        placesViewModel.fiveNearestPlaces.observe(this, Observer { places ->
            if(places != null && (!::fiveBestFittingPlaces.isInitialized || fiveBestFittingPlaces == null)){
                addPlaces(places)
            }
        })
//        SearchPanel.setOnEditorActionListener { _, actionId, _ ->
//            //            do czyszczenia SearchPanelu
//            when (actionId) {
//                EditorInfo.IME_ACTION_DONE -> {
//                    SearchPanel.text.clear()
//                    true
//                }
//                else -> false
//            }
//        }
    }

    private fun addPlaces(places: List<ShortPlace>){
        places.forEach{ place ->
            when(places.indexOf(place)){
                0 -> {
                    linearLayout_first_place.visibility = View.VISIBLE
                    textView_first_place_name.text = place.name
                }
                1 -> {
                    linearLayout_second_place.visibility = View.VISIBLE
                    textView_second_place_name.text = place.name
                }
                2 -> {
                    linearLayout_third_place.visibility = View.VISIBLE
                    textView_third_place_name.text = place.name
                }
                3 -> {
                    linearLayout_fourth_place.visibility = View.VISIBLE
                    textView_fourth_place_name.text = place.name
                }
                4 -> {
                    linearLayout_fifth_place.visibility = View.VISIBLE
                    textView_fifth_place_name.text = place.name
                }
            }
        }
    }
}