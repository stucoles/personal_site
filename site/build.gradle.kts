import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

group = "com.adkinscoles.portfolio"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("I wrote this page in Kobweb!")
            head.add {
                link(rel = "preconnect", href = "https://fonts.googleapis.com")
                link(
                    rel = "preconnect",
                    href = "https://fonts.gstatic.com"
                ) { attributes["crossorigin"] = "" }
                link(
                    href = "https://fonts.googleapis.com/css2?family=Domine:wght@400..700&family=IBM+Plex+Sans:wght@500&display=swap",
                    rel = "stylesheet"
                )
                link(rel = "apple-touch-icon", href = "/icons/apple-touch-icon.png")
                link(rel = "icon", type = "image/png", href = "/icons/favicon-32x32.png")
                link(rel = "icon", type = "image/png", href = "/icons/favicon-16x16.png")
                link(rel = "manifest", href = "/icons/site.webmanifest")
            }
        }
    }
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("portfolio" /*, includeServer = true*/)

    sourceSets {
//        commonMain.dependencies {
//          // Add shared dependencies between JS and JVM here if building a fullstack app
//        }

        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            // This default template uses built-in SVG icons, but what's available is limited.
            // Uncomment the following if you want access to a large set of font-awesome icons:
            // implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
        }

        // Uncomment the following if you pass `includeServer = true` into the `configAsKobwebApplication` call.
//        jvmMain.dependencies {
//            compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
//        }
    }
}
