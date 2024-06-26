package com.varabyte.kobweb.compose.foundation.layout

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.ElementRefScope
import com.varabyte.kobweb.compose.dom.registerRefScope
import com.varabyte.kobweb.compose.style.toClassName
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLElement

@LayoutScopeMarker
@Immutable // TODO(#554): Remove annotation after upstream fix
interface BoxScope {
    fun Modifier.align(alignment: Alignment) = attrsModifier {
        classes("${alignment.toClassName()}-self")
    }
}

internal object BoxScopeInstance : BoxScope

object BoxDefaults {
    val ContentAlignment: Alignment = Alignment.TopStart
}

/**
 * Add classes that tell the browser to display this element as a column.
 *
 * This method is public as there may occasionally be cases where users could benefit from using this, but in general
 * you shouldn't reach for this unless you know what you're doing.
 *
 * NOTE: This modifier sets attribute properties and can therefore not be used within CssStyles.
 */
fun Modifier.boxClasses(contentAlignment: Alignment = BoxDefaults.ContentAlignment) =
    this.classNames("kobweb-box", contentAlignment.toClassName())

@Composable
fun Box(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    ref: ElementRefScope<HTMLElement>? = null,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Div(attrs = modifier.boxClasses(contentAlignment).toAttrs()) {
        registerRefScope(ref)
        BoxScopeInstance.content()
    }
}
