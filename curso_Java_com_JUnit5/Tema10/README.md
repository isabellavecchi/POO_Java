Para rodar o script do banco em sua máquina, basta apenas:
    -instalar o postgresql
    -baixar o driver JDBC e incluí-lo no path do projeto

    Depois, utilizando o usuario postgres, no terminal digitar:
    $psql -f $TEMA10\src\main\resources\dump.sql

No dump já vem incluso a criação do Database, não sendo necessário criá-lo antes para rodar o script, como no padrão do PostgreSQL.

*OBS.: Também será necessário alterar o campo URL na Classe ConectaBD.java.


Para se CONECTAR ao BANCO DE DADOS, colocar
USUÁRIO e SENHA no arquivo conexao.txt também dentro de $TEMA10\src\main\resources.
(Substituir as palavras USER e PASSWORD).