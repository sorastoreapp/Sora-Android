package com.sora.sora.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp




    fun vFactor(value: Int): Dp {
        val DEFAULT_V_FACTOR = 1.1
        return (value* DEFAULT_V_FACTOR).dp
    }

    fun hFactor(value: Int): Dp {
        val DEFAULT_H_FACTOR = 1.045
        return (value* DEFAULT_H_FACTOR).dp
    }
