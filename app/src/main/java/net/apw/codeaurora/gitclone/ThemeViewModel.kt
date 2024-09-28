package net.apw.codeaurora.gitclone;

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    var isDarkTheme = mutableStateOf(false)
    
    fun toggleTheme() {
        isDarkTheme.value = !isDarkTheme.value
    }
}