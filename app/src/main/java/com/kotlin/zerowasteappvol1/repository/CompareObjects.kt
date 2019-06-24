package com.kotlin.zerowasteappvol1.repository

import me.xdrop.fuzzywuzzy.model.ExtractedResult
import java.util.Comparator

class CompareObjects {

    companion object : Comparator<ExtractedResult> {

        override fun compare(a: ExtractedResult, b: ExtractedResult): Int = b.score - a.score

    }
}