
# Android App Remote Control Joystick


## About this Android Application:

This application provides Remote Control Joystick for flying aircraft in FlightGear Sinmulator. The simulator is the server and the Android app is the client. By IP address and Port, the app connecting to the simulator and sending commands how to fly.

After the connecting the comunicatuion between them is by protocol of four commands. Commands to change the value of: Rudder, Throttle, Aileron and Elevator.

### The design pattern implemented in the app:

The app was designed with MVVM architecture.

### Developing tools:

The application developed in Android Studio using Kotlin language.

![remoteJoystick](https://user-images.githubusercontent.com/72437425/123521914-500d9900-d6c2-11eb-823e-591f796e078e.png)

## General structure of the folders:
1. java/... /remotejoystick
	- model
		- file "Model" - the model in the architecture MVVM.
	- view_model
		- file "ViewModel" - the view model.
	- view
		- file "MainActivity" – the programing side of the view.
		- file "Joystick" - class that inherits from class 'View' and implements joystick.
		- file "JoystickService" - interface to get the joystick's notifications.
2. res:
	- layout
		- file "activity_main.xml" - the XML of the view.


## Necessary installations:
1. Android Studio with minimum Android API 22.
2. FlightGear Simulator.
3. Android emulator (can be download inside Android Studio).

## Using instructions in Android Studio:
**In FlightGear Simulator:**
1. Clone the project and open it with Android Studio.
2. Copy the line below and paste it into the FlightGear Simulator's settings in 'additional settings' section:
```
--telnet=socket,in,10,127.0.0.1,6400,tcp
```
3. Then click on the button `Fly` and the simulator will run.

**In Android Studio:**

4. Run the Aplication.
5. There are two fields to fill in the app:
	- IP Address: when using in emulator then input the local network IP.
	- Port: input the port that matched to the port that written in Simulator's settings.
6. Click on the button `CONNECT`.

**Now you can start flying the aircraft.**


## UML:

![Untitled Workspace (1)](https://user-images.githubusercontent.com/72437425/123521664-babdd500-d6c0-11eb-9b50-38ad1d7f4e6f.png)


## Link to video for displaying the Architecture & demo:


## Developed by:
* Yaniv Rotics
* Dov Moshe

## Downloads:
- FlightGear Simulator link: https://www.flightgear.org/
- Android Studio link: https://developer.android.com/studio
