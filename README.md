# Jeu de Mémoire des Lettres

## Contents
1. [Introduction](#introduction)
2. [Objectifs du Projet](#objectifs-du-projet)
3. [Description Technique](#description-technique)
    1. [Interface Utilisateur](#interface-utilisateur)
    2. [Logique du Jeu](#logique-du-jeu)
    3. [Composants Principaux](#composants-principaux)
    4. [Gestion des Événements](#gestion-des-événements)
4. [Fonctionnalités et Comportement](#fonctionnalités-et-comportement)
    1. [Mélange et Réinitialisation](#mélange-et-réinitialisation)
    2. [Suivi des Tentatives et Correspondances](#suivi-des-tentatives-et-correspondances)
    3. [Message de Victoire](#message-de-victoire)
5. [Test et Validation](#test-et-validation)
6. [Temps Passé sur Chaque Partie](#temps-passé-sur-chaque-partie)
7. [Problèmes Rencontrés](#problèmes-rencontrés)
8. [Qualité du Code](#qualité-du-code)
9. [Présentation Finale](#présentation-finale)

## Introduction

Le projet consiste à créer un jeu de mémoire interactif sous forme d'application Android. L'objectif principal du jeu est d'améliorer la mémoire visuelle des joueurs en associant des cartes qui portent des images identiques. Ce jeu est conçu pour être simple, mais suffisamment engageant pour maintenir l'intérêt du joueur à travers plusieurs niveaux de difficulté.

## Objectifs du Projet

Les objectifs principaux du projet sont :
- Créer une application Android fonctionnelle de jeu de mémoire des lettres.
- Concevoir un tableau de jeu de cartes qui s'affichent sous forme d'images, avec un système de retournement des cartes et de correspondance.
- Implémenter un système de comptabilisation des tentatives et des correspondances réussies.
- Ajouter une fonctionnalité de réinitialisation du jeu et de mélange des cartes.
- Afficher un message de victoire lorsque toutes les cartes sont appariées.

## Description Technique

### 1. Interface Utilisateur
L'interface utilisateur du jeu est simple et facile à naviguer. Elle est composée de :
- Un tableau de cartes représentées par des boutons d'image.
- Un compteur pour le nombre de tentatives (flips) et un compteur pour les correspondances (matches).
- Un bouton de mélange pour réinitialiser le jeu.
- Un message de victoire qui s'affiche lorsque toutes les cartes ont été correctement appariées.

### 2. Logique du Jeu
La logique du jeu repose sur les éléments suivants :
- **Cartes** : Chaque carte est représentée par un `ImageButton`. Chaque carte a une image qui peut être retournée.
- **Mélange des cartes** : Lorsque le jeu est lancé ou réinitialisé, les cartes sont mélangées aléatoirement.
- **Retourner les cartes** : Lorsqu'une carte est cliquée, elle retourne l'image de la carte. Le joueur peut retourner une deuxième carte pour tenter de faire une correspondance.
- **Vérification de correspondance** : Si les deux cartes retournées ont la même image, elles sont considérées comme appariées et elles sont désactivées pour éviter de les retourner à nouveau.
- **Temps de verrouillage du tableau** : Si les cartes ne correspondent pas, elles sont retournées après un délai d'une seconde.

### 3. Composants Principaux
1. **Cartes (ImageButton)** : Chaque carte est un bouton d'image qui affiche soit une image de face, soit une image de dos (lorsqu'elle est retournée).
2. **Compte des flips et des matches** : Les variables `flip_count` et `match_count` permettent de suivre respectivement le nombre de tentatives et de correspondances réussies.
3. **Mélange des cartes** : La méthode `shuffleBoard()` permet de mélanger les cartes et de réinitialiser l'état du jeu.
4. **Réinitialisation du jeu** : Après chaque victoire, un message de succès est affiché et le jeu peut être réinitialisé.

### 4. Gestion des Événements
Le jeu est basé sur des événements de clics qui sont gérés dans les listeners associés aux `ImageButton`. Lorsqu'une carte est cliquée :
- Si c'est la première carte, elle est retournée.
- Si c'est la deuxième carte, elle est comparée à la première. Si elles correspondent, elles sont désactivées.
- Si elles ne correspondent pas, elles sont retournées après un délai de 1 seconde.

## Fonctionnalités et Comportement

### 1. Mélange et Réinitialisation
Le jeu commence avec les cartes mélangées et placées face cachée. Le joueur peut mélanger les cartes à tout moment en appuyant sur le bouton "Shuffle". Cela réinitialise le jeu, réactive les cartes et remet les compteurs à zéro.

### 2. Suivi des Tentatives et Correspondances
À chaque fois qu'une carte est retournée, le compteur de tentatives (`flips`) est incrémenté. Lorsque deux cartes sont appariées, le compteur de correspondances (`matches`) est également mis à jour.

### 3. Message de Victoire
Une fois toutes les cartes appariées, un message de victoire est affiché dans une boîte de dialogue. Cette boîte permet également de réinitialiser le jeu en appuyant sur le bouton "OK".

## Test et Validation
Le jeu a été testé de manière approfondie pour s'assurer que :
- Les cartes se mélangent correctement à chaque nouvelle partie.
- Le tableau ne peut pas être retourné lorsque deux cartes sont déjà en cours de comparaison.
- Les correspondances sont correctement identifiées et les cartes appariées sont désactivées.
- Le jeu affiche un message de victoire lorsque toutes les cartes sont appariées.

## Temps Passé sur Chaque Partie
- Conception de l'interface : 5 heures
- Développement de la mécanique de jeu : 6 heures
- Intégration des effets multimédia : 3 heures
- Système de scores et niveaux : 4 heures
- Documentation et tests : 3 heures

## Problèmes Rencontrés
- **Problème** : Difficulté à gérer les états des cartes après le retournement.
- **Solution** : Utilisation d'un gestionnaire d'état pour suivre les cartes retournées.

## Qualité du Code
- **Respect des Conventions de Nommage** : Les noms des variables, méthodes, et classes suivent les conventions de `camelCase` et `PascalCase`.
- **Organisation Claire des Packages** : Le projet est organisé en packages logiques.
- **Gestion des Erreurs** : Utilisation de `try-catch` pour gérer les exceptions.
- **Code Propre et Lisible** : Code indenté correctement, fonctions courtes et concises.

## Présentation Finale
L'interface principale est l'écran d'accueil de l'application. Elle offre deux options principales :
1.	Jouer une partie : Un bouton intitulé "Play" pour démarrer une nouvelle partie.
2.	Consulter l'historique : Un bouton intitulé "View History" pour accéder à l'historique des victoires.

<img src="https://github.com/user-attachments/assets/09717f14-cd6a-4625-bc1f-d0ee82511c7b" alt="image" width="200"/>


Lorsque vous cliquez sur "Play", le jeu commence et vous devez associer les cartes. Un message de victoire s'affichera lorsque vous aurez trouvé toutes les correspondances.

On clique sur Play pour que le jeu commence :

<img src="https://github.com/user-attachments/assets/564fe39b-5030-48c7-9979-96a7cb11929f" alt="image" width="200"/>

En haut, "Flips" compte le nombre de cartes retournées par l'utilisateur.
"Matches" suit le nombre de paires trouvées par l'utilisateur dans la partie en cours.
Le bouton "Shuffle" mélange les cartes qui sont face cachée (les cartes appariées ne seront pas mélangées).

<img src="https://github.com/user-attachments/assets/9e11a356-f89a-4046-9110-06fc9237eef8" alt="image" width="200"/>
<img src="https://github.com/user-attachments/assets/b669bd12-c33b-4a26-b6ef-78b861390a88" alt="image" width="200"/>
<img src="https://github.com/user-attachments/assets/521e2210-dfe1-4299-bfb0-fc041c9b9e67" alt="image" width="200"/>

•	- Un dialogue s'affiche lorsqu'un joueur gagne la partie.
- En cliquant sur **"OK"** :
  - Toutes les cartes sont mélangées et retournées face cachée.
  - Les compteurs de flips et de paires sont réinitialisés.
  - Une nouvelle partie commence automatiquement.

<img src="https://github.com/user-attachments/assets/5e349259-a1eb-4155-98be-3b69b1a31ad5" alt="image" width="200"/>

L'interface ci-dessous affiche une liste des victoires précédentes du joueur, avec des informations sur la date et le nombre de tentatives pour chaque victoire.

<img src="https://github.com/user-attachments/assets/9580dd02-964f-4df7-9230-156466cf5312" alt="image" width="200"/>

### Disposition

### En haut de l'écran :
- **Titre** :  
  - Un titre centré nommé **"Victory History"**.

### Section principale :
- **Liste déroulante** :  
  - Une **ListView** affichant les victoires au format spécifié.

### En bas :
- **Bouton** :  
  - Un bouton nommé **"Play"** permettant de commencer une nouvelle partie.


