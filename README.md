# Cotuba

Gerador de ebooks escritos no formato Markdown (`.md`) para os formatos EPUB e PDF.

Implementado em Java.

Pré-requisitos:

- Java 8+
- Maven 3+

Para gerar um arquivo `.zip` com os JARS e os scripts necessários, execute:

```
mvn install
```

O `.zip` será gerado na pasta `target`.

Descompacte esse `.zip` no diretório do projeto.

Rode os comandos abaixo nesse diretório.

Se desejar, use os arquivos `.md` do diretório `exemplo`.

## PDF

Para gerar um PDF, faça:

```
cotuba.sh -d exemplo -f pdf
```

Ou:

```
java -cp "libs/*" cotuba.cli.Main -d exemplo -f pdf
```

Deverá ser gerado um arquivo chamado `book.pdf`.

## EPUB

Para gerar um EPUB, faça:

```
cotuba.sh -d diretorio/do/livro -f epub
```

Ou:

```
java -cp "libs/*" cotuba.cli.Main -d exemplo -f epub
```

Deverá ser gerado um arquivo chamado `book.epub`.

