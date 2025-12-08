
# 🏢 CoWorking Manager  
Sistema de Gerenciamento de Espaços Compartilhados

Projeto desenvolvido por estudantes do 2º período de **Análise e Desenvolvimento de Sistemas – IFPB Campus Monteiro**, na disciplina de **Programação Orientada a Objetos**, orientado pelo professor **Alisson Werner**.

---

## 👥 Integrantes

| Nome |
|------|
| Ana Laura Ferreira Cavalcante |
| Guilherme Gomes Rebouças Lima |
| Sthefany Maria de Morais Jerônimo |

---

## 🎯 Objetivo do Projeto

O CoWorking Manager foi desenvolvido com o intuito de aplicar e integrar conceitos fundamentais de **Programação Orientada a Objetos**, como:

✓ Abstração  
✓ Encapsulamento  
✓ Herança  
✓ Polimorfismo  
✓ Interfaces  
✓ Exceções  
✓ Persistência de Dados  

O sistema simula o gerenciamento de um ambiente de coworking, permitindo controle de espaços, reservas, cancelamentos, pagamentos e geração de relatórios dinâmicos.

---

## 🛠 Tecnologias e Ferramentas Utilizadas

| Tecnologia | Uso |
|-----------|-----|
| **Java 21+** | Linguagem principal e requisitos de execução |
| **Maven** | Gerenciamento de dependências e build |
| **JavaFX** | Interface gráfica do sistema (GUI) |
| **ObjectDB + JPA (javax)** | Banco de dados orientado a objetos e persistência |
| **IntelliJ IDEA** | IDE de desenvolvimento |
| **Arquitetura MVC** | Organização do projeto em camadas |

---

## 📌 Estrutura do Projeto

```

📦 coworking-manager
├── 📁 dist                           → executável (.jar) pronto para uso
├── 📁 src
│   └── 📁 main
│       ├── 📁 java
│       │   ├── 📁 dao                → acesso a persistência de dados
│       │   ├── 📁 excecoes           → tratamento de exceções personalizadas
│       │   ├── 📁 model              → entidades, abstrações e herança
│       │   ├── 📁 service            → regras de negócio e validação
│       │   └── 📁 view               → interface e inicialização
│       │       ├── 📁 controller     → classes Controller JavaFX
│       │       └── 📄 MainCoworking.java  → classe que inicia a aplicação
│       │
│       └── 📁 resources
│           ├── 📁 fxml               → telas/layouts JavaFX
│           ├── 📁 lib                → ObjectDB (.jar) e dependências externas
│           └── 📁 META-INF
│               └── 📄 persistence.xml → configuração JPA/ObjectDB
│
├── pom.xml
└── README.md


````

---

## 🧩 Funcionamento do Sistema

### 🔹 Espaços
O sistema cadastra três tipos de ambientes:

| Tipo | Comportamento específico |
|------|--------------------------|
| Sala de Reunião | Pode incluir taxa adicional de projetor |
| Cabine Individual | 10% de desconto para reservas acima de 4h |
| Auditório | Possui taxa extra fixa de evento |

Cada espaço contém:  
`id, nome, capacidade, disponibilidade, preçoPorHora, calcularCustoReserva()` (método abstrato)

---

### 🔹 Reservas
- Datas/hora validadas para evitar sobreposição
- Valor calculado automaticamente com base no espaço e duração

### 🔹 Cancelamentos
- Até 24h antes → sem custo  
- Após 24h → taxa de 20%  
- Reserva permanece registrada para compor relatórios e histórico

### ⚠ Por que não excluímos registros do banco?

Para evitar lacunas nos relatórios.  
Em um ambiente real seria incoerente perder o histórico de lucro/prejuízo causado por um espaço que foi excluído permanentemente.  
Por isso, ao "remover" um espaço, ele apenas deixa de ser exibido, mas permanece salvo com campo booleano `existente = false`.  
Se necessário, existe na aplicação o método para exclusão permanente — mas não é utilizado por padrão.

---

## 📊 Relatórios

O sistema exibe em tela relatórios como:

- Reservas em um período
- Faturamento por tipo de espaço
- Ranking de utilização
- Espaços mais rentáveis e mais utilizados

---

## ▶ Como Executar o Projeto

### 📌 Requisitos
- **Java 21 ou superior**
- Maven (se for rodar via projeto)
- O ObjectDB será carregado automaticamente na persistência

---

### 🔹 Rodando pelo Maven

```bash
mvn clean install
mvn javafx:run
````

---

### 🔹 Executando o .jar (forma mais fácil)

> 📍 O arquivo `.jar` estará disponível em `/dist`

Clique duplo pode não abrir em alguns sistemas — por isso recomendamos rodar pelo terminal:

```bash
PS > cd <pasta-onde-o-jar-foi-salvo>
PS > java -jar coworkingv4.jar
```

Exemplo real:

```bash
PS C:\Users\Sthefany\Downloads> java -jar coworkingv4.jar
```

Caso não funcione ao clicar, **use o Terminal ou PowerShell**.
Este aviso deve ser considerado antes de relatar erro.

---

## 📦 Distribuição

| Arquivo                 | Descrição                                     |
| ----------------------- | --------------------------------------------- |
| `/dist/coworkingv4.jar` | Execução direta do programa                   |
| `/src/`                 | Código-fonte completo implementando MVC e POO |

---

## 📜 Licença e Uso

Projeto desenvolvido para fins acadêmicos.
Pode ser utilizado para estudo e referência.

---