# **Programme de facturation**

## **Description :**

Ce programme gére 2 types de clients :

A) Les clients Pro, qui ont les propriétés suivantes :
- Reference Client (EKW + 8 caractères numériques)
- N° SIRET
- Raison Sociale
- CA

B) Les particuliers, qui ont les propriétés suivantes :
- Reference Client (EKW + 8 caractères numériques)
- Civilité
- Nom
- Prénom

Un client peut consommer deux types d'énergies :
- Electricité
- Gaz

Chaque énergie est facturée au kWh.
- Pour les particuliers, le prix du kWh est de 0,121 € pour l'électricité et 0,115€ pour le gaz
- Pour les pro, ayant un CA supérieur à 1 000 000 €, le prix du kWh est de 0,114 € pour l'électricité et 0,111€ pour le gaz
- Pour les pro, ayant un CA inférieur à 1 000 000 €, le prix du kWh est de 0,118 € pour l'électricité et 0,113€ pour le gaz


## Usage
Depuis la racine du projet, lancer la commande:

`mvn spring-boot:run`

L'application se lancera sur le port 8080, et l'endpoint post REST sera disponible a: `http://localhost:8080/billing`

Exemple de body json a envoyer:

```
{
    "reference": "EKW89632547",
    "clientType": "PRO",
    "consumptions": [
    {
        "energyType": "ELEC",
        "amount": 10000
    },
    {
        "energyType": "GAZ",
        "amount": 1000
    }
],
    "revenue": 500
}
```


## Améliorations envisagées
- Ajout de contrôles sur les valeurs
- Améliorer la gestion des erreurs par le controleur (Ajout d'un advice)
- Connection a une BDD pour réduire les informations nécessaires dans la requête
- Vérification de l'existence du client
- Ajout d'un swagger
- Ajout d'une pipeline
- Ajout de logs
- Ajout d'une pipeline
