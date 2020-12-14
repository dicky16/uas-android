int relay = D3;
void setup() {
  // put your setup code here, to run once:
  pinMode(relay, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(relay, HIGH);
  Serial.println("on");
  delay(5000);
  digitalWrite(relay, LOW);
  Serial.println("off");
  delay(5000);
}
