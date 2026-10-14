
# Notes sur la couche Repository — AutoLoc

## 1. Choix de l'interface

Les neuf repositories étendent JpaRepository avec Long comme
type de clé primaire.

Ce choix permet d'utiliser les opérations CRUD, de récupérer
les résultats sous forme de List et de disposer des fonctionnalités
de tri, de pagination et de flush.

## 2. Repositories créés

- IAgenceRepository
- IEmployeRepository
- IVehiculeRepository
- IEquipementRepository
- IClientRepository
- IReservationRepository
- IContratRepository
- IPaiementRepository
- IMaintenanceRepository

## 3. Analyse SonarQube for IDE

| Anomalie détectée | Règle ou explication | Correction apportée |
|---|---|---|
| À compléter | À compléter | À compléter |
| À compléter | À compléter | À compléter |
| À compléter | À compléter | À compléter |
