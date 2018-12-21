# balancingRobot2018

### Intro
Remote controlled self balancing robot.
### Media
Check the MEDIA folder for more videos.

![alt text](https://github.com/tonyYSaliba/balancingRobot2018/blob/master/MEDIA/WhatsApp%20Image%202018-12-21%20at%202.58.01%20AM.jpeg)

### Details
the remote control is an android application built using Android studio.

There is a server on the ESP8266.

the android application connects to the server via Sockets.

the connection is made using a local network.

The robot uses stepper motors instead of regular DC motors. The main reason is that stepper motors are precise and have no performance loss when the battery voltage drops. One pulse is always an exact amount of motion. Regular DC motors can have mechanical friction and electric resistance differences. This can cause performance differences. As a result the robot will not move in a straight line.

We are using the MPU-6050 GYRO to calculate the angular tilt of the robot.

The ESP8266 receives a string from the android application (via the sockets). It receives "0" or "1", or 2", or "3", or "4".

"0": stop

"1": go backwards

"2": go forward

"3": turn left

"4": turn right

And then the ESP sends byte data to the arduino via TX, and the arduino reads those data via RX.

### Instructions
The android mobile application is in the RemoteControl folder.

The arduino uno code is in the Balancing_robot folder.

The Node Mcu (ESP8266) code is in the ESP_server folder.

### Connections
We are using a 12v battery for the stepper controllers (Motor side), and a 9v battery for the rest of the components.

There is a 9V to 5V voltage transformer connected to the 9V battery as INPUT, and its OUTPUT is connected to the arduino, the gyro, the ESP and the stepper controllers (Logic side).

The GYRO is connected to the arduino by I2C bus.

the ESP is connected to the arduino by Serial (TX, RX).

the stepper controllers are DRV8825, and they are calibrated so that the maximum current becomes 1.4 Ampers.
