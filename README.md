# CalculatriceGPT

[![Android CI](https://github.com/alnews2/CalculatriceGPT/actions/workflows/android.yml/badge.svg)](https://github.com/alnews2/CalculatriceGPT/actions/workflows/android.yml)
![Android](https://img.shields.io/badge/Android-API%2036-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?logo=jetpackcompose&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-9.6-02303A?logo=gradle&logoColor=white)

Application Android native de calculatrice à quatre opérations.

## Résumé du projet

Calculatrice Android native développée en Kotlin et Jetpack Compose. Le projet sert de base à une évolution progressive de l'interface, de la logique métier, des tests et de la chaîne CI/CD.

**Dernière version publiée : 0.1.0**

Le résumé de ce fichier doit rester à jour lorsque les fonctionnalités, la stack technique ou l'état du projet évoluent. L'historique détaillé des changements est conservé dans CHANGELOG.md.

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
- interface avec bandeau de titre et bouton de fermeture
- marge d'environ 5 mm autour des éléments principaux

La logique de calcul est séparée de l'interface afin de permettre son évolution sans coupler le moteur de calcul à Compose.

## Tests

Les quatre opérations et la division par zéro disposent de tests unitaires.

## CI

GitHub Actions exécute les tests Gradle, valide la politique de version SemVer sur les PR et produit un APK debug téléchargeable comme artefact.

## Documentation développeur

La documentation technique et les règles de contribution sont regroupées dans [docs/DEVELOPMENT.md](docs/DEVELOPMENT.md). Elle décrit notamment :

- l'organisation du projet ;
- l'architecture et les responsabilités des composants ;
- les commandes de test et de build ;
- le fonctionnement de la CI ;
- le processus de branche, PR et validation ;
- le versionnement SemVer 2.0.0 et la préparation des versions ;
- le fonctionnement des releases et du CHANGELOG.md.

## Cycle de maintenance documentaire

Toute modification fonctionnelle ou technique doit mettre à jour, lorsque nécessaire :

1. README.md pour le résumé et l'état général du projet ;
2. CHANGELOG.md pour l'historique des changements ;
3. docs/DEVELOPMENT.md lorsque les règles, l'architecture, les outils ou le processus de développement changent.
