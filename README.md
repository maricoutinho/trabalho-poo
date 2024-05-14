# trabalho-poo-priv


## To Do:
* Não permitir criacao de usuarios com o mesmo login
* Permitir edicao de niveis pelo professor

## Done:
* Fazer documentação
* Desenhar as telas do sistema
* Permite de professor crie niveis
* Carrega arquivo de usuários
* Carrega arquivo de perguntas
* Faz login
* Valida login
* Cria usuário
* Exibe perguntas ao usuário e faz validação da resposta
* Exibe quantas perguntas ele respondeu e quantas acertou
* Atualiza seu nível caso ele passe
* Tratamento para caso não exista perguntas cadastradas
* Salva histórico do aluno
* Corrigir atualizacao de usuário no arquivo que não está funcionando
* Passar para a próxima fase apenas quando o aluno acertar todas as perguntas do seu nível
* Não mostrar perguntas repetidas ao aluno, caso ele ja tenha acertado (como salvar progresso no arquivo?)
* Transferir algumas responsabilidades ao sistema, como: criar/atualizar usuário, salvar progresso etc
* Trocar o uso de array para list para simplificar o código

## Possíveis Funcionalidades

* Professor ver historico de alunos
* Adicionar perguntas as níveis existentes
* Perfomance por nível


### Arquivos

Padrao do arquivo de usuário:

#### Aluno
```
Tipo: Aluno
Login: login
Senha: senha
Nivel Atual: 2
Nivel: 1
Respondidas corretamente: 0,1,2,3
Concluido: 100%
Nivel: 2
Respondidas corretamente: 0,2
Concluido: 50%
```
#### Professor
```
Tipo: Professor
Login: aline
Senha: 123
```


Padrão do arquivo de perguntas:

```
Texto: A maçã é vermelha.
Pergunta: Qual a tradução correta?
A: The apple is green.
B: The pinapple is green.
C: The apple is red.
D: The pinapple is green.
Resposta: C
Texto: The apple is red.
Pergunta: Qual a tradução correta?
A: A maçã é verde.
B: O abacaxi é verde.
C: O abacaxi é vermelho.
D: A maçã é vermelha.
Resposta: D
```