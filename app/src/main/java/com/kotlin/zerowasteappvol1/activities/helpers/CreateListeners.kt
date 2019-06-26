package com.kotlin.zerowasteappvol1.activities.helpers

import android.widget.EditText
import android.widget.LinearLayout
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.activities.SearchActivity
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel

class CreateListeners {

    fun createListenersForSearchActivity(activity: SearchActivity, viewModel: PlacesViewModel){
        createListenerForSearchPanel(activity, viewModel)
        createListenerForSearchResults(activity)
    }

    private fun createListenerForSearchPanel(activity: SearchActivity, viewModel: PlacesViewModel){
        val searchPanel = activity.findViewById<EditText>(R.id.editText_search_panel)

        searchPanel.addTextChangedListener(
            SearchPanelTextWatcher(
                activity,
                viewModel
            )
        )
    }

    private fun createListenerForSearchResults(activity: SearchActivity){
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
}