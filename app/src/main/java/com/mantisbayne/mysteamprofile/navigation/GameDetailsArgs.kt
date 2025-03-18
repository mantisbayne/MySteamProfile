package com.mantisbayne.mysteamprofile.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameDetailsArgs(
    val title: String,
    val playtime: String,
    val imageUrl: String?,
    val lastPlayed: String
) : Parcelable
