# **Práctica Fundamentos Android**
## **Sinopsis**
- Debe descargar la lista de posibles platos del restaurante de un servicio. Como no es el propósito principal de esta práctica hacer el servicio puedes valerte de Mocky: http://www.mocky.io/ o de tu propio servicio, lo que prefieras
- Una vez descargada la carta, desde la aplicación ha de ser posible acceder a un listado de las mesas de los clientes- Cuando se accede a una mesa debe poder verse lo que han pedido hasta el momento. No es necesario guardar qué ha pedido cada cliente individual, confiaremos en que el camarero se acuerde qué plato iba para cada cliente en una mesa- Desde esa vista de mesa se deben poder añadir platos. Para esto aparecerá otra pantalla donde se pueda elegir el plato de una lista. En esa lista aparece el nombre del plato, una pequeña imagen, unos iconos que indiquen los alérgenos que posee (si los tiene) y su precio- Al pulsar sobre un plato aparece una pantalla con la información del plato (imagen, algún detalle más...), y una caja de texto donde poner las pequeñas variantes que pueda pedir un cliente- Si guardamos dicho plato se añadirá a la lista de los platos que han pedido en una mesa- Debe existir un pequeño menú para calcular la cuenta
- La aplicación no tiene por qué persistir nada, bastante tienes ya con esto. Además oficialmente no se tiene constancia de ningún móvil Android que se cuelgue y haya que reiniciarlo, por lo que puedes respirar tranquilo. 
## ValoraciónEn la aplicación se valorará:
- Buena aplicación de los conceptos vistos en el curso- Diseño material allá donde sea posible y tenga sentido
- Organización y claridad del código- Facilidad de uso para el usuario- Distintas visualizaciones y formas de interactuar para distintos dispositivos como hemos visto en el curso (al menos distingue entre dispositivos tipo teléfono y tipo tableta)- Soporte de varias versiones de Android, cubriendo un mínimo del 90% de los dispositivos actuales

## Notas

- *La url del menu está en [http://www.mocky.io/v2/5a5bcd782c0000c21cee3a3b?mocky-delay=1500ms](http://www.mocky.io/v2/5a5bcd782c0000c21cee3a3b?mocky-delay=1500ms) añado un delay forzado para que se muestre brevemente la ProgressBar* al inicio de la aplicación.
- La aplicación funciona minimo desde la api 16.
- Uso una librería, picasso [https://github.com/square/picasso](https://github.com/square/picasso), para descargar directamente las imágenes de los platos de internet.
- Uso la libreria anko [https://github.com/Kotlin/anko/wiki/Anko-Coroutines](https://github.com/Kotlin/anko/wiki/Anko-Coroutines) para descargar asíncronamente la lista de platos desde Mocky al iniciar la aplicación.
- Uso un interactor para el caso de uso de la descarga de platos del servicio Mocky.
- Distingue entre modo teléfono y tablet.

### Phone screenshots
![https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-download.png](https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-download.png) ![https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-table-list.png](https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-table-list.png) 

![https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-table-plate-list.png](https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-table-plate-list.png) ![https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-cloud-plates-list.png](https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-cloud-plates-list.png)

![https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-plate-detail.png](https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-plate-detail.png) ![https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-table-plate-list-with-plate.png](https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-table-plate-list-with-plate.png)

![https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-table-pay.png](https://raw.githubusercontent.com/gomez-fabio/practicaAndroid/master/pics/phone-table-pay.png)