package com.kotlin.zerowasteappvol1.activities.helpers

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.activities.SearchActivity
import com.kotlin.zerowasteappvol1.repository.ShortPlaceWithAddress

class SearchResultsOperations(private val activity: SearchActivity) {

    fun addPlaces(places: List<ShortPlaceWithAddress?>) {

        val linearLayoutFirstPlace = activity.findViewById<LinearLayout>(R.id.linearLayout_first_place)
        val linearLayoutSecondPlace = activity.findViewById<LinearLayout>(R.id.linearLayout_second_place)
        val linearLayoutThirdPlace = activity.findViewById<LinearLayout>(R.id.linearLayout_third_place)
        val linearLayoutFourthPlace = activity.findViewById<LinearLayout>(R.id.linearLayout_fourth_place)
        val linearLayoutFifthPlace = activity.findViewById<LinearLayout>(R.id.linearLayout_fifth_place)

        val imageViewFirstPlace = activity.findViewById<ImageView>(R.id.imageView_first_place_type)
        val imageViewSecondPlace = activity.findViewById<ImageView>(R.id.imageView_second_place_type)
        val imageViewThirdPlace = activity.findViewById<ImageView>(R.id.imageView_third_place_type)
        val imageViewFourthPlace = activity.findViewById<ImageView>(R.id.imageView_fourth_place_type)
        val imageViewFifthPlace = activity.findViewById<ImageView>(R.id.imageView_fifth_place_type)

        val textViewFirstPlaceName = activity.findViewById<TextView>(R.id.textView_first_place_name)
        val textViewSecondPlaceName = activity.findViewById<TextView>(R.id.textView_second_place_name)
        val textViewThirdPlaceName = activity.findViewById<TextView>(R.id.textView_third_place_name)
        val textViewFourthPlaceName = activity.findViewById<TextView>(R.id.textView_fourth_place_name)
        val textViewFifthPlaceName = activity.findViewById<TextView>(R.id.textView_fifth_place_name)

        val textViewFirstPlaceAddress = activity.findViewById<TextView>(R.id.textView_first_place_address)
        val textViewSecondPlaceAddress = activity.findViewById<TextView>(R.id.textView_second_place_address)
        val textViewThirdPlaceAddress = activity.findViewById<TextView>(R.id.textView_third_place_address)
        val textViewFourthPlaceAddress = activity.findViewById<TextView>(R.id.textView_fourth_place_address)
        val textViewFifthPlaceAddress = activity.findViewById<TextView>(R.id.textView_fifth_place_address)

        places.forEach { place ->
            val placeIcon = getMarkerIcon(place!!.typeOfPlace)
            when (places.indexOf(place)) {
                0 -> {
                    linearLayoutFirstPlace.visibility = View.VISIBLE
                    imageViewFirstPlace.setImageResource(placeIcon)
                    textViewFirstPlaceName.text = place.name
                    if (place.address != null) textViewFirstPlaceAddress.text = place.address
                }
                1 -> {
                    linearLayoutSecondPlace.visibility = View.VISIBLE
                    imageViewSecondPlace.setImageResource(placeIcon)
                    textViewSecondPlaceName.text = place.name
                    if (place.address != null) textViewSecondPlaceAddress.text = place.address
                }
                2 -> {
                    linearLayoutThirdPlace.visibility = View.VISIBLE
                    imageViewThirdPlace.setImageResource(placeIcon)
                    textViewThirdPlaceName.text = place.name
                    if (place.address != null) textViewThirdPlaceAddress.text = place.address
                }
                3 -> {
                    linearLayoutFourthPlace.visibility = View.VISIBLE
                    imageViewFourthPlace.setImageResource(placeIcon)
                    textViewFourthPlaceName.text = place.name
                    if (place.address != null) textViewFourthPlaceAddress.text = place.address
                }
                4 -> {
                    linearLayoutFifthPlace.visibility = View.VISIBLE
                    imageViewFifthPlace.setImageResource(placeIcon)
                    textViewFifthPlaceName.text = place.name
                    if (place.address != null) textViewFifthPlaceAddress.text = place.address
                }
            }
        }
    }

    fun cleanSearch(){
        val linearLayoutFirstPlace = activity.findViewById<LinearLayout>(R.id.linearLayout_first_place)
        val linearLayoutSecondPlace = activity.findViewById<LinearLayout>(R.id.linearLayout_second_place)
        val linearLayoutThirdPlace = activity.findViewById<LinearLayout>(R.id.linearLayout_third_place)
        val linearLayoutFourthPlace = activity.findViewById<LinearLayout>(R.id.linearLayout_fourth_place)
        val linearLayoutFifthPlace = activity.findViewById<LinearLayout>(R.id.linearLayout_fifth_place)

        linearLayoutFirstPlace.visibility = View.GONE
        linearLayoutSecondPlace.visibility = View.GONE
        linearLayoutThirdPlace.visibility = View.GONE
        linearLayoutFourthPlace.visibility = View.GONE
        linearLayoutFifthPlace.visibility = View.GONE
    }

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