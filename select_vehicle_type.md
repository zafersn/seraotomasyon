
# Araç Tipi 1:Tek motorlu Servo direksiyonLu RC-ARABA<br><br>



![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/IMG_20170118_191443_488.jpg)<br><br>
![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/IMG_20170109_181102.jpg)<br>
![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/IMG_20161226_171613.jpg)<br>
* Bu araçta ileri geri hareketler için bir adet motor ve bu motoru sürmek için bir motor sürücü kullanıldı.
 * Motor sürücü olarak burada BTS7960 tercih edildi.
* Direksiyon hareketleri için servo motor kullanıldı.<br>

### MALZEME LİSTESİ
Malzeme Adı| Adet
----| ---- 
Raspberry Pi| 1
Raspberry Pi Camera Modülü| 1
Wi-Fi Adaptör| 1 (Pi 3 için isteğe bağlı)
L298N,BTS7960,L293 Motor Sürücü| 1 
DC MOTOR|  1  
SERVO MOTOR| 3
12V Lipo Batarya| 1
Jumper Kablo | ~
Araç Şasi (Gövdesi)| 1
5V Güç Kaynağı  [**(LM2596-ADJ)**](http://www.robotistan.com/mini-ayarlanabilir-3a-voltaj-regulator-karti-lm2596-adj) or [**(2A Mini)**]( http://www.robotistan.com/2a-mini-ayarlanabilir-voltaj-dusurucu-regulator-karti)|1
<br>

## BAĞLANTI ŞEMASI AŞAĞIDAKİ GİBİDİR. <br><br>

### RASPBERRY Pİ PİN BAĞLANTILARI
PİN ADI| GPIO NUMARASI (BCM)
----| ---- 
DİREKSİYON KONTROL SERVO MOTORU| GPIO 12
KAMERA DİKEY EKSEN HAREKET SERVO MOTORU| GPIO 18
KAMERA YATAY EKSEN HAREKET SERVO MOTORU| GPIO 17
MOTOR SURUCU ICIN LEFT PWM | GPIO 13
MOTOR SURUCU ICIN RIGHT PWM| GPIO 19 
MOTOR SURUCU ICIN RIGHT ENABLE PIN| GPIO 23
MOTOR SURUCU ICIN LEFT ENABLE PIN| GPIO 24
5V GUC VCC  | 5V INPUT
5V GUC GND | GND INPUT

* Burada dikkat etmeniz gereken nokta 5V güç kaynağı dışında pinleri yanlış taktığınızda araç ta veya kamera da sadece yanlış hareketler söz konusu olur. Ama 5V güç kaynağını ters veya yanlış pinlere taktığınız taktirde raspberry de geri dönüşü olmayan hasarlar meydana gelebilir. Raspberry de pinlerden güç girişine alternatif olarak raspberry pi 'nizi micro usb girişinide kullanarabilirsiniz.(Power bank vs diğer alternatif.)

* **NOT:** Burada unutmamanız gereken tek nokta raspberry pi 'nizi güç girişlerini doğru yapmanız ve RASPBERRY Pİ'NİZİ ve SERVO MOTOR larınızı aynı kaynaktan doğrudan beslememelisiniz. Yani kısaca LM2676 ile ürettiğiniz 5V 'la hem Raspberry pi hemde Servo motorları çalıştırmayınız.


![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/DEVRE%20semasi%20.PNG)<br><br>
![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/devre%20sematic.PNG)<br><br>


# Proje anlatımında anlaşılmayan bir konu, öneriniz ve ya istekleriniz için lütfen [SORUNUZ!](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/issues)


<br><br>

# Araç Tipi 2: TANK Tipi Araç <br><br>

* Sağ ve sol motor olmak üzere 2 adet yön kontrol motor mantığı vardır. Çalışma şekli tank şeklindedir.
* Aşağıdaki resimlerden de anlaşılabileceği gibi sağa ve sola dönüşlerde sağ ve sol motorların dönüş hızı farkından faydalanılır.
<br>


<p align="center" width="640" height="480" > <b>6 X 6 VEHICLE !!</b>
<a href="https://www.youtube.com/watch?v=06LrVjRDfS8"><img src="https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/giphy.gif" alt="DETAYLI ANLATIM VE KURULUM" align="middle" />
</a></p>


<br>
![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/IMG_20160201_001625.jpg)<br><br>


![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/IMG_20151122_142027.jpg)
![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/IMG_20151122_141900.jpg)
![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/IMG_20151122_135042.jpg)

<br><br>
# Proje anlatımında anlaşılmayan bir konu, öneriniz ve ya istekleriniz için lütfen [SORUNUZ!](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/issues)
