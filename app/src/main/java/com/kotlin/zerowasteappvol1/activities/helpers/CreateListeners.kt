package com.kotlin.zerowasteappvol1.activities.helpers

import android.widget.EditText
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

    }
}