## ServerController commands

### autehnticate command:
```
1) the client will send the authenticate string to the server through a socket.
2) the server will recieve a student object from client with student name and password.
3) given the username and password the server will look for the user in the database.
4) if the student's credentials are valid, server will send a student object back with username,
   student ID, and password
5) if the credentials are invalid the client will receive a 0
6) after receiving the student object, client can use this object to send the credentials of the student and would not need 
   to send a student object to the server. 
```
### getAllCourses command:
```
1) the client will send the getAllCourses string to the server through a socket.
2) the server will look through the database and creates a catalog object which has a list of courses. 
3) the course object has an array of course offerings and the server adds the offerings to the courses 
   while creating the catalog object. 
4) server will send a catalog object to the client. 
5) client can use this object to get the list of the courses and their offerings, therefore a command for 
   showing the course offerings is not needed. 
```
### register command:
```
1) the client will send the register string to the server through a socket.
2) client will send a sectionId and studentId as strings to the server. 
3) client can find the studentId through the student object received from authenticate command and 
   sectionId for the list of courses received from the getAllCourses command. 
4) server will look for the course that the user want to register for in database using the sectionId.
5) server will use the studentId to add the student and the course they have registered for to the database. 
6) server will send a message to the client regarding the status of the registration. 
```
### getRegisteredCourses command: 
```
1) the client will send the getRegisteredCourses string to the server through a socket.
2) client will send the studentId as a string to the server. 
3) client can find the studentId through the student object received from authenticate command.
4) server will use the studentId to find the courses that the student has registered for in the database. 
5) server will create a list of registration object using the information received from the database. 
6) the registration object has sectionID, studentID, courseID, secNUm, courseName. 
7) server will send the registration list to the client. 
```
### removeregistration command: 
```
1) client will send a registrationId and sectionId as strings to the server. 
2) client can find registrationId and sectionId from the list of registrations received from getRegisteredCourses command. 
3) server will use the recieved strings to find the registration in the database and removes it. 
4) server will send a message to client regarding the status of removing the reigstration. 
```
### search command: 
```
1) client will send the courseName as a string to the server.
2) server will find the course in the database using the courseName. 
3) server will create a course object that has the course name and course id. 
4) server will send the course object to the client. 
```



