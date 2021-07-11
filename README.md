# Yebra

Une application mobile pour la gestion des consultations médicales
Le projet consiste à développer une application mobile permettant aux patients de prendre rendez-vous auprès des médecins et de consulter les traitements médicaux des différentes consultations.

1.1 Fonctionnalités
1. Authentification
L’application doit permettre aux utilisateurs de se connecter et se déconnecter. L’authentification doit se faire avec un numéro de téléphone et un mot de passe.

2. Affichage des médecins.
L’interface principale de l’application doit afficher la liste des médecins. Chaque élément de la liste doit contenir les informations suivantes: photo du médecin, nom, prénom, numéro de téléphone, spécialité du médecin et une icône pour afficher sur l’application Google Maps l’itinéraire vers le cabinet

du médecin sur l’application Maps. Quand l’utilisateur clique sur le numéro de téléphone, l’interface d’appel doit s’afficher.

3. Prise de RDV
Un patient peut prendre un rendez-vous en ligne en mentionnant la date et l’heure du rendez-vous. La prise de RDV doit respecter l’agenda du médecin. Après la prise du RDV, un code QR du RDV est généré.

4. Consultation
Le jour de la consultation, le médecin scanne via son application mobile scan le code QR pour afficher les informations du RDV. Après chaque visite, le médecin saisit via son application web le traitement prescrit au patient. Un traitement a une durée et un ensemble de prise de médicaments par jour.

5. Affichage des traitements
L’application doit permettre aux patients d’afficher les traitements en cours.

6. Demande de conseil
Le patient peut demander un conseil auprès de son médecin, en écrivant un texte libre. Si la connexion internet n’est pas disponible, le descriptif de la demande est stocké en local et une synchronisation doit être planifiée.

1.2 Fonctionnalité optionnelle
● Notifications push. Après la validation de la prise de RDV par le patient, une notification est envoyée au médecin concerné. Quand le médecin clique sur la notification, la liste des RDV en attente est affichée. ● Consultation des traitements en mode offline. Après la saisie du traitement par le médecin, une synchronisation doit être lancée pour stocker le traitement en local et permettre au patient de le consulter en mode offline.

1.4 Contraintes
● L’authentification d’un utilisateur doit se faire une seule fois. ● L’application du patient doit fonctionner juste sur un smartphone en mode portrait. ● La création des comptes ne se fait pas via l’application mobile (créez les patients et les médecins directement dans la base de données).

