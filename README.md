# ubistart-api
Api para o teste da Ubistart
Decisões tomadas:
- As tecnologias utilizadas forma Java 8 e springboot verção 2.2.4 e ao longo do projeto foi utiliado boas praticas de 
desenvolvimento fazendo uso de CleanCode e SOLID, posso citar a injeção de dependencia atraves da anotação @Autowired
que evita dependencia ciclica devido a ijeção ser feita no momento do seu uso, entretanto para sistemas com testes
unitarios recomenta injeção pelo contrutor pois via anotação pode deixar o sistema mais lento.
- No contexto desta aplicação foi utilizado banco de dados h2 em memoria para não ter a necessidade de subir um banco de daodo,
mas poderia ser qualquer ume a atualização/criação se da atraves do spring, 
o que em grandes aplicações não é recomendado, caso fosse o caso deveria se utilizar o flyway, liquebase, migration ou
outra ferramenta de versionamento de banco de dados.
- Foi utilizado framework do lombok para abstrair e ternar mais legivel a camada de entidade.
- A arquitetura adotada foi a MVC (model-view-controller) por ser amplamente difundida e faciliar a manutenção.
- Para senhar não ficar exposta foi utilizado o ByCrypt.
- Foi utilizado interface na camada de serviço para manter os contratos, separando as responsabilidade e evitando acoplamento.
- No contexto desta aplicação o cors esta liberado mas em um ambiente de produção deve ser bloquado apenas para ips expecificos
por questão de segurança.

Dados da aplicação:
Porta da aplicação = 8080
banco de dados = postgres
Usuario administrador login = admin@admin.com
Usuario administrador senha = 1234
Rota http://localhost:8080/api/tarefa/admin esta bloquada apenas para usuario administrado

abrir H2
digitar na no navegador a url = http://localhost:8080/h2-console
JDBC URL = jdbc:h2:mem:testdb
user name = sa
Password = password

Como rodar aplicação?
Para rodar a aplicação é necessasrio antes criar um banco de dados com o nome "ubistart_db" e com usuarioU e senha postgres
caso senha necesario aletar, existe uma sessão no application.proprieties para isso. 

