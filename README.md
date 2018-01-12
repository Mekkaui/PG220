## PG220

#===========Fonctionnalités accomplies===============#
Affichage de la température(en celcius et fahrenheit), humidité, vitesse du vent pour max 5 jours.
Ecriture du tableau dans un fichier avec l'option -o et -a
Gestion des erreurs
Historique des requetes
Utilisation de l'agrégateur




# La classe Affichage sert, avec sa méthode "do_it", de gérer l'affichage.
# Cette méthode a besoin des fonctionnalités demandées qui sont données par la ligne de commande.
# la classe Arguments permet de faire ça.
# Dans la méthode "do_it", on utilise la classe "Wrapper" pour instancier les différents APIs sous le même type.
# Par conséquent l'ajout d'une nouvelle API se fait en une ligne.
# La classe DataQuery permet de récupérer les réponses des requêtes des APIs.
# Chaque API a son architecture, ce qui exige de créer une classe pour chacune, et de faire le calcul séparemment.
# La classe API est la super classe abstraite qui rassemble toutes les APIs.
# La classe PrintWrap permet de mettre en oeuvre les getters de la classe Wrapper.
