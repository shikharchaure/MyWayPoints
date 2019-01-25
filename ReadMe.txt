/***********************************************************MYWAYPOINTS************************************************************************/

Description

This project aims at developing a distributed web application called “MyWaypoints” which provides weather details to the user, not only of start and destination but also of the places along the route.

Project Structure-

This project is implemented in Spring Boot framework in Java. It uses tomcat as a server.The project has two parts. 

First, .java files which handles API calls and other back-end operations. They can be found under
/src/main/java
	--->com.sc 		Has Main class which is important to run the project.
	--->com.controller 	Handles all the user requests and calls appropriate methods to display results to run the project.
	--->com.dto 		a simple POJO class to store objects.
	--->com.service		Contains Business Logic. Example-making calls to API.

Second, jsp pages which is displayed to the user.
/src/main/webApp/WEB-INF/jsp

Also there is an xml file called pom.xml, which is used to install all the dependencies(jar) files to the project.

How to run this application-

1. Import project as Maven Project in Eclipse Ide.
2. Go to Run as and enter
	spring-boot:run
   This will run the project on localhost.
3. To specify a port number go to application.properties file inside project
	Add/change
	server.port:#PortNumber
4. This project is hosted on server port 8091. The endpoint for the application is 

	http://localhost:8091/welcome

5. By hitting this the user will be able to see the UI and have to enter start and destination in the two text boxes.  
   The start and destination are given as strings.
6. On clicking the submit button, the user will be shown the route from start to destination and the places along the route by markers.
   On hovering mouse over the marker the user will be able to see the weather details of the particular location. This includes desciption,    	  humidity,minimum and maximum temperature.
   	

API References-

To display the map and routes from start to destination, Google Direction API is used.
https://developers.google.com/maps/documentation/directions/start

To display weather, Open Weather API is used.
https://openweathermap.org/current	
