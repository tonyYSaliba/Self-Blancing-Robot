#include <ESP8266WiFi.h>
#include <ESP8266mDNS.h>
#include <WiFiClient.h>
const char* ssid = "#####";          // Your own wifi SSID (the one you want the esp8266 to connect), mine is called TeteTheRobot
const char* password = "####";        // Your own wifi password (the one you want the esp8266 to connect), mine is called tsaliba007
byte send_byte =B00000000;                  // The stop byte
char c;
// TCP server at port 80 will respond to HTTP requests
   WiFiServer server(80);
  WiFiClient client;
  boolean isNew = true;   
   String data;
////////////////////////////
void readData(){
       int i = client.read();
       c = char(i);
       if(c == '3')send_byte |= B00000001;                // The turn left byte
       if(c=='4' )send_byte |= B00000010;                 // The turn right byte
       if(c=='2')send_byte |= B00001000;                  // The go forward byte
       if(c=='1')send_byte |= B00000100;                  // The go backwards byte
       if(c=='0')send_byte = B00000000;                   // The stop byte
                          
       delay(40);  
  }   
///////////////////////////
void setup(void) {

  pinMode(D5, OUTPUT);
  Serial.begin(9600);
  // Connect to WiFi network
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
  }
  Serial.println(WiFi.localIP());                       // To read the IP address of the ESP

  // Set up mDNS responder:
  // - first argument is the domain name, in this example
  //   the fully-qualified domain name is "esp8266.local"
  // - second argument is the IP address to advertise
  //   we send our IP address on the WiFi network
  if (!MDNS.begin("esp8266")) {
    while (1) {
      delay(1000);
    }
  }
  // Start TCP (HTTP) server
  server.begin();
  // Add service to MDNS-SD
  MDNS.addService("http", "tcp", 80);
  delay(2000);
}

void loop(void) {
  if(client)
  digitalWrite(D5, HIGH);
  else
  digitalWrite(D5, LOW);  
   while(!client){
  isNew = true;
  client = server.available();
  }
  if(isNew){
  isNew = false;
  }
  if (client.connected() &&client.available()) {
    readData();
  }
  if (client.connected()){
    if(send_byte){
      Serial.print((char)send_byte);
    }
  }    
}

  


