package com.reishandy.grisaiacharacters.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Character(
    @StringRes val characterType: Int,
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)
