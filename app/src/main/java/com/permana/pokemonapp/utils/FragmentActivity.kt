package com.permana.pokemonapp.utils

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun FragmentActivity.getLaunch(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> Unit) =
    lifecycleScope.launch(context = context) { block.invoke(this) }
