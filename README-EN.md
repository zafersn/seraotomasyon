# RASPBERRY PI- ARDUINO ANDROID-CONTROLLED RC-CAR ROVER WITH LIVE VIDEO STREAMING
# ----------------------------- Pi_CAR -----------------------------
[![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/images/yotubeT1.png)](https://youtu.be/J8r_bX_RNzU)

## Materials
<br>

Product Name| Piece
----| ---- 
Arduino Nano| 1
Raspberry Pi| 1
Raspberry Pi Camera Module| 1
Wi-Fi Dongle| 1
L298N,BTS7960,L293 Motor Drive| 1 or 2 
DC MOTOR|  2  or   4
12V Lipo Battery| 1
Jumper Cables | ~
Vehicle Chassis | 1
<br>


## Arduino:
### PURPOSE AND TASKS:

*	Arduino was used for motor control.
* Arduino and Raspberry Pi were connected by serial communication.
* Before PWM signal interval which will be sent from Android phone to Arduino don't transfer, this signal was calculated. (Because of the next updates and easily interfere to PWM variable by users.)
* The Raspberry Pi provides the Wi-Fi communication with Arduino and the user (Android phone).
* In the following section, we show the circuit schematic for Raspberry Pi, Raspberry Pi Camera, Arduino, L298N Motor Driver, motors and batteries.
 

### SETTING UP ARDUINO AND PIN CONNECTION SCHEMATIC

* Data which come to Arduino is sent as a PWM interval that go to the motors directly. There are two values `+ (forward)` or `- (backward)` for decide direction with PWM value.
* Above-mentioned situations is considered by us, there can be made various modifications.
* PWM interval is between` 0-255.`
* RIGHT AND LEFT motor PWMs' and servo motor angle is taken as a String(e.g. `200:200!888`) from phone. This String value is splitted with `:` and `!` characters and created the array that has 3 elements.
* The value after the `!` character is the servo motor's angle value which connected with camera. Servo motor is not used in this project.
 **Motor Moving, PWM data and situations of the car**<br>
Example:<br>
 0:0 //stop<br>
 200:200 // move forward. (2 motors work with 200pwm)<br>
 200:-200 //move backward. (2 motors work with 200pwm)<br>
 200:-200 // left motor turns 200 pwm to forward, right motor turns 200 pwm to backward (The car turns its around from left to right.)<br>
 200:-200 // left motor turns 200 pwm to backward, right motor turns 200 pwm to forward (The car turns its around from right to left.)<br>
 200:100 // The car moves as turning to the right.<br><br>
 



[![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/images/youtbeT2.png)](https://youtu.be/D4ewbO-OGLY)

![Screen shot WiFi Maunt](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/images/wificontrol.png)
<br><br>
* Connections among Arduino, Raspberry pi,Raspberry pi camera module, L298N motor driver, Motors and Power Supply are set up as above picture.
* After connected Arduino pins and Raspberry pi as above picture, we can load our codes to Arduino. That do by this sequence:
* Detail information about Arduino codes is placed in that codes.
* Download `androidToRaspberry.ino` file and open this file with double click.
* For uploading that project file to Arduino, first you must select the Arduino model from `Tools => Board` menu.<br><br>


![Screen Shot RA1](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/images/ra1.png)
<br><br>
* Again from `Tools` menu, you must show which port plugged to Arduino board. `Tools => port`
* After realize all steps, now you can upload the codes to Arduino board. You can complete the uploading process by pressing `Upload` button from left-up corner of the program.<br><br><br>
![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/images/ra2.png)
<br><br>
## RASPBERRY PI:
### PURPOSE AND MISSIONS:

* Taking video stream with Raspberry Pi camera module and trasfer that stream to phone via Raspberry pi.
* Connection between Arduino and Android phone.

### RASPBERRY PI SETUP:
Raspberry pi setup is quite simple. First, <nr>
Win32DiskImager is the program that is required to print the operating system to the SD card.  [**Click**](http://www.gezginler.net/indir/win32-disk-imager.html) to download

## 1. Raspbian Operating System Downloads;
Raspberry pi mize kuracağımız araç kontrol yazılımlarınıda içinde bulunduran işletim sistemini SD karta yazdımamız gerekmektedir.Burada DİKKAT edilmesi gereken nokta aşağıda linkleri verilen işletim sistemleri seçerken elinizde bulunan Wi-Fi adaptörlere göre 
işletim sistemini indirmeniz ve sd karta yazdırmalısınız.İşletim sistemleri ve destekdikleri modemler aşağıda verilmektedir.

### Downloadable operating system for Raspberry Pi 3
Raspberry pi 3 için 2 alternatif yöntemimiz vardır;<br>

1. si doğrudan raspberry pi üzerindeki Wi-Fi kullanmak. <br>
2. Harici olarak USB Wi-Fi adaptör kullanmak(Tavsiye edilen yöntemdir.Mesafe ve veri hızı bakımından. Tabi bu kullanacağınız Wi-Fi adaptöre göre değişebilir.)
<br>

### 1.If you want to manage Pi_CAR via Raspberry pi 2 or  the built-in Wi-Fi module on the Raspberry Pi 3.Bu [**linteki**](https://drive.google.com/file/d/0B6yjwSAqPTgfX1dsaVVJZThEN2c/view?usp=sharing) işletim sistemini indirerek SD karta yazdırınız.



### 2.Pi_CAR'I Raspberry pi 3 'ün  Üzerinden harici olarak taktığınız Wi-Fi modüşü kullarak yönetmek istiyorsanız.Bu [**linteki**](https://drive.google.com/file/d/0B6yjwSAqPTgfYkhxWEN2dXBIQlU/view?usp=sharing) işletim sistemini indiriniz.



Bu seçenek için elimizde bulunan ve test ettiğimiz Wi-Fi cihazları ve çipsetleri
<br>

Test Edilen cihazlar|CHIPSET
---------- | --------
Dark WDN300A5 | RTL8192CU
Tenda UH150 | RT2870/RT3070
AWUS036NH | RT2870/RT3070
<br>

Desteklenen diğer cipsetler ve cihazlar için detalı bilgi:http://elinux.org/RPi_USB_Wi-Fi_Adapters

## 2. İşletim Sistemini Micro-SD Kart’a Yazdırma
İndirdiğimiz imaj dosyasını zip içerisinden çıkarıyoruz. Ardından daha önce indirdiğimiz win32diskImager programını açıyoruz. İmaj dosyamızı belirtilen yerden seçiyoruz.

![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V2.0/images/win32diskimager.jpg)

Sd kartınızın bilgisayara takılı olduğundan emin olduktan sonra Device kısmında görebilirsiniz. Ardından Write butonuna tıklayıp yazma işlemini başlatıyoruz. Yazma işlemi yaklaşık 2-3 dk sürmektedir. Yazma işleminin bitmesini yeni açılan pencerede "Write Succesful." yazısını görene kadar bekleyiniz.


![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V2.0/images/Write%20Succesful.png)
## 3.Bağlantılar ve Çalıştırma

Yazma işlemi tamamlandıktan sonra SD kartınızı bilgisayardan çıkartıp Raspberry pi'nize takabilirsiniz.
Artık yapmanız gereken SADECE Raspberry' nize USB üzerinde bir Arduino takıp  gerekli güç ve motor sürücüleri bağlantılarını yaptıktan sonra araca monte etmenizdir.
<br>
Video'lu anlatım: 
<br>
[![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/V2.0/images/youtube%20play2.png)](https://youtu.be/yNLug0DhQlc)



