# RASPBERRY PI- ARDUINO ANDROID-CONTROLLED RC-CAR ROVER WITH LIVE VIDEO STREAMING
# ----------------------------- Pi_CAR -----------------------------

[![Screen Shot](images/yotubeT1.png)](https://youtu.be/J8r_bX_RNzU)


## Kullanılan Malzemeler
* Arduino Nano
* Raspberry Pi
* Raspberry Pi Camera Modülü
* Wi-Fi Adaptör 
* L298N Motor Sürücü
* DC MOTOR X  2  YA DA   X     4
* 12V Lipo Batarya
* Jumper Kablo 
* Araç Şasi (Gövdesi)

## Arduino:
### AMAÇ VE GÖREVLER:
*	Arduino motorları kontrol etmek amacı ile kullanılmıştır.
*	Arduino ile raspberry pi usb üzerinden seri haberleşme yapmaktadır.
*	Android telefonumuz üzerinde arduino’ya gidecek olan pwm aralığı hesaplanıp gönderilmektedir.(Gelecek güncellemeler ve kullanıcının pwm değişkenine daha kolay müdahale edilmesi için)
*	Aşağıda Raspberry pi, Raspberry pi Camera, Arduino, L298N Motor Sürücü,Motorlar ve Bataryanın devre bağlantı şeması gösterilmiştir. 

 
### ARDUINO KURULUMU VE PIN BAĞLANTI ŞEMASI

* Arduino 'ya gelen veri doğrudan motorlara gidecek pwm aralığı olarak gelmektedir.PWM değerinin yanında sadece aracın yön tayini için ` + (ileri)` ve ya `- (geri)` değerini almaktadır.
* Yukarıdaki durum göz önüne alınarak çeşitli modifikasyonlar yapılabilir.
* PWM aralığı `0-255` arasındadır.
* Telefon üzerinden SAĞ VE SOL motor pwmlerini ve servo motor açısını String bir şekilde örn:  200:200!888 şeklinde alıyoruz. Aradaki iki nokta üst üste  `:` ve ünlem işareti `!` ' e göre bölerek 3 elemanlı bir dizi oluşturuyoruz.
*  `!` den sonraki değer, cameranın bağlı bulunduğu servo motor'un açı değerleridir. Bu çalışmada servo motor kullanılmamıştır.<br><br>
**Motor Hareket PWM Geliş Tipi ve Aracın Durumları**<br>
örn:
*  0:0      //stop
* 200:200     // ileri git. ( 2 motorda 200pwm ile çalışır )
*  -200:-200   //geri git. (2 motorda 200pwm ile çalışır)
*  200:-200   // sol motor 200 pwm ileri, sag motor 200 geri döner ( araç kendi etrafında soldan sağa doğru döner)
* -200:200   // sol motor 200 pwm geri, sag motor 200 ileri döner ( araç kendi etrafında sağdan sola doğru döner)
* 200:100    // araç sağa dönecek şekilde hareket eder.<br><br>
 



[![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/images/youtbeT2.png)](https://youtu.be/D4ewbO-OGLY)

![Screen shot WiFi Maunt](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/images/wificontrol.png)
<br><br>
* Arduino, Raspberry pi,Raspberry pi camera modülü, L298N motor sürücü, Motorlar, Güç kaynağının bağlantılarını  yukarıdaki resimdeki gibi gerçekleştiriniz.
* Yukarıdaki şekildeki gibi arduino pin bağlantılarını ve raspberry pi bağlantısını  gerçekleştirdikten sonra yapmamız gereken arduino kodlarımızı yüklemek olacaktır.<br>
**Bunu sıra ile şu  şekilde yapabilirsiniz.<br>**

**I.** Arduino kodlarının açıklamaları ve ne işe yaradığı ile ilgili detaylı bilgi kodların içinde mevcuttur.<br>

**II.** `androidToRaspberry.ino` adlı arduino kodumuzu indiriniz ve çift tıklayarak açınız.<br>

**III.** Açılan proje dosyasını arduino' ya yüklemek için sıra ile  sekmelerden `Tools` => `Board`  ve buradan kullandığınız arduino modelinizi seçiniz.<br><br>


![Screen Shot RA1](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/images/ra1.png)
<br><br>
**IV.** Tekrar `Tools` sekmesinden takmış olduğunuz arduino' nuzun hangi port' a takılı olduğunu gösteriniz.  `Tools` => `port`<br>

**V.** Yukarıdaki adımları gerçekleştirdikten sonra şimdi programımızı arduino' muza yükleyebiliriz.Sol üst köşede `Upload` butonuna basarak yükleme işlemini tamamlamış oluyoruz.<br><br>
![Screen Shot](https://github.com/zafersn/WiFi-RC-Controller-With-Camera/blob/master/images/ra2.png)
<br><br>

## RASPBERRY PI:
### AMAÇ VE GÖREVLER:

* Raspberry pi camera modülü kullanarak görüntünün alınması ve raspberry pi üzerinden telefona aktarılması.
* Arduino ve Telefon arasında bağlantının kurulması.

### RASPBERRY PI KURULUMU:
Raspberry pi kurulumu oldukça basittir.Öncelikle,

Win32DiskImager SD karta işletim sistemi yazdırmamız için gerekli olan programdır.
İndirmek için ![Tıklayınız](http://www.gezginler.net/indir/win32-disk-imager.html)

## 1. Raspbian İşletim Sistemi İndirme;
Raspberry pi mize kuracağımız araç kontrol yazılımlarınıda içinde bulunduran işletim sistemini SD karta yazdımamız gerekmektedir.Burada DİKKAT edilmesi gereken nokta aşağıda linkleri verilen işletim sistemleri seçerken elinizde bulunan Wi-Fi adaptörlere göre 
işletim sistemini indirmeniz ve sd karta yazdırmalısınız.İşletim sistemleri ve destekdikleri modemler aşağıda verilmektedir.

### Raspberry Pi 3  İçin indirilebilir işletim sistemi
Raspberry pi 3 için 2 alternatif yöntemimiz vardır;<br>

1. si doğrudan raspberry pi üzerindeki Wi-Fi kullanmak. <br>
2. Harici olarak USB Wi-Fi adaptör kullanmak(Tavsiye edilen yöntemdir.Mesafe ve veri hızı bakımından. Tabi bu kullanacağınız Wi-Fi adaptöre göre değişebilir.)
<br>

### 1.Pi_CAR'I raspberry pi Üzerindeki dahili Wi-Fi modülü kullanarak yönetmek istiyorsanız.Bu linkteki işletim sistemini indirerek SD karta yazdırınız.
### 2.Pi_CAR'I raspberry pi Üzerinden harici olarak taktığınız Wi-Fi modüşü kullarak yönetmek istiyorsanız.Bu linkteki işletim sistemini indiriniz.
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

[![Screen Shot](images/yotubeT1.png)](https://youtu.be/J8r_bX_RNzU)

Sd kartınızın bilgisayara takılı olduğundan emin olduktan sonra Device kısmında görebilirsiniz. Ardından Write butonuna tıklayıp yazma işlemini başlatıyoruz. Yazma işlemi yaklaşık 2-3 dk sürmektedir. Yazma işleminin bitmesini yeni açılan pencerede "Write Succesful." yazısını görene kadar bekleyiniz.


[![Screen Shot](images/yotubeT1.png)](https://youtu.be/J8r_bX_RNzU)
## 3.Bağlantılar ve Çalıştırma

Yazma işlemi tamamlandıktan sonra SD kartınızı bilgisayardan çıkartıp Raspberry pi'nize takabilirsiniz.
Artık yapmanız gereken SADECE Raspberry' nize USB üzerinde bir Arduino takıp  gerekli güç ve motor sürücüleri bağlantılarını yaptıktan sonra araca monte etmenizdir.
<br>
Video'lu anlatım: 
<br>
[![Screen Shot](images/yotubeT1.png)](https://youtu.be/J8r_bX_RNzU)

