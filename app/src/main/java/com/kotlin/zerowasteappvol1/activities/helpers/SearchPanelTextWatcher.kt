package com.kotlin.zerowasteappvol1.activities.helpers

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ProgressBar
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.activities.SearchActivity
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel

class SearchPanelTextWatcher (private val activity: SearchActivity, val viewModel: PlacesViewModel)
    : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        val progressBar = activity.findViewById<ProgressBar>(R.id.progressBar_search_activity)
        progressBar.visibility = View.VISIBLE
        viewModel.getFiveBestFittingPlaces(s.toString())
        SearchResultsOperations(activity).cleanSearch()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}