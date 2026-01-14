package com.sindhi.urdu.english.keybad.sindhikeyboard.interfaces

import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.QuoteX


interface generalstatusitemclicklistner {

    fun onshayariitemcopyitemclicked(quoteX: QuoteX)
    fun onshayariitemshareitemclicked(quoteX: QuoteX)
    fun onshayariitemmessengeritemclicked(quoteX: QuoteX)
    fun onshayariitemwhatsappitemclicked(quoteX: QuoteX)
    fun onshayariitemwitteritemclicked(quoteX: QuoteX)
    fun onshayariitemedititemclicked(quoteX: QuoteX)
}