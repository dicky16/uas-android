#include "FirebaseESP8266.h" 
#include <ESP8266WiFi.h>

const char* ssid = "Apartemen Borobudur";
const char* password = "guegakngerti";

FirebaseData firebaseData;

//int trigPin = D1;
//int echoPin = D7;
int l = D6;
int ld = D5;
int lt = D2;
int lb = D3;

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
 pinMode(l, OUTPUT);
 pinMode(ld, OUTPUT);
 pinMode(lt, OUTPUT);
 pinMode(lb, OUTPUT);

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
   String lampu = "";
   String lampuBelakang = "";
   String lampuDepan = "";
   String lampuTengah = "";
  if(Firebase.getString(firebaseData, "/kelas/33/lampu")) {
    lampu = firebaseData.stringData();
    delay(1);
  }
  if(Firebase.getString(firebaseData, "/kelas/33/lampu_belakang")) {
    lampuBelakang = firebaseData.stringData();
    delay(1);
  }
  if(Firebase.getString(firebaseData, "/kelas/33/lampu_depan")) {
    lampuDepan = firebaseData.stringData();
    delay(1);
  }
  if(Firebase.getString(firebaseData, "/kelas/33/lampu_tengah")) {
    lampuTengah = firebaseData.stringData();
    delay(1);
  }
  if(lampu == "on") {
    digitalWrite(l, 0);
    Serial.println("lampu menyala");
  } else if(lampu == "off") {
    digitalWrite(l, 1);
  }
  if(lampuDepan == "on") {
    digitalWrite(ld, 0);
    Serial.println("lampu depan menyala");
  } else if(lampuDepan == "off") {
    digitalWrite(ld, 1);
  }
  if(lampuBelakang == "on") {
    digitalWrite(lb, 0);
    Serial.println("lampu belakang menyala");
  } else if(lampuBelakang == "off") {
    digitalWrite(lb, 1);
  }
  if(lampuTengah == "on") {
    digitalWrite(lt, 0);
    Serial.println("lampu tengah menyala");
  } else if(lampuTengah == "off") {
    digitalWrite(lt, 1);
  }
}
