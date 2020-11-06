#include "FirebaseESP8266.h" 
#include <ESP8266WiFi.h>

const char* ssid = "Apartemen Borobudur";
const char* password = "guegakngerti";

FirebaseData firebaseData;

//int trigPin = D1;
//int echoPin = D7;
int trigPinMobilMasuk = D6;
int echoPinMobilMasuk = D5;
int trigPinMobilKeluar = D2;
int echoPinMobilKeluar = D3;

int i = 0;
int m = 0;
int k = 0;
int currentState = 0;
int previousState = 0;
int stateMasuk = 0;
int stateKeluar = 0;

void setup() {
 Serial.begin (9600);
// pinMode(trigPin, OUTPUT);
// pinMode(echoPin, INPUT);
 pinMode(trigPinMobilMasuk, OUTPUT);
 pinMode(echoPinMobilMasuk, INPUT);
 pinMode(trigPinMobilKeluar, OUTPUT);
 pinMode(echoPinMobilKeluar, INPUT);

 konekWifi();
 Firebase.begin("https://uas-android-1c142.firebaseio.com/", "5qFYABWgVwP6rnWEUnu3IA75zeV4MuqhfyJBgEXJ");
}

void konekWifi() {
  WiFi.begin(ssid, password);
  //memulai menghubungkan ke wifi router
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print("."); //status saat mengkoneksikan
  }
  Serial.println("Sukses terkoneksi wifi!");
  Serial.println("IP Address:"); //alamat ip lokal
  Serial.println(WiFi.localIP());
}

void loop() {

if (Firebase.getInt(firebaseData, "/wisata/1/mobil_masuk")) {
      m = firebaseData.intData();
      //mobil masuk
 long durasiMasuk, jarakMasuk;
 digitalWrite(trigPinMobilMasuk, LOW); 
 delayMicroseconds(2); 
 digitalWrite(trigPinMobilMasuk, HIGH);
 delayMicroseconds(10); 
 digitalWrite(trigPinMobilMasuk, LOW);
 durasiMasuk = pulseIn(echoPinMobilMasuk, HIGH);
 jarakMasuk = (durasiMasuk/2) / 29.1;
 
 if(jarakMasuk <= 10) {
  stateMasuk = 1;
 } else {
  stateMasuk = 0;
 }
 if(stateMasuk == 1) {
  m = m + 1;
  Serial.print("Counter : ");
  Serial.println(m);
  if(Firebase.setInt(firebaseData, "/wisata/1/mobil_masuk", m)) {
  } else  {
    Serial.print("Error in setInt, ");
    Serial.println(firebaseData.errorReason());
  }
//   delay(500);
 }
//      delay(100);
  }
  if (Firebase.getInt(firebaseData, "/wisata/1/mobil_keluar")) {
      k = firebaseData.intData();
      //mobil keluar
 long durasiKeluar, jarakKeluar;
 digitalWrite(trigPinMobilKeluar, LOW); 
 delayMicroseconds(2); 
 digitalWrite(trigPinMobilKeluar, HIGH);
 delayMicroseconds(10); 
 digitalWrite(trigPinMobilKeluar, LOW);
 durasiKeluar = pulseIn(echoPinMobilKeluar, HIGH);
 jarakKeluar = (durasiKeluar/2) / 29.1;
 
 if(jarakKeluar <= 10) {
  stateKeluar = 1;
 } else {
  stateKeluar = 0;
 }
 if(stateKeluar == 1) {
  k = k + 1;
  Serial.print("Counter : ");
  Serial.println(k);
  if(Firebase.setInt(firebaseData, "/wisata/1/mobil_keluar", k)) {
  } else  {
    Serial.print("Error in setInt, ");
    Serial.println(firebaseData.errorReason());
  }
//   delay(500);
 }
  }




 delay(500);
 
}
