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

Nous creons ensuite un graphe comme sur le schéma en pièce jointe (GrapheCombat1.jpg) afin de relier nos monstres au solar et de les faire combattre. 
Pour faire les combats nous utilisons des agregates messages qui permettent de parcourir tous les noeuds afin d'attaquer. Ensuite on recréer un graphe avec seulement les noeuds qui ne sont pas morts ( HP à 0 ) durant l'agregate message et re-parcours le graphe jusqu'a ce que plus personne ne soit en vie ou que plus personne ne puisse attaquer. 

Pour l'exercice 2, nous avons créer un graphe beaucoup plus complexe à l'aide de plusieurs boucles afin de placer tous les barbares orcs. Le principe est de relier tous les méchants au gentils et inversement. Ainsi les noeuds ont connaissances de la distances qu'ils ont avec tous les noeuds qu'ils ont le droit d'attaquer. Les noeuds n'ont pas besoins d'être relier au noeuds qu'ils n'attaquent pas. 

