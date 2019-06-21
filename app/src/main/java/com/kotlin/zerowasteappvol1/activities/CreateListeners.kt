package com.kotlin.zerowasteappvol1.activities

import android.widget.EditText
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel

class CreateListeners {

    fun createListenersForSearchActivity(activity: SearchActivity, viewModel: PlacesViewModel){
        val searchPanel = activity.findViewById<EditText>(R.id.editText_search_panel)

        searchPanel.addTextChangedListener(SearchPanelTextWatcher(activity, viewModel))

    }
}