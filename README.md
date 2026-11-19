# SlotBook

Application de réservation de salles pour petite structure : calendrier,
réservation et annulation, détection des conflits, rôles utilisateur et administrateur.

## Stack

- **Backend** : Java 17, Spring Boot 4, Maven
- **Base de données** : PostgreSQL 17, avec Docker

## Lancer le projet en local

Prérequis : JDK 17 et Docker.

Démarrer la base de données, depuis la racine du projet :

```bash
docker compose up -d
```

Démarrer l'API :

```bash
cd backend
./mvnw spring-boot:run
```

Vérifier que l'application fonctionne :

```bash
curl http://localhost:8080/actuator/health
```

La réponse doit contenir `"status":"UP"`.

PostgreSQL est exposé sur le port `5433` de la machine. L'API écoute sur le port `8080`.
