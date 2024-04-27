# demoHexagonal
Principes de l'architecture hexagonale :
- isolation du métier
- l'hexagone represente le metier, regle de gestion
- les points d'adherences avec l'UI, API, API externe, BDD sont realisés via des interfaces (Port) et leurs implementations (Adapters)
-

Interet :
- Maintenance facilité avec une plus grande souplesse sur les tests unitaires, seuls des mocks au niveau des interfaces permettent d'isoler et de tester independamment le metier.


- add junit test


- Test avec springboottest
---------------------------
Interet :
- met à disposition des outils pour faciliter les tests d'application spring springboot


How to :
--------

Ajout des dependances :
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

/!\ scope test , utiliser uniquement pour les tests ne fait pas partie du livrable pour la prod.

L'ajout de cette dependance permet d'avoir :
- JUnit and Hamcrest
- Mockito
- Spring Tools


-------------------
- Tests Unitaires -
-------------------

- Mockito (Mocker des dependances externes au metier)
Si une classe service a une dependance vers une classe repository pour recuperer des données d'une BDD.
On peut mocker la dependance avec spring :
  1- @InjectMocks sur la classe service
  2- @Mock sur la classe repository

il sera alors possible de mocker le comportement de la classe repository.
exemple :
public class MockitoControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserById() {
        User u = new User();
        u.setId(1l);
        when(userRepository.findOne(1l)).thenReturn(u);

        User user = userController.get(1L);

        verify(userRepository).findOne(1l);

        assertEquals(1l, user.getId().longValue());
    }
}

On peut utiliser la methode verify  de mockito pour controler l'execution et les appels d'un stub.

- On peut egalement faire des tests unitaires sur la partie controller :
avec mockmvc
cf. EmployeeControllerUnitTest.java



----------------------
- Integrations Tests -
----------------------

l'app utilise une base mysql pour le stockage :
 docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:latest

1/ Pour les tests on utilise une base h2. (on surcharge la conf pour ne plus utiliser la base mysql)
 -> la configuration de la base pour les tests se change dans src/test/resources/application.properties (ici on configure une base h2)

2/ On utilise mockmvc pour simuler les appels http
cf EmployeeServiceIntegrationTest.java

3/ Il est possible de populer la base de test avec l'annotation @Sql
cf employees_schema.sql et import_employees.sql

# CICD

## Jenkins :
- Run jenkins in docker

docker run \
  --rm \
  -u root \
  -p 8080:8080 \
  -v jenkins-data:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v "$HOME":/home \
  jenkinsci/blueocean

le script jenkinsfile permet de builder l'application, executer les tests, builder l'image docker et pousser l'image sur la registry : **sardaens/demohexagonale:latest**

new value