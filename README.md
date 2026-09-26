# CalculatriceGPT

[![Android CI](https://github.com/alnews2/CalculatriceGPT/actions/workflows/android.yml/badge.svg)](https://github.com/alnews2/CalculatriceGPT/actions/workflows/android.yml)
![Android](https://img.shields.io/badge/Android-API%2036-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?logo=jetpackcompose&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-9.6-02303A?logo=gradle&logoColor=white)

Application Android native de calculatrice à quatre opérations.

## Résumé du projet

Calculatrice Android native développée en Kotlin et Jetpack Compose.

**Version préparée : 0.5.0**

La version 0.5.0 améliore la convention d’interface Android : le titre « CalculatriceGPT » est placé en haut à gauche dans un bandeau limité au titre, avec une taille légèrement réduite ; les commandes « Copier » et « Coller » sont accessibles depuis un menu « Overflow » à trois points verticaux placé à droite sur la même ligne.

La version 0.4.0 améliore la lisibilité de l'interface : les touches numériques utilisent une couleur dédiée, les touches non numériques sont carrées et le titre « CalculatriceGPT » est centré.

La version 0.3.0 ajoute un menu « Édition » avec les commandes « Copier » et « Coller » pour le contenu de la zone Résultat.

La version 0.2.0 apporte l'affichage du résultat avec texte blanc sur fond noir et un pied de fenêtre en italique indiquant que l'application a été générée par une intelligence artificielle ChatGPT.

## Technologies

- Kotlin 2.2.10
- Android Gradle Plugin 9.4.0
- Jetpack Compose
- Gradle 9.6
- JDK 17
- compileSdk / targetSdk 36
- minSdk 28

## Fonctionnalités du prototype

- addition
- soustraction
- multiplication
- division
- effacement
- affichage du résultat
- affichage du résultat dans une zone noire avec texte blanc
- touches numériques avec une couleur dédiée
- touches non numériques de forme carrée
- menu « Overflow » à trois points verticaux avec commandes « Copier » et « Coller »
- gestion de la division par zéro
- interface avec titre en haut à gauche, menu « Overflow » à droite et bouton de fermeture
- marge d'environ 5 mm autour des éléments principaux
- pied de fenêtre centré en italique

La logique de calcul est séparée de l'interface afin de permettre son évolution sans coupler le moteur de calcul à Compose.

## Tests

Les quatre opérations et la division par zéro disposent de tests unitaires.

## CI

GitHub Actions exécute les tests Gradle, valide la politique de version SemVer sur les PR et produit un APK debug téléchargeable comme artefact.

## Documentation

- [Documentation développeur](docs/DEVELOPMENT.md) : architecture, commandes, tests, CI/CD, workflow Git/PR, SemVer, releases et maintenance documentaire.
- [Journal des modifications (CHANGELOG.md)](CHANGELOG.md) : historique détaillé des fonctionnalités, corrections et évolutions.

## Cycle de maintenance documentaire

Toute modification fonctionnelle ou technique doit mettre à jour, lorsque nécessaire, README.md, CHANGELOG.md et docs/DEVELOPMENT.md.
