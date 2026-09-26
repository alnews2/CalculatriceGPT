# Journal des modifications

## [À venir]

## [0.4.0] - 2026-09-26

### Ajouté

- Différenciation visuelle des touches numériques par une couleur dédiée.
- Forme carrée pour les touches non numériques afin de distinguer les commandes et les opérateurs.

### Modifié

- Centrage du titre « CalculatriceGPT » dans son bandeau.

### Documentation

- Mise à jour du README.md pour décrire les évolutions de l'interface.

## [0.3.0] - 2026-09-26

### Ajouté

- Ajout d'un menu « Édition » avec les commandes « Copier » et « Coller ».
- Ajout de la copie du contenu de la zone « Résultat » vers le presse-papier.
- Ajout du collage du contenu du presse-papier après remplacement du contenu de la zone « Résultat ».

### Documentation

- Mise à jour du README.md et de la documentation développeur pour décrire les commandes de copie et de collage.

## [0.2.0] - 2026-09-25

### Ajouté

- Ajout d'une zone de résultat noire avec texte blanc.
- Ajout d'un pied de fenêtre en italique indiquant que l'application a été générée par ChatGPT.
- Ajout d'un bandeau de titre de l'application.
- Ajout d'un bouton dédié à la fermeture de l'application.
- Ajout d'une marge de 5 mm autour des principaux éléments de l'interface.

### Documentation

- Ajout de la documentation développeur couvrant l'architecture, le développement, la CI/CD, SemVer, les releases et la maintenance documentaire.
- Ajout d'un script de préparation des versions SemVer et d'un workflow de validation des versions sur les PR.

### Corrigé

- Le workflow de release compare désormais la version de l'application avec la dernière release SemVer publiée et ignore les releases utilisant une version déjà publiée.

## [0.1.0] - 2026-09-25

### Ajouté

- Création de la structure initiale du projet Android.
- Mise en place de Kotlin et Jetpack Compose.
- Création de l'interface de calculatrice à quatre opérations.
- Séparation de la logique métier de calcul.
- Ajout des tests unitaires pour les quatre opérations et la division par zéro.
- Ajout du workflow GitHub Actions exécutant les tests et construisant un APK debug.
- Ajout du workflow de release GitHub déclenché après une CI réussie sur `main`.
- Création automatique des tags de version selon la convention `v<version>`.
- Validation stricte de la version de l'application selon SemVer 2.0.0.
- Ajout automatique de l'APK validé à la release GitHub.
- Utilisation automatique de la section correspondante du CHANGELOG.md comme notes de release.
