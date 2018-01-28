# **Práctica Fundamentos Android**
## **Sinopsis**
- Debe descargar la lista de posibles platos del restaurante de un servicio. Como no es el propósito principal de esta práctica hacer el servicio puedes valerte de Mocky: http://www.mocky.io/ o de tu propio servicio, lo que prefieras

- La aplicación no tiene por qué persistir nada, bastante tienes ya con esto. Además oficialmente no se tiene constancia de ningún móvil Android que se cuelgue y haya que reiniciarlo, por lo que puedes respirar tranquilo.
## Valoración

- Organización y claridad del código

## Notas

- *La url del menu está en [http://www.mocky.io/v2/5a5bcd782c0000c21cee3a3b?mocky-delay=1500ms](http://www.mocky.io/v2/5a5bcd782c0000c21cee3a3b?mocky-delay=1500ms) añado un delay forzado para que se muestre brevemente la ProgressBar* al inicio de la aplicación.
- La aplicación funciona minimo desde la api 16.
- Uso una librería, picasso [https://github.com/square/picasso](https://github.com/square/picasso), para descargar directamente las imágenes de los platos de internet.
- Uso la libreria anko [https://github.com/Kotlin/anko/wiki/Anko-Coroutines](https://github.com/Kotlin/anko/wiki/Anko-Coroutines) para descargar asíncronamente la lista de platos desde Mocky al iniciar la aplicación.
- Uso un interactor para el caso de uso de la descarga de platos del servicio Mocky.
- Distingue entre modo teléfono y tablet.