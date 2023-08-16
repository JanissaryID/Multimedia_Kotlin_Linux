import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
//    id("org.openjfx.javafxplugin") version "0.0.14"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

//javafx {
//    version = "20"
//    modules("javafx.controls", "javafx.fxml", "javafx.media", "javafx.swing", "javafx.graphics")
//}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("net.samuelcampos:usbdrivedetector:2.2.1")
    implementation("uk.co.caprica:vlcj-javafx:1.2.0")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Multimedia_Kotlin_Linux"
            packageVersion = "1.0.0"
        }
    }
}
