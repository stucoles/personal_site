package com.adkinscoles.portfolio.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.adkinscoles.portfolio.Background
import com.adkinscoles.portfolio.components.sections.NavHeader
import com.varabyte.kobweb.compose.css.BackgroundImage
import com.varabyte.kobweb.compose.css.BackgroundSize
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import kotlinx.browser.document
import org.jetbrains.compose.web.css.AlignContent
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Div

val PageContentStyle = CssStyle {
    base { Modifier.fillMaxHeight().padding(leftRight = 2.cssRem, topBottom = 4.cssRem).alignContent(AlignContent.End).justifyContent(
        JustifyContent.End)}
    Breakpoint.MD { Modifier.alignContent(AlignContent.Start).fillMaxWidth() }
    Breakpoint.MD { Modifier.maxWidth(60.cssRem) }
}

@Composable
fun PageLayout(title: String, backgroundImage: BackgroundImage = Background.image, content: @Composable ColumnScope.() -> Unit) {
    LaunchedEffect(title) {
        document.title = "Stuart Adkins Coles"
    }

    Box(
        Modifier
            .fillMaxWidth()
            .minHeight(100.percent)
            .backgroundImage(backgroundImage)
            .backgroundSize(BackgroundSize.Cover),
        contentAlignment = Alignment.Center
    ) {
        Column(
            // Isolate the content, because otherwise the absolute-positioned SVG above will render on top of it.
            // This is confusing but how browsers work. Read up on stacking contexts for more info.
            // https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_positioned_layout/Understanding_z-index/Stacking_context
            // Some people might have used z-index instead, but best practice is to avoid that if possible, because
            // as a site gets complex, Z-fighting can be a huge pain to track down.
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavHeader()
            Div(PageContentStyle.toAttrs()) {
                content()
            }
        }
    }
}
