# Documentation développeur

## 1. Objectif

CalculatriceGPT est une application Android native de calculatrice à quatre opérations. Le projet évolue progressivement en conservant une séparation entre logique métier, interface et CI/CD.

## 2. Stack technique

- Android
- Kotlin 2.2.10
- Android Gradle Plugin 9.4.0
- Jetpack Compose
- Gradle 9.6
- JDK 17
- compileSdk / targetSdk 36
- minSdk 28

## 3. Organisation

- `app/src/main/java/fr/alnews2/calculatricegpt/` : code Kotlin.
- `app/src/test/` : tests unitaires.
- `.github/workflows/` : CI, validation SemVer et releases.
- `scripts/bump_version.py` : préparation des versions.
- `README.md` : résumé du projet.
- `CHANGELOG.md` : historique.
- `docs/DEVELOPMENT.md` : documentation développeur.

## 4. Interface et presse-papier

La zone Résultat est pilotée par l'état `display` dans le composable principal.

Le menu **Édition** contient :

- **Copier** : place exactement le contenu actuellement affiché dans Résultat dans le presse-papier ;
- **Coller** : lit le texte du presse-papier et remplace entièrement le contenu de Résultat.

Après un collage, les valeurs d'opération en cours sont réinitialisées afin que le texte collé constitue le nouvel affichage de départ. Si aucun texte n'est disponible, l'affichage revient à `0`.

## 5. Développement local

Depuis la racine :

    gradle test
    gradle assembleDebug

Une modification métier doit être accompagnée des tests correspondants.

## 6. CI GitHub Actions

La CI exécute les tests unitaires, construit l'APK debug et publie l'artefact. La validation SemVer est également exécutée sur les PR.

Une PR ne doit pas être considérée comme validée tant que les tests et le build n'ont pas réussi.

## 7. Workflow Git et SemVer

Les développements sont réalisés sur une branche dédiée. Pour une nouvelle fonctionnalité rétrocompatible, préparer une augmentation **minor** de SemVer avant la PR.

Le flux attendu est :

1. créer une branche ;
2. choisir et préparer la version SemVer ;
3. développer et tester ;
4. mettre à jour README.md, CHANGELOG.md et cette documentation si nécessaire ;
5. ouvrir la PR ;
6. attendre la CI ;
7. tester l'APK ;
8. effectuer la validation et la fusion vers `main`.

La fusion finale reste une validation humaine.

## 8. Releases

Après une CI Android réussie sur `main`, le workflow de release lit `versionName`, valide SemVer, compare la dernière release, puis crée le tag et la release si la version est supérieure.

Un changement de code destiné à une nouvelle fonctionnalité doit donc arriver dans la PR avec la version SemVer correspondante.

## 9. Maintenance documentaire

À chaque évolution, vérifier :

1. README.md pour le résumé et l'état courant ;
2. CHANGELOG.md pour l'historique ;
3. docs/DEVELOPMENT.md lorsque l'architecture, les outils, les commandes, les tests, la CI/CD ou le processus changent.
