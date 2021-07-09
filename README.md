# La Reconquista Del Paladar #

## Índice ##

1. [Fase 1](#id1)
2. [Fase 2](#id2)
3. [Fase 3](#id3)
4. [Fase 4](#id4)


## Fase 1 <a name="id1"><a> ##
### Descripción general ###
Se desarrollará una aplicación web destinada a la venta de comida latinoamericana a domicilio, en la que los usuarios podrán navegar por las diferentes categorías de las comidas ofertadas sin necesidad de registrarse. Para formalizar el pedido deberán iniciar sesión con su usuario y completar su compra.

### Entidades principales ####
- Producto: Cada producto llevará asociado un identificador único, y contendrá datos específicos que lo describan junto al número de stock. 
- Cliente: Cada ususario tendrá su propio nombre de usuario, que será unico para cada uno, a parte de otros datos como su dirección y otros datos personales. El usuario podrá realizar de 0 a N pedidos.
- Pedido: esta entidad tendrá asociada un numero de productos y un usuario, asociandolos. Esta entidad tambien contendrá el coste final total.
- Categoría de productos: esta entidad llevará asosciado un identificador único y contendrá de 1 a N productos que se recojan en ella.

### Descripción servicio interno ###
\- Cuando el usuario hace un pedido se le enviará un correo electrónico con un pdf donde se incluirá el resumen de su pedido.

\- Servicio de pago online.


### Autores ###

**Roberto Adrián Toaza Castro**
- ra.toaza.2016@alumnos.urjc.es
- GII + GIC 129
- ##### Github: [RoberToaza](https://github.com/RoberToaza)


## Fase 2 <a name="id2"></a> ##

### Desarrollo en local ###
#### Diagrama de Navegación ####
![Diagrama de Navegación](./picturesReadMe/Diagrama_de_navecacion.png)

#### Diagrama UML ####
![Diagrama UML](./picturesReadMe/Diagrama_UML.png)

#### Diagrama E/R ####
![iagrama E/R](./picturesReadMe/Diagrama_E_R.png)

#### Home Principal ####
![Home](./picturesReadMe/Home.png)

#### Home (Privado) ####
![Home](./picturesReadMe/home_P.png)

#### Menú (Público) ####
![Home](./picturesReadMe/menu1.png)
![Home](./picturesReadMe/menu2.png)

#### Menú (Privado)####
![Home](./picturesReadMe/menu_P.png)

A diferencia del menú anterior. este dispone de un botón para poder añadirlo al carrito del usuario.

#### Carrito (Privado) ####
![Carrito](./picturesReadMe/cart_P.png)

#### Contacto (Publico/Privado) ####
![Contacto](./picturesReadMe/contacto.png)
Lo único que diferencia a estos dos es que en uno puedes hacer login en la barra nav y en el otro aparece tu nombre de usuario

#### Info (Privado) ####
![Info](./picturesReadMe/info_P.png)

#### Registrarse como usuario (Publico) ####
![SignUp](./picturesReadMe/signUp.png)


## Fase 3 <a name="id3"></a> ##

Nueva Navegación haciendo una verdadera diferencia entre el usuario podrá acceder de forma pública (sin login) y después de hacer login

### Diagrama de nueva navegación ###
![NuevaNavegacion](./picturesReadMe/p4/nuevaNavegacion.jpeg)


#### Home Principal (Público) ####
![Home](./picturesReadMe/p4/Home.png)

#### Log In (Público) ####
![LogIn](./picturesReadMe/p4/login.png)

Esta pantalla aparecerá siemrpe que se quiera acceder a alguna parte privada, y después de hacer log in, se redireccionará a donde quería acceder en una primera instancia.

#### Sign Up (Público) ####
![SignUp](./picturesReadMe/p4/signup.png)

Esta solo se mostrará desde las pantallas públicas ya que luego su acceso desaparecerá.

#### Contacto (Público) ####
![Contacto](./picturesReadMe/p4/contacto.png)

#### Menú (Privado) ####
![Menu](./picturesReadMe/p4/menu_P.png)

#### Carrito (Privado) ####
![Cart](./picturesReadMe/p4/carrito_P.png)

#### Tickets (Privado) ####
![Tickets](./picturesReadMe/p4/tickets_P.png)

#### Perfil (Privado) ####
![Info](./picturesReadMe/p4/info_P.png)

### Diagrama de Clases ###
![p1](./picturesReadMe/p4/Parte1.jpeg)
![p2](./picturesReadMe/p4/sinCache2.jpeg)


##### Servicio Interno #####

![SI](./picturesReadMe/p4/serviciointerno.jpeg)



### Despliegue ####

Para el despliegue de la aplicación, se necesitará tener instalado docker en su máquina así como java.

Primero arrancaremos la base de datos con la siguiente línea



Para comprobar su versión de java 

```
$ docker run --rm -e MYSQL_ROOT_PASSWORD=pass -e  MYSQL_DATABASE=lareconquista -p 3306:3306 -d mysql
```

Una vez hecho este paso nos colocaremos en la carpeta target tanto del servicio interno como de la página web y abriremos un terminal ahí ejecutaremos la siguiente linea

```
$ java -jar "nombredel jar"
```

Si no ejecuta comprobaremos la versión de java:

```
$ java --version
```

Si no funciona hay que instalar mínimo la versión 8 de java:

```
$ sudo add-apt-repository ppa:openjdk-r/ppa
$ sudo apt-get update
$ sudo apt-get install openjdk-8-jdk
```

## Fase 4 <a name="id4"></a> ##

### Vídeo despliegue ###

[![LaReconquista](./picturesReadMe/p4/info_P.png)](https://youtu.be/iFM8ulg1cVI)

### Diagrama de Clases ###

El cambio mas significativo ha sido que ahora tenemos un controlador para la caché

![p1](./picturesReadMe/p4/Parte1.jpeg)
![p2](./picturesReadMe/p4/Parte2.jpeg)



##### Servicio Interno #####

![SI](./picturesReadMe/p4/serviciointerno.jpeg)

### Despligue ###

La aplicación seguirá los siguientes esquémas

![HAProxy](./picturesReadMe/p4/haproxy.jpeg)

y la siguiente comunicación:

![DIagramita](./picturesReadMe/p4/Comunicacion.jpeg)


Para desplegar el servicio tenemos que descargarnos el zip descomprimirlo e ir a la carpeta docker donde abriremos un terminal e introduciremos la siguiente línea

```
$ sudo docker-compose up
```

Para mostrar las imagenes tenemos el mandato

```
$ sudo docker images
```

Para los ids:

```
$ sudo docker ps
```

Y para detener un proceso usaremos:

```
$ sudo docker stop [id]
```

