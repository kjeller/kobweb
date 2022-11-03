package com.varabyte.kobweb.compose.foundation.layout

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.ElementRefListener
import com.varabyte.kobweb.compose.dom.registerRefListener
import com.varabyte.kobweb.compose.style.toClassName
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.asAttributesBuilder
import com.varabyte.kobweb.compose.ui.attrsModifier
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLElement

class ColumnScope {
    fun Modifier.align(alignment: Alignment.Horizontal) = attrsModifier {
        classes("${alignment.toClassName()}-self")
    }
}

@Composable
fun Column(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    refListener: ElementRefListener<HTMLElement>? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Div(modifier.asAttributesBuilder {
        classes("kobweb-col", verticalArrangement.toClassName(), horizontalAlignment.toClassName())
        registerRefListener(refListener)
    }) {
        ColumnScope().content()
    }
}