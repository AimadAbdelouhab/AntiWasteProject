# AntiWasteProject

Chaque année en France, près de 10 millions de tonnes de nourriture consommable sont gaspillées, que ce soit par les consommateurs mais aussi principalement par les commerçants qui sont contraints à jeter les invendus du jour.

Ce projet à pour but d'offrir aux commerçants la possibilité de mettre en vitrine les produits invendus du jour au lieu de les jeter, et aux consommateurs de les acheter à un prix réduit. Un échange où les deux parties sont gagnantes.

La réalisation du projet se distingue en deux parties. Partie web, représentant le backend, est une API réalisé avec le Framework SpringBoot permettant de gérer le backend de l’application et les transactions de données.la partie mobile, considéré comme le frontend, consomme le service web et effectue l’interfaçage avec l’utilisateur. Elle donne la possibilité au consommateur de visualiser les commerçants et les produits qu’ils proposent et par la suite, de les réserver.


![api](https://user-images.githubusercontent.com/80917620/125946579-c0a97fb0-9b84-43d9-870c-91da8165c8ed.PNG)


## Le service web:
Le service web a été réalisé sur Eclipse JEE version 2020-12 avec le JDK 11 avec le Framework SpringBoot. il est consititué de la couche Entity, Dao, Service et Controller. Toutes communiquant entre elles afin de réaliser les transaction avec l'application mobile et la base de donnée sous sql.

![service web](https://user-images.githubusercontent.com/80917620/125951027-35e1ba54-8879-4ee5-84f2-27782a942f7d.PNG)

Le service web traite des reqûetes HTTP (dans la couche Controller) et renvois des données JSON à l'aaplication Mobile et des reqûetes SQL à la base de donnée (dans la couche DAO).

Lien du tutoriel sur lequel s'est basé la réalisation du service Web
https://www.youtube.com/watch?v=IucFDX3RO9U

## L'application mobile:
l'application mobile à été réalisé avec le langage Java sur Android Studio. les propriété du projet sont:
  - compileSdkVersion 30
  - minSdkVersion 21
  - targetSdkVersion 30

L'envoie des requetes HTTP et la réceptions des données JSON est réalisé avec la librarie Volley (https://developer.android.com/training/volley) avec des objets JSonObjectRequest qui prennent en paramètre l'URL sous forme de String (L'application mobile et le service web doivent être connecté au même réseau wifi pour qu'ils puissent communiquer).

L'affichage des listes de paniers et des commerçants utilise des RecyclerView qui recycle un layout prédéfini. On Récupère la liste des Panier/Commerçants dans des HashMap classées selon leurs catégories. Le RecyclerView prend en paramètre la liste et affiche chaque élément de cette dernière en récupérant la liste d'une catégorie spécifique.

Afin d'afficher les images, des éléments, chaque objet contient l'URL d'une image qui lui correspond. On utilise la classe ImageDownloaderTask dans l'adapter du recyclerView qui affiche pour chaque élément une image à l'aide d'un Url qui lui a été affecté.

Le menu de navigation est réalisé avec la librairy Meow Bottom Navigation (https://github.com/oneHamidreza/MeowBottomNavigation) et les onglets d'affichage des paniers et des commerçants a été réalisé avec un TabLayout qui prend des ViewPager (https://leonardovinsen.medium.com/android-tablayout-with-gradient-indicator-bd49c3a0f4f6).

L'integration d'une carte maps a été réalisé en suivant la vidéo suivante (https://www.youtube.com/watch?v=ifoVBdtXsv0&t=1022s)

