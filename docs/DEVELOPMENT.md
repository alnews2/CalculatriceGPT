# Documentation développeur

## 1. Objectif

CalculatriceGPT est une application Android native de calculatrice à quatre opérations. Le projet est conçu pour évoluer progressivement tout en conservant une séparation claire entre logique métier, interface et automatisation CI/CD.

Cette documentation décrit les règles utiles pour reprendre le développement sans dépendre de l'historique des conversations.

## 2. Stack technique

- Android
- Kotlin 2.2.10
- Android Gradle Plugin 9.4.0
- Jetpack Compose
- Gradle 9.6
- JDK 17
- compileSdk / targetSdk 36
- minSdk 28

## 3. Organisation du projet

- app/src/main/java/fr/alnews2/calculatricegpt/ : code Kotlin de l'application.
- app/src/test/ : tests unitaires de la logique métier.
- .github/workflows/android.yml : CI Android.
- .github/workflows/release.yml : automatisation des tags et releases.
- README.md : résumé actuel du projet et point d'entrée documentaire.
- CHANGELOG.md : historique des changements destiné aux utilisateurs et aux releases.
- docs/DEVELOPMENT.md : documentation destinée aux développeurs.

## 4. Principes d'architecture

La logique de calcul doit rester indépendante de l'interface Compose autant que possible.

La couche métier porte les opérations et leurs règles, notamment la gestion de la division par zéro. L'interface Compose se charge de l'affichage, des interactions utilisateur et de la présentation des résultats.

Lorsqu'une fonctionnalité nouvelle peut être isolée dans une responsabilité distincte, privilégier cette séparation plutôt que d'accumuler de la logique métier dans les composables.

## 5. Développement local

Depuis la racine du dépôt :

    gradle test
    gradle assembleDebug

Le premier contrôle avant une PR doit être l'exécution des tests et la compilation de l'APK debug.

Pour une modification de logique métier, ajouter ou mettre à jour les tests unitaires correspondants.

## 6. CI GitHub Actions

La CI exécute :

1. checkout du dépôt ;
2. installation de JDK 17 ;
3. configuration de Gradle 9.6 ;
4. installation des composants Android nécessaires ;
5. tests unitaires avec gradle test ;
6. build debug avec gradle assembleDebug ;
7. publication de l'APK debug comme artefact.

Une PR ne doit pas être considérée comme validée tant que les tests et le build n'ont pas réussi.

## 7. Workflow Git

Les développements doivent être réalisés sur une branche dédiée, par exemple :

    feature/nom-de-la-fonctionnalite
    fix/nom-du-probleme
    docs/nom-de-la-documentation

Le flux attendu est :

1. créer une branche à partir de main ;
2. développer et tester ;
3. mettre à jour la documentation concernée ;
4. mettre à jour CHANGELOG.md ;
5. ouvrir une PR vers main ;
6. attendre la CI ;
7. télécharger et tester fonctionnellement l'APK si nécessaire ;
8. effectuer la validation et la fusion vers main.

La fusion finale reste une étape de validation humaine.

## 8. Versionnement SemVer

Le projet utilise Semantic Versioning 2.0.0.

Exemples valides :

- 0.1.0
- 0.2.0
- 1.0.0
- 1.2.3-alpha.1
- 1.2.3+build.45

Le versionName Android doit contenir uniquement la version SemVer, sans préfixe v.

Les tags GitHub utilisent la convention v<version>.

Exemple : versionName = 0.2.0 donne le tag v0.2.0.

## 9. CHANGELOG

Toute modification du projet doit être examinée pour déterminer si elle doit apparaître dans CHANGELOG.md. Pour une modification destinée à la prochaine version, elle est ajoutée sous [Unreleased].

Le changelog doit rester exploitable comme notes de release. Il ne doit pas contenir de références temporaires à une PR ou à son statut.

Lorsqu'une version est publiée, la section correspondante sert de base aux notes de la GitHub Release.

## 10. Releases

La release est déclenchée après une CI Android réussie sur main.

Le workflow de release :

1. récupère le commit exact validé par la CI ;
2. lit versionName dans la configuration Android ;
3. vérifie la conformité SemVer 2.0.0 ;
4. compare la version avec la dernière release publiée ;
5. ne crée aucune nouvelle release si la même version SemVer est déjà publiée ;
6. échoue explicitement si la version est inférieure à la dernière version publiée ;
7. crée le tag v<version> et la GitHub Release si la version est supérieure ;
8. attache l'APK issu de la CI validée ;
9. utilise la section correspondante de CHANGELOG.md comme notes de release.

Un changement documentaire ou CI sans augmentation de version ne doit donc pas produire une nouvelle release.

## 11. Maintenance documentaire

À chaque évolution, vérifier les trois niveaux suivants.

### README.md

Mettre à jour le résumé si une fonctionnalité, la stack, l'état ou les points d'entrée du projet changent.

### CHANGELOG.md

Ajouter le changement sous [Unreleased] lorsqu'il doit être tracé dans l'historique du projet.

### docs/DEVELOPMENT.md

Mettre à jour cette documentation si l'évolution modifie :

- l'architecture ;
- l'organisation des fichiers ;
- les commandes de développement ;
- les tests ;
- la CI/CD ;
- le processus Git/PR ;
- le versionnement ou les releases.

Cette règle permet de conserver une documentation utilisable même lorsque le projet évolue sur une longue période.
