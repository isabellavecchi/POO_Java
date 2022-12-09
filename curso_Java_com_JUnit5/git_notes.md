Isabella Vecchi Ferreira


git init dentro da pasta

--configurando inicialmente
git config user.name "Isabella Vecchi"
git config user.email "vecchisabella@gmail.com"

--para ver as configurações atuais
cat .git/config

ignorecase = true
precomposeunicode = true

--listar a atual situação dos arquivos desta pasta
git status

--adicionar arquivo(s) ao Stage/index, antes de ser comitado
git add file

--listar um histórico de tudo o que foi commitado
git log

-- commitar colocando msg
git commit -m "label"

--para voltar para um arquivo antigo
git log (para ver qual)
git reset HEAD file_name

git reset HEAD^ --volta um commit
git reset HEAD^^ --volta dois commit...

git reset(--mixed) ... --volta o commit e o arquivo volta para o meu diretório
git reset --soft... --volta para o Stage/index (antes de ser commitado)


git revert commit_id --pode digitar só o começo do id
	--deleta o arquivo anterior, cria um novo igual e coloca o antigo na frente na árvore
	--é interessante dar um git diff id_commit1 id_commit2 antes de reverter
--BRANCHes
git branch --lista todos
git branch branch_name --cria este branch
git checkout branch_name --mudo para este outro branch

--MERGE
git merge branch_name --mergeia as info do branch atual com as do branch branch_name
	--cria um novo commit

--dúvida: e se tiver 2 arquivos de mesmo nome com conteúdos diferentes? Qual ele escolhe para remover?

--PULL
git pull --busca os arquivos do repositório remoto e já faz um merge (inclusive de commits) com a máquina local

--PUSH
git push --envia as informações locais para o repositório remoto
	--se o local estiver muito desatualizaddo em relação ao remoto, ele só deixará dar o push, depois do pull

--RELEASE/TAG
git tag release-1.0 --marca o commit atual com esta tag

--Para dar o primeiro pull
	--congifurar uma chave no github
git clone https://...
nome de usuário
chave gerada

--adicionando repositório updtream no projeto
git remote add upstream https://...
git remote -v --para ver os repositórios

