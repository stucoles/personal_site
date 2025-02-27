package com.adkinscoles.portfolio.pages

import androidx.compose.runtime.Composable
import com.adkinscoles.portfolio.HeadlineTextStyle
import com.adkinscoles.portfolio.SubheadlineTextStyle
import com.adkinscoles.portfolio.components.layouts.PageLayout
import com.adkinscoles.portfolio.toSitePalette
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.invert
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

val HeroContainerStyle = CssStyle {
    base { Modifier.fillMaxWidth().gap(2.cssRem).margin { bottom(7.vh) } }
    Breakpoint.MD { Modifier.margin { bottom(0.vh) } }
    Breakpoint.MD { Modifier.margin { top(0.vh)} }
    Breakpoint.XL { Modifier.margin { top(15.vh) } }
}

@Page
@Composable
fun HomePage() {
    PageLayout("Home") {
        Row(HeroContainerStyle.toModifier()) {
            Box {
                val sitePalette = ColorMode.current.toSitePalette()
                Column(Modifier.gap(2.cssRem)
                    .background(sitePalette.brand.primary.toRgb().copyf(alpha = 0.6f))
                    .padding(2.cssRem)
                    .borderRadius(8.px)
                    .backdropFilter(blur(30.px)),
                   horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Div(HeadlineTextStyle.toAttrs()) {
                        SpanText(
                            "Stuart Adkins Coles", Modifier.color(
                                when (ColorMode.current) {
                                    ColorMode.LIGHT -> Color.black
                                    ColorMode.DARK -> Color.ghostwhite
                                }
                            )
                        )
                    }

                    Div(SubheadlineTextStyle.toAttrs()) {
                        SpanText("software engineer - san francisco bay area", Modifier.color(
                            when (ColorMode.current) {
                                ColorMode.LIGHT -> Color.black
                                ColorMode.DARK -> Color.ghostwhite
                            }
                        ))
                    }

                    Div(SubheadlineTextStyle.toAttrs()) {
                        val filter = when (ColorMode.current) {
                            ColorMode.LIGHT -> invert()
                            ColorMode.DARK -> blur(0.px)
                        }
                        Link(path = "https://www.linkedin.com/in/stuart-coles/", ) {
                            // Block display overrides inline display of the <img> tag, so it calculates centering better
                            Image(
                                "/linkedin.png",
                                "linkedIn",
                                Modifier.height(2.cssRem).display(DisplayStyle.Block).filter(filter),
                            )
                        }
                    }



//                    val ctx = rememberPageContext()
//                    Button(onClick = {
//                        // Change this click handler with your call-to-action behavior
//                        // here. Link to an order page? Open a calendar UI? Play a movie?
//                        // Up to you!
//                        ctx.router.tryRoutingTo("/about")
//                    }, colorPalette = ColorPalettes.Blue) {
//                        Text("Read More")
//                    }
                }
            }
        }
    }
}
