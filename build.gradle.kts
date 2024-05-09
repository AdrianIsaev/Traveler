buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
    }
}
// Верхний уровень файла сборки, где вы можете добавить конфигурационные опции общие для всех подпроектов/модулей.
plugins {
    id("com.android.application") version "8.1.1" apply false;
    id("org.jetbrains.dokka") version "1.9.20"
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false;
    id("com.google.gms.google-services") version "4.4.1" apply false
}
