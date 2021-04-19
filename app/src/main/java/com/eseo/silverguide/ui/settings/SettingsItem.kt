package com.eseo.silverguide.ui.settings

data class SettingsItem(val name: String, val icon: Int, val onClick: (() -> Unit)? = null)
