package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardLayout

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Vibrator
import android.util.Log
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.KeyboardState
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.arabicLayout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.bangla_layout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.extendedSymbolsLayout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.husneguftar
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.nepali_layout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.numbersLayout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.pashto_layout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.qwertyLayout_english
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.sindhi_layout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.symbolsLayout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.symbolsSindhiLayout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.symbolsUrduLayout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.textEditLayout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.urdu_layout
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.dbClasses.DataBaseCopyOperationsKt
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.dbClasses.SuggestionItems
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.constants.CUSTOM_ACTION
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.constants.LABEL_DELETE
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.KeyboardVisibilityProvider
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.LayoutState
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardComponents.CustomKey
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.MyPreferences
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.service.CustomImeService
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.theme.AppTheme
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.theme.KeyboardTheme
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.utilityClasses.getIconResource
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.utilityClasses.getSelectedLanguage
import com.sindhi.urdu.english.keybad.sindhikeyboard.stickers.StickerPackInfo
import com.sindhi.urdu.english.keybad.sindhikeyboard.stickers.StickerViewModel
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.DictionaryObject.suggestionList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

@Composable
fun MyKeyboard(imeService: CustomImeService? = null, vibrator: Vibrator? = null, context: Context?, inputConnection: InputConnection? = null) {

    val myPreferences = context?.let { MyPreferences(it) }

    var defaultLanguage by remember {
        mutableStateOf(myPreferences?.getKeyboard() ?: "English")
    }

    // Update preferences when language changes
    LaunchedEffect(defaultLanguage) {
        myPreferences?.setKeyboard(defaultLanguage)
    }

    val downloadedStickersPackList: MutableList<StickerPackInfo>
    val stickerViewModel: StickerViewModel by lazy { StickerViewModel() }
    downloadedStickersPackList = stickerViewModel.fetchDownloadStickers(imeService!!.application.applicationContext)

    // Observe changes in theme mode
    var themeMode by remember { mutableStateOf(myPreferences!!.getTheme()) }
    // Use a state variable to hold the value of showNumbersRow
    var showNumbersRow by remember { mutableStateOf(myPreferences!!.getShowNumbersRow()) }

        val currentLanguage = when (defaultLanguage) {
        "English" -> qwertyLayout_english
        "Urdu" -> urdu_layout
        "Arabic" -> arabicLayout
        "Nepali" -> nepali_layout
        "Pashto" -> pashto_layout
        "Bangla" -> bangla_layout
        "Sindhi" -> sindhi_layout
        else -> qwertyLayout_english
    }

    // Mutable state variables for Suggestions
    var isSuggestionsEnabled by remember { mutableStateOf(false) }

    val view = LocalView.current

    val isGestureNavigationEnabled = remember {
        // Check if gesture navigation is enabled
        val resources = view.context.resources
        val resourceId = resources.getIdentifier(
            "config_navBarInteractionMode",
            "integer",
            "android"
        )
        if (resourceId > 0) {
            resources.getInteger(resourceId) == 2 // 2 indicates gesture navigation
        } else {
            false
        }
    }

    // Mutable state variables for VoiceClick
    var isVoiceInputClicked by remember { mutableStateOf(false) }

    var isEmojiButtonClicked by remember { mutableStateOf(true) }
    var isStickerButtonClicked by remember { mutableStateOf(false) }

    // Mutable state variables for Caps and Caps Lock
    var isCapsEnabled by remember { mutableStateOf(true) }
    var isCapsLockEnabled by remember { mutableStateOf(false) }

    // Mutable state for the icon based on IME action type
    var icon by remember(imeService?.imeActionType) {
        mutableIntStateOf(getIconResource(imeService.imeActionType))
    }

    // Observe changes in imeActionType and update the icon accordingly
    LaunchedEffect(imeService?.imeActionType) {
        icon = getIconResource(imeService?.imeActionType)
    }

    // Mutable state variable to track the current layout
    var currentLayout by KeyboardState.currentLayout

    // Mutable state variable to track the back icon visibility
    var showBackIcon by remember { mutableStateOf(false) }


    val languagePreferences = listOf(
        "English" to MyPreferences::getEnglish,
        "Urdu" to MyPreferences::getUrdu,
        "Arabic" to MyPreferences::getArabic,
        "Sindhi" to MyPreferences::getSindhi,
        "Pashto" to MyPreferences::getPashto,
        "Bangla" to MyPreferences::getBangla,
        "Nepali" to MyPreferences::getNepali
    )

    val enabledLanguages = languagePreferences.filter { (_, preferenceGetter) ->
        myPreferences?.let { preferenceGetter.invoke(it) } == true
    }.map { (language, _) ->
        language
    }.toList()

    // Keep currentLanguageIndex in sync with defaultLanguage
    var currentLanguageIndex by remember {
        mutableIntStateOf(enabledLanguages.indexOf(defaultLanguage).coerceAtLeast(0))
    }

    // Update currentLanguageIndex when defaultLanguage changes
    LaunchedEffect(defaultLanguage) {
        currentLanguageIndex = enabledLanguages.indexOf(defaultLanguage).coerceAtLeast(0)
    }

    // Define the height of the keyboard based on whether the numbers row should be shown
    val keyboardHeight = if (showNumbersRow) 298.dp else 268.dp

    // Determine which keys to display based on the current layout
    var keys = when (currentLayout) {
        LayoutState.Main -> currentLanguage
        LayoutState.Symbols -> symbolsLayout
        LayoutState.SymbolsUrdu -> symbolsUrduLayout
        LayoutState.SymbolsSindhi -> symbolsSindhiLayout
        LayoutState.ExtendedSymbols -> extendedSymbolsLayout
        LayoutState.Numbers -> numbersLayout
        else -> null
    }
    keys =
        keys?.drop(if (!myPreferences!!.getShowNumbersRow() && currentLayout == LayoutState.Main && (defaultLanguage == "English" || defaultLanguage == "Arabic")) 1 else 0)
            ?.toTypedArray()

    // Helper function to check if text field is empty
    fun isTextFieldEmpty(inputConnection: InputConnection?): Boolean {
        val extractedText = inputConnection?.getExtractedText(ExtractedTextRequest(), 0)
        return extractedText?.text?.isEmpty() ?: true
    }

    // Helper function to check if text field is empty (variant)
    fun isTextFieldEmptyVariant(inputConnection: InputConnection?): Boolean {
        return isTextFieldEmpty(inputConnection)
    }

    fun resetKeyboard() {
        currentLayout = LayoutState.Main
        isCapsLockEnabled = false
        if (isTextFieldEmpty(inputConnection)) {
            isCapsEnabled = true
        }
    }

    var isKeyboardVisible by remember { mutableStateOf(false) }
    KeyboardVisibilityProvider { isVisible ->
        isKeyboardVisible = isVisible
        if (!isKeyboardVisible) {
            showNumbersRow = context?.getSharedPreferences("SR_keyboard", Context.MODE_PRIVATE)
                ?.getBoolean(Preferences.showNumbersRow, false)!!
            themeMode = context.getSharedPreferences("SR_keyboard", Context.MODE_PRIVATE)
                ?.getString(Preferences.themeKey, "AUTO").toString()
        }
    }

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            try {
                resetKeyboard()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    DisposableEffect(context) {
        val filter = IntentFilter(CUSTOM_ACTION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context?.registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED)
        } else {
            context?.registerReceiver(receiver, filter, null, null)
        }

        onDispose {
            context?.unregisterReceiver(receiver)
        }
    }

    val specialKeys = if (currentLayout == LayoutState.TextEdit) {
        textEditLayout
    } else {
        null
    }

    val specialKeysHusneGuftar = if (currentLayout == LayoutState.HusnEguftar) {
        husneguftar
    } else {
        null
    }

    // Click handlers for layout switches
    val onLayoutSwitchClick = {
        currentLayout = LayoutState.Main
    }

    val onNumbersSwitchClick = {
        currentLayout = LayoutState.Numbers
    }

    val onSymbolsLayoutSwitchClick = {
        currentLayout = LayoutState.Symbols
    }

    val onSymbolsUrduLayoutSwitchClick = {
        currentLayout = LayoutState.SymbolsUrdu
    }

    val onSymbolsSindhiLayoutSwitchClick = {
        currentLayout = LayoutState.SymbolsSindhi
    }

    val onExtendedSymbolsSwitchClick = {
        currentLayout = LayoutState.ExtendedSymbols
    }

    // FIXED: Unified language switching logic
    val onLanguageSwitchClick: (String) -> Unit = { languageChangedFromTop ->
        if (languageChangedFromTop.isNotEmpty()) {
            // Direct language change from candidate view
            defaultLanguage = languageChangedFromTop
            Log.e("Language", "Language changed from top: $languageChangedFromTop")
        } else if (enabledLanguages.isNotEmpty()) {
            // Cycle through enabled languages from keyboard
            currentLanguageIndex = (currentLanguageIndex + 1) % enabledLanguages.size
            defaultLanguage = enabledLanguages[currentLanguageIndex]
            Log.e("Language", "Cycling languages - Current: $defaultLanguage, Index: $currentLanguageIndex")
        }

        // Reset keyboard state on language change
        resetKeyboard()
    }

    val onEditTextSwitchClick = {
        currentLayout = LayoutState.TextEdit
        showBackIcon = true
    }

    val onHusnEguftarClick = {
        currentLayout = LayoutState.HusnEguftar
        showBackIcon = true
    }

    val onEmojiSwitchClick = {
        currentLayout = LayoutState.Emojis
        showBackIcon = true
    }

    val onBackClick = {
        currentLayout = LayoutState.Main
        showBackIcon = false
        isSuggestionsEnabled = false
        imeService.filterList.value = emptyList()
    }

    // Click handler for Caps
    val onCapsClick = {
        if (isCapsLockEnabled) {
            isCapsLockEnabled = false
        } else {
            isCapsEnabled = !isCapsEnabled
        }
    }

    // Click handler to toggle Caps Lock
    val onCapsClickToLock = {
        isCapsLockEnabled = !isCapsLockEnabled
        isCapsEnabled = false
    }

    val layoutLabel =
        if (currentLayout == LayoutState.TextEdit) stringResource(id = R.string.edit_layout)
        else if (currentLayout == LayoutState.HusnEguftar) stringResource(id = R.string.HusnEguftar_layout)
        else stringResource(id = R.string.emoji_layout)

    KeyboardTheme(
        theme = when (themeMode) {
            "AUTO" -> AppTheme.AUTO
            "LIGHT" -> AppTheme.LIGHT
            "Dark" -> AppTheme.DARK
            "SolidSimple" -> AppTheme.SolidSimple
            "Gradient1" -> AppTheme.Gradient1
            "Gradient2" -> AppTheme.Gradient2
            "Gradient3" -> AppTheme.Gradient3
            "Gradient4" -> AppTheme.Gradient4
            "Gradient5" -> AppTheme.Gradient5
            "Gradient6" -> AppTheme.Gradient6
            "Gradient7" -> AppTheme.Gradient7
            "Gradient8" -> AppTheme.Gradient8
            "Gradient9" -> AppTheme.Gradient9
            "Gradient10" -> AppTheme.Gradient10
            "Gradient11" -> AppTheme.Gradient11
            "Gradient12" -> AppTheme.Gradient12
            "SOLID1_LIGHT" -> AppTheme.SOLID_LIGHT_1
            "SOLID2_LIGHT" -> AppTheme.SOLID_LIGHT_2
            "SOLID3_LIGHT" -> AppTheme.SOLID_LIGHT_3
            "SOLID4_LIGHT" -> AppTheme.SOLID_LIGHT_4
            "SOLID5_LIGHT" -> AppTheme.SOLID_LIGHT_5
            "SOLID6_LIGHT" -> AppTheme.SOLID_LIGHT_6
            "SOLID7_LIGHT" -> AppTheme.SOLID_LIGHT_7
            "SOLID8_LIGHT" -> AppTheme.SOLID_LIGHT_8
            "SOLID9_LIGHT" -> AppTheme.SOLID_LIGHT_9
            "SOLID10_LIGHT" -> AppTheme.SOLID_LIGHT_10
            "SOLID11_LIGHT" -> AppTheme.SOLID_LIGHT_11
            "SOLID12_LIGHT" -> AppTheme.SOLID_LIGHT_12
            "SOLID1_DARK" -> AppTheme.SOLID_DARK_1
            "SOLID2_DARK" -> AppTheme.SOLID_DARK_2
            "SOLID3_DARK" -> AppTheme.SOLID_DARK_3
            "SOLID4_DARK" -> AppTheme.SOLID_DARK_4
            "SOLID5_DARK" -> AppTheme.SOLID_DARK_5
            "SOLID6_DARK" -> AppTheme.SOLID_DARK_6
            "SOLID7_DARK" -> AppTheme.SOLID_DARK_7
            "SOLID8_DARK" -> AppTheme.SOLID_DARK_8
            "SOLID9_DARK" -> AppTheme.SOLID_DARK_9
            "SOLID10_DARK" -> AppTheme.SOLID_DARK_10
            "SOLID11_DARK" -> AppTheme.SOLID_DARK_11
            "SOLID12_DARK" -> AppTheme.SOLID_DARK_12
            else -> AppTheme.AUTO
        }
    ) {

        Log.e("DataBaseCopyOperationsMyKeyboardLayout", "TheSize: 1: " + suggestionList.size)
        if (imeService.mainSuggestionList.isNotEmpty()) {
            if (suggestionList.isNotEmpty()) {
                imeService.mainSuggestionList = suggestionList
            }
        }

        isSuggestionsEnabled = imeService.filterList.value.isNullOrEmpty() != true
        val background = Brush.linearGradient(
            colors = listOf(
                MaterialTheme.colorScheme.inverseSurface,
                MaterialTheme.colorScheme.inverseOnSurface
            )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White) // White background first
                .navigationBarsPadding()
                .padding(bottom = if (isGestureNavigationEnabled) 14.dp else 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(keyboardHeight)
                    .background(background)
            ) {
                MyCandidateView(
                    context = context,
                    imeService = imeService,
                    onEditTextSwitchClick = onEditTextSwitchClick,
                    onHusnEguftarSwitchClick = onHusnEguftarClick,
                    onEmojiSwitchClick = onEmojiSwitchClick,
                    onEmojiButtonClick = {
                        isEmojiButtonClicked = true
                        isStickerButtonClicked = false
                    },
                    onStickerButtonClick = {
                        isStickerButtonClicked = true
                        isEmojiButtonClicked = false
                    },
                    onBackClick = { onBackClick() },
                    onDragGestureToSelect = { dragAmount ->
                        imeService.selectTextGesture(dragAmount)
                    },
                    deleteTextAfterDrag = {
                        imeService.deleteText()
                        // Check if text field is empty after drag deletion
                        if (isTextFieldEmpty(inputConnection) && !isCapsLockEnabled) {
                            isCapsEnabled = true
                        }
                    },
                    onLongKeyPressed = { isEmoji ->
                        imeService.doSomethingWith(
                            key = Key(LABEL_DELETE, 1f),
                            isLongPressed = true,
                            isEmoji = isEmoji
                        )
                        if (!isCapsLockEnabled && isCapsEnabled) {
                            isCapsEnabled = false
                        }
                    },
                    onLongKeyPressedEnd = {
                        imeService.longPressedStops(key = Key(LABEL_DELETE, 1f))
                    },
                    onSuggestedWordSelected = {
                        onBackClick()
                    },
                    onDelete = { imeService.deleteEmoji() },
                    onChangeLanguageBtnClick = { languageChangedFromTop ->
                        // Pass the selected language directly
                        onLanguageSwitchClick(languageChangedFromTop)
                    },
                    currentLayout = currentLayout,
                    myPreferences = myPreferences,
                    layoutLabel = layoutLabel,
                    onVoiceInputClicked = { isClicked ->
                        isVoiceInputClicked = isClicked
                    },
                    isShowingSuggestions = isSuggestionsEnabled,
                    isVoiceInputClicked = isVoiceInputClicked,
                    currentLanguage = defaultLanguage
                )
                when (currentLayout) {
                    LayoutState.TextEdit -> {
                        EditTextLayout(
                            keyboardHeight,
                            specialKeys,
                            imeService,
                            context,
                            vibrator,
                        )
                    }

                    LayoutState.HusnEguftar -> {
                        HusnEguftarLayout(
                            keyboardHeight,
                            specialKeysHusneGuftar,
                            imeService,
                            context,
                            vibrator,
                            background,
                            onGuftarClick = {
                                imeService.commitHusnEguftar(" $it")
                            }
                        )
                    }

                    LayoutState.Emojis -> {
                        EmojiLayout(
                            context,
                            MaterialTheme.colorScheme.tertiary,
                            MaterialTheme.colorScheme.onSurfaceVariant,
                            isEmojiButtonClicked,
                            isStickerButtonClicked,
                            downloadedStickersPackList,
                            onEmojiClick = {
                                imeService.commitEmoji(it)
                            },
                            sendStickerTest = { file ->
                                imeService.sendSticker(context, file)
                            })
                    }

                    else -> {
                        // Composable layout for the keyboard
                        key(keys) {
                            // Adjust the keys based on whether the numbers row is shown
                            keys?.forEachIndexed { rowIndex, row ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            horizontal = if (defaultLanguage == "English") {
                                                if (showNumbersRow) {
                                                    if (rowIndex == 2 && currentLayout == LayoutState.Main) 18.dp
                                                    else dimensionResource(id = R.dimen.key_marginH).value.dp
                                                } else {
                                                    if (rowIndex == 1 && currentLayout == LayoutState.Main) 18.dp
                                                    else dimensionResource(id = R.dimen.key_marginH).value.dp
                                                }
                                            } else {
                                                dimensionResource(id = R.dimen.key_marginH).value.dp
                                            },
                                            vertical =
                                                if (showNumbersRow && ((currentLayout == LayoutState.Symbols) ||
                                                            (currentLayout == LayoutState.ExtendedSymbols))
                                                )
                                                    7.dp
                                                else
                                                    dimensionResource(id = R.dimen.key_marginV).value.dp
                                        )
                                        .weight(1f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    row.forEach { key ->
                                        CustomKey(
                                            key = key,
                                            isCapsEnabled = isCapsEnabled,
                                            isCapsLockEnabled = isCapsLockEnabled,
                                            onKeyPressed = {
                                                GlobalScope.launch(Dispatchers.Main) {
                                                    val getCompleteTextForSuggestion =
                                                        withContext(Dispatchers.IO) {
                                                            imeService.getCompleteTextForSuggestionAsync()
                                                        }
                                                    if (key.isCharacter) {
                                                        val endsWithSpace = false
                                                        isSuggestionsEnabled =
                                                            imeService.filterList.value?.isEmpty() != true
                                                        val fullText =
                                                            getCompleteTextForSuggestion + key.labelMain

                                                        imeService.currentWordMain =
                                                            returnTypedWordForSuggestion(
                                                                fullText,
                                                                "character"
                                                            )
                                                        if (imeService.currentWordMain != "") {
                                                            CoroutineScope(Dispatchers.Main).launch {
                                                                try {
                                                                    withContext(Dispatchers.IO) {
                                                                        imeService.filterList.value =
                                                                            searchForSuggestion(
                                                                                imeService,
                                                                                context,
                                                                                imeService.currentWordMain
                                                                            )
                                                                        if (imeService.filterList.value?.size == 0) {
                                                                            isSuggestionsEnabled =
                                                                                false
                                                                        }
                                                                    }
                                                                } catch (e: Exception) {
                                                                    e.printStackTrace()
                                                                }
                                                            }

                                                        }
                                                        imeService.commitText(
                                                            text = key,
                                                            isCapsEnabled = isCapsEnabled,
                                                            isCapsLockEnabled = isCapsLockEnabled,
                                                            endsWithSpace = endsWithSpace
                                                        )
                                                        if (fullText.endsWith(".")) {
                                                            isCapsEnabled = true
                                                        } else {
                                                            if (!isCapsLockEnabled && isCapsEnabled) {
                                                                isCapsEnabled = false
                                                            }
                                                        }
                                                    } else {
                                                        var isEmoji = false
                                                        if (key.labelMain == "Delete") {
                                                            val fullText =
                                                                getCompleteTextForSuggestion.toString()
                                                            imeService.currentWordMain =
                                                                returnTypedWordForSuggestion(
                                                                    fullText,
                                                                    "delete"
                                                                )
                                                            isEmoji =
                                                                if (fullText.isNotEmpty() && fullText.last()
                                                                        .toString() == " "
                                                                ) {
                                                                    false
                                                                } else {
                                                                    checkEmoji(
                                                                        ctx = context!!,
                                                                        fullText
                                                                    )
                                                                }

                                                            if (imeService.currentWordMain != "") {
                                                                GlobalScope.async {
                                                                    imeService.filterList.value =
                                                                        searchForSuggestion(
                                                                            imeService,
                                                                            context,
                                                                            imeService.currentWordMain
                                                                        )
                                                                }
                                                            }

                                                            // Check if this deletion will make text field empty
                                                            val willBeEmpty = fullText.length <= 1
                                                            if (willBeEmpty && !isCapsLockEnabled) {
                                                                isCapsEnabled = true
                                                            }
                                                        }

                                                        if (getCompleteTextForSuggestion.length == 1) {
                                                            isSuggestionsEnabled = false
                                                            imeService.filterList.value =
                                                                emptyList()
                                                        }

                                                        imeService.doSomethingWith(
                                                            key,
                                                            false,
                                                            isEmoji = isEmoji
                                                        )

                                                        // Check if text field is empty after deletion
                                                        if (key.labelMain == LABEL_DELETE && isTextFieldEmpty(
                                                                imeService.currentInputConnection
                                                            )
                                                            && !isCapsLockEnabled
                                                        ) {
                                                            isCapsEnabled = true
                                                        }
                                                    }
                                                }
                                            },
                                            onDragGestureToSelect = { dragAmount ->
                                                if (key.labelMain == LABEL_DELETE && myPreferences!!.getSwipeToDelete()) {
                                                    imeService.selectTextGesture(dragAmount)
                                                }
                                            },
                                            deleteTextAfterDrag = {
                                                if (key.labelMain == LABEL_DELETE) {
                                                    imeService.filterList.value = emptyList()
                                                    imeService.deleteText()

                                                    // Proper check for empty text field
                                                    GlobalScope.launch(Dispatchers.Main) {
                                                        delay(100) // Small delay to ensure deletion completes
                                                        if (imeService.isTextFieldCompletelyEmpty() && !isCapsLockEnabled) {
                                                            isCapsEnabled = true
                                                        }
                                                    }
                                                }
                                            },
                                            onLongKeyPressed = {
                                                imeService.filterList.value = emptyList()
                                                imeService.doSomethingWith(
                                                    key, true,
                                                    isSymbolEnabled = myPreferences!!.getLongPressForSymbols()
                                                )
                                                if (!isCapsLockEnabled && isCapsEnabled) {
                                                    isCapsEnabled = false
                                                }
                                            },
                                            onLongKeyPressedEnd = {
                                                imeService.longPressedStops(key = key)

                                                // Proper check for empty text field after long press
                                                GlobalScope.launch(Dispatchers.Main) {
                                                    delay(100) // Small delay
                                                    if (key.labelMain == LABEL_DELETE &&
                                                        imeService.isTextFieldCompletelyEmpty() &&
                                                        !isCapsLockEnabled) {
                                                        isCapsEnabled = true
                                                    }
                                                }
                                            },
                                            modifier = Modifier
                                                .padding(horizontal = 2.dp)
                                                .weight(key.weight),
                                            imeService = imeService,
                                            onLayoutSwitchClick = {
                                                onLayoutSwitchClick()
                                            },
                                            onExtendedSymbolsSwitchClick = {
                                                onExtendedSymbolsSwitchClick()
                                            },
                                            onLanguageSwitchClick = {
                                                onLanguageSwitchClick("")
                                            },
                                            onNumbersSwitchClick = {
                                                onNumbersSwitchClick()
                                            },
                                            onSymbolsLayoutSwitchClick = {
                                                onSymbolsLayoutSwitchClick()
                                            },
                                            onSymbolsUrduLayoutSwitchClick = {
                                                onSymbolsUrduLayoutSwitchClick()
                                            },
                                            onSymbolsSindhiLayoutSwitchClick = {
                                                onSymbolsSindhiLayoutSwitchClick()
                                            },
                                            onCapsClick = {
                                                onCapsClick()
                                            },
                                            onCapsClickToLock = {
                                                onCapsClickToLock()
                                            },
                                            context = context,
                                            vibrator = vibrator,
                                            iconResourceId = icon
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun checkEmoji(ctx: Context, character: String): Boolean {
    val emojiLists = ctx.getSharedPreferences("androidx.emoji2.emojipicker.preferences", Context.MODE_PRIVATE)?.
    getString("pref_key_custom_emoji_freq", null)?.
    split(",")?.
    associate { entry -> entry.split("=", limit = 2).
    takeIf { it.size == 2 }?.
    let { it[0] to it[1].toInt() } ?: ("" to 0) }?.
    toMutableMap() ?: mutableMapOf()
    var returnResult = false
    emojiLists.keys.forEach loop@{ element ->
        if (character.endsWith(element)) {
            returnResult = true
            return@loop
        }
    }
    return returnResult
}

fun returnTypedWordForSuggestion(fullTextMain: String, from: String): String {
    val originalWord = fullTextMain
    val lastWhitespaceIndex = originalWord.lastIndexOf(' ')
    val modifiedText = if (lastWhitespaceIndex != -1) {
        originalWord.substring(lastWhitespaceIndex + 1)
    } else {
        originalWord
    }

    if (from == "delete") {
        if (modifiedText.isNotEmpty()) {
            return modifiedText.substring(0, modifiedText.length - 1)
        } else if (lastWhitespaceIndex != -1 && originalWord.isNotEmpty()) {
            val previousWord = originalWord.substring(0, lastWhitespaceIndex)
            if (previousWord.contains(' ')) {
                val words = previousWord.split(' ')
                return words.last()
            }
            return previousWord
        }
    }

    return modifiedText
}

suspend fun searchForSuggestion(imeService: CustomImeService?, context: Context?, searchWord: String): List<SuggestionItems> {
    return if (imeService?.mainSuggestionList?.isEmpty() == true) {
        Log.e("DataBaseCopyOperationsMyKeyboardLayout","CustomKeyboard: 2: " + imeService.mainSuggestionList.size)
        if (suggestionList.isNotEmpty()) {
            imeService.mainSuggestionList = suggestionList
        } else {
            DataBaseCopyOperationsKt.init(context!!)
            Log.e("DataBaseCopyOperationsMyKeyboardLayout","CustomKeyboard: DangerZone: " + imeService.mainSuggestionList.size)
            val deferredList = GlobalScope.async(Dispatchers.IO) {
                DataBaseCopyOperationsKt.getAllItems() as MutableList<SuggestionItems>
            }
            imeService.mainSuggestionList = deferredList.await()
        }
        filterSuggestions(imeService, searchWord, getSelectedLanguage(context!!))
    } else {
        Log.e("DataBaseCopyOperationsMyKeyboardLayout","CustomKeyboard: 4: ")
        filterSuggestions(imeService, searchWord, getSelectedLanguage(context!!))
    }
}

private fun filterSuggestions(imeService: CustomImeService?, searchWord: String, lang: String): List<SuggestionItems> {
    return imeService?.mainSuggestionList?.filter { suggestion: SuggestionItems ->
        if (lang == "English") {
            suggestion.engRomanWordsSuggestion?.startsWith(searchWord, ignoreCase = true) == true
        } else {
            suggestion.urduWordsSuggestion?.startsWith(searchWord, ignoreCase = true) == true
        }
    } ?: emptyList()
}

