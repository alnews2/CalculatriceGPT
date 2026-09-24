# CalculatriceGPT

Application Android native de calculatrice à quatre opérations.

## Technologies

- Kotlin 2.2.10 / Kotlin intégré à AGP
- Android Gradle Plugin 9.4.0
- Jetpack Compose
- Gradle 9.6
- JDK 17

Le projet utilise la version stable du Compose BOM recommandée par la documentation Android (2026.09.00).

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
