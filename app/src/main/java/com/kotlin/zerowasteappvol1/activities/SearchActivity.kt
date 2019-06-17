package com.kotlin.zerowasteappvol1.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.application.ZeroWasteApplication
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel
import javax.inject.Inject

class SearchActivity: AppCompatActivity() {

    @Inject lateinit var placesViewModel: PlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        supportActionBar?.title = "Zero Waste App"

        (application as ZeroWasteApplication).appComponent.inject(this)



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
}