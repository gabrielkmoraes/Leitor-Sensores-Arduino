# Leitor-Sensores-Arduino 
O programa tem como objetivo de realizar a coleta de dados e exibir um gráfico de sensores conectados a um dispositivo com microcontrolador (arduino).
É possível realizar o cadastro dos sensores para uma possível implementação, em que, usuário possa ter o controle da função, precisão e atributos de cada sensor, como também o local em que ele se encontra.
OBS: Este projeto foi resultado de aulas de POO realizado na Universidade São Francisco (USF) para a apresentação de um trabalho no final do semestre. 



### Demonstração:
Qualquer alteração que o sensor detecte e exiba no SerialPort, o gráfico irá exibir.
Para demonstrar isso, foi utilizado os dados de um potênciometro variando de 0 a 1024.

![Gif de demonstração gravado pelo celular](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/GIf%20-%20Grafico%20celular.gif)
![Gif de demonstração gravado pelo computador](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/GIf%20-%20Grafico.gif)

### Pré requisitos:

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
### Montando o circuito:
Com uma placa que possua um microcontrolador, siga o desenho a seguir para poder realizar uma demonstração do funcionamento do programa:
![Circuito arduino](https://github.com/gabrielkmoraes/Leitor-Sensores-Arduino/blob/master/material/Foto%20-%20Circuito.png)

### Programando no arduino:
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

### Executando o programa:

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



### Features do programa

A biblioteca 
```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

