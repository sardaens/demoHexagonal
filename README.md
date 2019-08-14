# demoHexagonal
Principes de l'architecture hexagonale : 
- isolation du métier
- l'hexagone represente le metier, regle de gestion
- les points d'adherences avec l'UI, API, API externe, BDD sont realisés via des interfaces (Port) et leurs implementations (Adapters)
- 

Interet :
- Maintenance facilité avec une plus grande souplesse sur les tests unitaires, seuls des mocks au niveau des interfaces permettent d'isoler et de tester independamment le metier.


- add junit test
