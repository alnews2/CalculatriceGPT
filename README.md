# CalculatriceGPT

[![Android CI](https://github.com/alnews2/CalculatriceGPT/actions/workflows/android.yml/badge.svg)](https://github.com/alnews2/CalculatriceGPT/actions/workflows/android.yml)
![Android](https://img.shields.io/badge/Android-API%2036-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?logo=jetpackcompose&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-9.6-02303A?logo=gradle&logoColor=white)

Application Android native de calculatrice à quatre opérations.

## Technologies

- Kotlin 2.2.10 / Kotlin intégré à AGP
- Android Gradle Plugin 9.4.0
- Jetpack Compose
- Gradle 9.6
- JDK 17

Le projet utilise une version stable du Compose BOM compatible avec l'API Android 36.

## Fonctionnalités du prototype

- addition
- soustraction
- multiplication
- division
- effacement
- affichage du résultat
- gestion de la division par zéro

La logique de calcul est séparée de l'interface afin de permettre son évolution sans coupler le moteur de calcul à Compose.

## Tests

Les quatre opérations et la division par zéro disposent de tests unitaires.

## CI

GitHub Actions exécute les tests Gradle et produit un APK debug téléchargeable comme artefact.

## État du projet

Version initiale 0.1.0, en cours de validation.
