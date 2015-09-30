SIR - TP02
----------


Auteur
------

Anne-Sophie SAINT-OMER


Sujet
-----

Dans le TP1 nous avons réalisé un programme permettant d'obtenir la matrice Usagers Thèmes ainsi que la liste de des usagers
et la liste des thèmes. On vous soumet un nouveau fichier de log (log-reco.txt) duquel on vous demande d'extraire les éléments suivants :
– Mut2:  Matrice Usagers Thèmes correspondant au nouveau fichier log-reco.txt
– Mtt : Matrice des distances entre thèmes
Pour construire  Mtt on commence par transformer Mut2 en matrice binaire (i.e selon la présence ou absence d'une relation usager thème). 
On appliquera ensuite le calcul de distance* entre thèmes sur la matrice Mut2-binaire pour obtenir Mtt. On convertira ensuite Mtt en matrice binaire
pour extraire les règles d'associations. Pour cela, on considérera que 2 thèmes peuvent être liés s'ils ont une distance inférieure à 0,5.  
Mtt-binaire contiendra 1 dans ce cas, zéro sinon.



Compilation
-----------

javac Ex2.java
java Ex2


Résultats
---------

Mut2

20 20 0 0 0 
20 20 0 20 0 
0 0 20 0 20 
20 0 0 20 0 
0 0 20 0 20 
0 20 0 0 0 
0 0 20 20 20 
20 0 20 0 0 
0 20 0 0 0 

Mut2Binaire 

1 1 0 0 0 
1 1 0 1 0 
0 0 1 0 1 
1 0 0 1 0 
0 0 1 0 1 
0 1 0 0 0 
0 0 1 1 1 
1 0 1 0 0 
0 1 0 0 0 


MTT

0.0 0.6666666 0.85714287 0.6 1.0 
0.6666666 0.0 1.0 0.8333333 1.0 
0.85714287 1.0 0.0 0.8333333 0.25 
0.6 0.8333333 0.8333333 0.0 0.8 
1.0 1.0 0.25 0.8 0.0 

MTT binaire

1 0 0 0 0 
0 1 0 0 0 
0 0 1 0 1 
0 0 0 1 0 
0 0 1 0 1 

MutR

0 0 0 0 0 
0 0 0 0 0 
0 0 0 0 0 
0 0 0 0 0 
0 0 0 0 0 
0 0 0 0 0 
0 0 0 0 0 
0 0 1 0 0 
0 0 0 0 0 


Conclusion : On recommande à Justine le bricolage.


Information supplémentaire
--------------------------

Le nom du fichier est en dur dans un attribut d'instance.
Changer le chemin si besoin.




