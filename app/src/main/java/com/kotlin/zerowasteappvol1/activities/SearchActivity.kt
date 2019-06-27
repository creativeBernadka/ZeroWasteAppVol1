package com.kotlin.zerowasteappvol1.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.application.ZeroWasteApplication
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel
import kotlinx.android.synthetic.main.search_activity.*
import javax.inject.Inject
import com.kotlin.zerowasteappvol1.activities.helpers.CreateListeners
import com.kotlin.zerowasteappvol1.activities.helpers.CreateObservers


class SearchActivity: AppCompatActivity() {

    @Inject lateinit var placesViewModel: PlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        supportActionBar?.title = "Zero Waste App"

        (application as ZeroWasteApplication).appComponent.inject(this)

        CreateListeners()
            .createListenersForSearchActivity(this, placesViewModel)
        CreateObservers()
            .createObserversForSearchActivity(this, placesViewModel)

        progressBar_search_activity.visibility = View.VISIBLE
        placesViewModel.getFiveNearestPlaces(
            intent.getParcelableExtra("location"),
            this
        )
    }
}