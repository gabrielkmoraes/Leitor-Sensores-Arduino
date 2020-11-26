# Leitor-Sensores-Arduino 
O programa tem como objetivo realizar a coleta de dados e exibir um gráfico de sensores conectados a um dispositivo com microcontrolador (arduino).
É possível realizar o cadastro dos sensores para uma possível implementação, em que, usuário possa ter o controle da função, precisão e atributos de cada sensor, como também o local em que ele se encontra.

OBS: Este projeto foi resultado de aulas de POO realizado na Universidade São Francisco (USF) para a apresentação de um trabalho no final do semestre. 



### Demonstração
Qualquer alteração que o sensor detecte e exiba na porta Serial, o gráfico irá exibir.
Para demonstrar isso, foi utilizado os dados de um potênciometro variando de 0 a 1024.

![Gif de demonstração gravado pelo celular](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/GIf%20-%20Grafico%20celular.gif)
![Gif de demonstração gravado pelo computador](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/GIf%20-%20Grafico.gif)

### Pré requisitos

Você irá precisar de:

```
Um computador com JAVA instalado
```

```
Uma placa com microcontrolador (a que foi utilizado foi um arduino)
```

```
Sensores para realizar a coleta
```

```
Uma IDE para desenvolvimento com JAVA, caso queira realizar modificações no código
```
### Montando o circuito
Com uma placa que possua um microcontrolador, siga o desenho a seguir para poder realizar uma demonstração do funcionamento do programa:
![Circuito arduino](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/Foto%20-%20Circuito.png)

### Programando no arduino
Com a IDE do arduino aberta, cole o seguinte código e depois realize o upload para a placa.
```
//Configurando as variavéis e pinos da placa
 
const int sensorTemp = A1;  // Define o pino onde será conectado o sensor de temperatura (LM35)
const int pinPot = A0;      //Define o pino onde será conectado o potênciometro
float temperatura = 0;     // Variável que armazenará a temperatura medida
 

void setup() {
Serial.begin(9600); // inicializa a comunicação serial
}
 
//Função que será executada continuamente
void loop() {

int valor_pot; 
temperatura = (analogRead(sensorTemp) * 0.0048828125 * 100); //VARIÁVEL RECEBE A TEMPERATURA MEDIDA
valor_pot = analogRead(potenciometro); //Realiza a leitura digital do potenciometro (Variando de 0 a 1024)

Serial.println(temperatura);            //imprime na saída Serial o valor de temperatura
Serial.println(valor_pot);              //imprime na saída Serial o valor do potênciometro
delay(100);                             //Aguarda 100ms (0.1 segundo) para realizar atualizar e realizar o procedimento novamente
}

```

### Executando o programa

Vá até o seguinte diretório e abra o arquivo:
```

...\LeituraArduino\dist\LeituraArduino.jar

```
Após isso, será mostrada a seguinte janela:


![tela inicial do programa](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/Tela%20inicial%20-%20Programa.png)


No botão esquerdo(*dropdrown button*), selecione a porta COM conectada ao arduino.
Após isso selecione o botão **Conectar**


Agora é só ver o show acontecer !!!

OBS: o programa inclui automaticamente um arquivo de texto no diretório do projeto contendo todos os dados exibidos no gráfico.

![dados gerados pelos sensores](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/print%20-%20dados%20txt.png)

### Cadastrando sensores

Clique no botão **Cadastrar** e após abrir a nova janela, clique em **Incluir**

![botão inclui](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/Cadastrar%20sensor.png)

Você perceberá que o *textBox* ficou habilitado para incluir dados nele. Após preencher, clique em **Salvar** e depois **Gravar Dados**
Caso queira listar todos os sensores já cadastrados, basta clicar em **Listar**.
Para editar, basta selecionar o sensor listado e depois alterar manualmente os dados e depois clique em **Salvar**



### Features do JChart

Foi utilizada neste programa a biblioteca JChart que possibilita o desenvolvedor criar gráficos em tempo real ou estáticos para poder analisar e/ou apresentar as informações coletadas de diferentes fontes.
Neste programa, graças a biblioteca JChart, é possivel alterar algumas propriedades do gráfico para melhorar sua visualização e/ou ajustar melhor ao tipo de informação que está sendo apresentado.

#### Configurações
Clicando com o botão direito sobre o gráfico, é possível acessar as seguintes informações:
> Propriedades (Já vamos ver mais sobre isso)
> Copiar o estado atual do gráfico
> Salvar o estado atual em um arquivo .png
> Imprimir o gráfico 
> Ampliar/reduzir
> E habilitar/desabilitar a escala automática dos eixos.

![Informações do Gráfico](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/OP%20Propriedades%20-%20grafico.png)

#### Propriedades do Gráfico
Nesta opção é possivel realizar diversas como o texto atribudo ao título, sua cor e fonte.

![Propriedades de exibição do texto do gráfico](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/Propriedades%20-%20Grafico.png)

Há também, a possibilidade de alterar o rótulo dos eixos, como seus atributos de tamanho, cor, e fonte.

![Propriedades de exibição do gráfico](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/Propriedades%20-%20Grafico%20EScala.png)


## E como que lê os dados enviados à porta Serial ?

Isso só é possível com a biblioteca [jSerialComm](https://fazecast.github.io/jSerialComm/) que permite que o programa realize a leitura de qualquer dado que é disponibilizado por qualquer porta **serial comm**.
De acordo com os desenvolvedores da biblioteca:

```
jSerialComm é uma biblioteca Java projetada para fornecer uma maneira independente de plataforma para acessar portas seriais padrões sem exigir bibliotecas externas, código nativo, ou qualquer outra ferramenta. Ele é uma alternativa ao RxTx e à (obsoleta) Java Communications API, com maior facilidade de uso, suporte aprimorado para timeouts e a habilidade de abrir multiplas portas simultaneamente

```
## Autores

* [**Gabriel Keglevich Moraes**](https://github.com/gabrielkmoraes)


## Futuras implementações
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-[] Implementar um sistema de edição para o usuário declarar quantos sensores está usando
-[] Arrumar o sistema de gravação de arquivos (não consegue **substituir** o arquivo após o primeiro uso do programa)
-[] Adicionar um botão "Editar INFO" para o usuário poder editar sem a necessidade de acessar o código
Limite máximo de 3 futuras implementação. Motivo: manter a sanidade mental e não enlouquecer achando apenas defeitos :)
##### No README
-[] Adicionar neste README uma possível aplicação para ilustrar melhor o uso deste programa

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
## Agradecimentos

* Felipe Cavalaro (Instrutor da disciplina de POO e coodenador do curso de Engenharia de Computação da Universidade São Francisco - USF)
