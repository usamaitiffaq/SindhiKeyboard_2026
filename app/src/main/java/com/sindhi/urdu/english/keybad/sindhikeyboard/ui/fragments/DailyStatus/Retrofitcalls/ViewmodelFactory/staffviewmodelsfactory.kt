package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.ViewmodelFactory

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.ViewmodelFactory.DictionaryViewModel
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.viewmodel.dictionaryrepository

class staffviewmodelsfactory (val application: Application,val activity: Activity,val navController: NavController?=null,val dictionaryrepository: dictionaryrepository?=null): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass){
        DictionaryViewModel::class.java->navController?.let { DictionaryViewModel(application, navController = it,activity,dictionaryrepository!!) }
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    } as T

}