
# Android App Remote Control Joystick

## About the Android Application:

This application provides a Remote Control Joystick for flying an aircraft in FlightGear Simulator. The simulator is the server and the Android app is the client. By IP address and Port, the app connects to the simulator and sends commands for the aircraft's flying.

After the connection the communications between the simulator and the app is by a protocol of four commands. The commands are change the value of: Rudder, Throttle, Aileron and Elevator.

**The design pattern implemented in the app:** MVVM architecture.

**Developing tools:** The application developed by Android Studio (with minimum Android API 22) using Kotlin language. 

![remoteJoystick](https://user-images.githubusercontent.com/72437425/123521914-500d9900-d6c2-11eb-823e-591f796e078e.png)

## General structure of the folders:
1. java/... /remotejoystick
	- model
		- file "Model" - the model in the architecture MVVM.
	- view_model
		- file "ViewModel" - the view model.
	- view
		- file "MainActivity" – the programming part of the view.
		- file "Joystick" - class that inherits the class 'View' and implements joystick.
		- file "JoystickService" - interface to get the joystick's notifications.
2. res:
	- layout
		- file "activity_main.xml" - the XML of the view.


## Necessary installations:
1. Android Studio.
2. FlightGear Simulator.
3. Android Emulator (can be downloaded and installed within Android Studio).

## Using instructions:
**In FlightGear Simulator:**
1. Copy the line below and paste it into the FlightGear Simulator's settings in 'additional settings' section:
```
--telnet=socket,in,10,127.0.0.1,6400,tcp
```
2. Then click the button `Fly` and the simulator will start running.

**In Android Studio:**

3. Clone the project and open it with Android Studio.
4. Run the Application.
5. There are two fields you must fill in the app:
	- IP Address: when using the emulator input the local network IP.
	- Port: input the port `6400`.
6. Click the button `CONNECT`.

**You can now start flying the aircraft.**

## UML diagram:

![Untitled Workspace (1)](https://user-images.githubusercontent.com/72437425/123521664-babdd500-d6c0-11eb-9b50-38ad1d7f4e6f.png)

## Link to video for displaying the architecture & demo:
https://youtu.be/eIhqrhGCPyc

## Developed by:
* Yaniv Rotics
* Dov Moshe

## Downloads:
- FlightGear Simulator link: https://www.flightgear.org/
- Android Studio link: https://developer.android.com/studio
