Para rodar o script do banco em sua máquina, basta apenas:
    -instalar o postgresql 13
    -baixar o driver JDBC e incluí-lo no path do projeto

    Rodar os comandos do script database.sql dentro do proprio postgres

*OBS.: Pode ser necessário alterar o campo URL na Classe ConectaBD.java.


Para se CONECTAR ao BANCO DE DADOS, colocar
USUÁRIO e SENHA no arquivo conexao.txt dentro de trabalhofinal\src\resources.
(Substituir as palavras USER e PASSWORD).

O programa emite caixas de diálogo, no entanto o programa de captura de tela não consegue gravá-las.

As classes DAO são as responsáveis por fazer a conexão com o banco, enquanto que as classes sem DAO
fazem a ponte entre o cliente e a classe que acessa o banco. Validando entradas, etc.