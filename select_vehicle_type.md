
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
5V Güç Kaynağı  (LM2576 DC-DC REGULATOR 12V to 5V)|1
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



![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/DEVRE%20semasi%20.PNG)<br><br>
![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/devre%20sematic.PNG)<br><br>




<br>

# Araç Tipi 2: Kabaca Sağ ve sol motor olmak üzere 2 adet yön kontrol motoru vardır. Çalışma şekli tank şeklindedir.<br><br>

![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/IMG_20160201_001625.jpg)<br><br>


![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/IMG_20151122_142027.jpg)
![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/IMG_20151122_141900.jpg)
![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V3Images/images/IMG_20151122_135042.jpg)
