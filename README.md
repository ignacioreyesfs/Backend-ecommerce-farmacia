# Farmacia
Backend de aplicacion web para ventas de una farmacia desarrollado en java, utilizando Hibernate ORM y Quartz JobScheduler.

## Funcionalidades
* **Poner un producto en oferta:** producto con descuento, inicio y finalización de la promoción de forma asincrónica (scheduling jobs).
* **Generar una orden:** conjunto de productos a ser enviados al domicilio del cliente.
* **Notificación ante renovación de stock:** ante la falta de stock de un producto, el cliente puede pedir que se le notifique cuando ingresen unidades del mismo (implementado Gmail Library pero brinda la flexibilidad de agregar otros notificadores).
* **Recomendación de productos:** recomendaciones de que productos comprar en base a la temperatura del día, utilizando OpenWeather API.

## Class diagram
![Farmacia class diagram](https://i.imgur.com/ADbs7Hx.jpg)
## Entity relationship diagram
![Farmacia DER](https://i.imgur.com/TfvuUvc.jpg)
