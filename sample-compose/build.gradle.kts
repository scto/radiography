import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.android.application")
  kotlin("android")
}

android {
  compileSdkVersion(30)

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  defaultConfig {
    minSdkVersion(21)
    targetSdkVersion(30)
    applicationId = "com.squareup.radiography.sample.compose"
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerVersion = Versions.KotlinCompiler
    kotlinCompilerExtensionVersion = Versions.Compose
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs = listOf(
        "-Xallow-jvm-ir-dependencies",
        "-Xskip-prerelease-check",
        "-Xopt-in=kotlin.RequiresOptIn"
    )
  }
}

dependencies {
  // Don't use Versions.KotlinStdlib for Kotlin stdlib, since this module actually uses the Compose
  // compiler and needs the latest stdlib.

  implementation(project(":radiography"))
  implementation(Dependencies.Compose.Tooling)
  implementation("androidx.compose.material:material:${Versions.Compose}")
  implementation("androidx.appcompat:appcompat:1.2.0")
}
