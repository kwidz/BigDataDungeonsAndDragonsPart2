# BigDataDungeonsAndDragonsPart2

## Parser.py
est le fichier permetant de parser le pathfinder afin de récupérer toutes les créatures ainsi que tous les sorts
Il va créer un gros fichier json contenant tous les sort de chaque créature.

## Data.json 
est le fichier contenant toutes les creatures

## DataSample.json est un exemple du fichier data.json avec un formatage pour le rendre lisible par un humain

Tous les exercices sont dans le dossier scala qui est un projet intelij idea
Les objets scala Exercice1, Exercice2Combat1 et Exercice2Combat2 correspondent au exercice donnés dans le devoir

L'exercice 1 prends le fichier json du parser, et ensuite le transforme en RDD, Ensuite on effectue les transformations nécéssaires
et on crée un second RDD qui trie les créatures par sorts disponibles. Nous avons implémenté deux façons pour faire ceci. La methode classique avec des maps reduce, et la methode en utilisant les outils recommandés par spark pour faire ce genre d'opérations.
