# ENSF409 Term Project 
## Contributers 
```
Anita Rezaee      Email: anita.rezaeeanzabee@ucalgary.ca
Nolan Chan        Email: nolan.chan@ucalgary.ca
Benjamin Nielsen  Email: benjamin.nielsen@ucalgary.ca
```
## Running The Application 
### Step 1: *Running the Application and Logging In as Student* 
```
1) Download all SQL Text Files
2) Create a database with a schema named "ensf409", set the username to "root" and password to "ensf409"
2) In order to run the server and client on one computer navigate to ClientApp.java file, line 18 and change the IP address to 
   "localhost" 
3) Run the Server.java and ClientApp.java
4) Once the GUI is displayed, to run a student registration application enter "Jim" for username and "123" for password
5) Click on return button to login 
```
### Step 2: *Viewing All Courses and Searching for a Course* 
```
1) To look at the course catalog click on "View All Courses"
2) To search for a course click on "Search Courses" 
3) Enter the name and number of the course. (Ex. Course name: ENSF  Course number: 409)
4) After entering the name and the number of the course click on "Search" to see the course with its sections
5) Click on "Return to Main Window" after searching for a course
```
### Step 3: *Registering for a Course and Viewing All the Registered Courses* 
```
1) Click on "Register for a Course"
2) Enter the name and number of the course. (Ex. Course name: ENSF  Course number: 409)
3) Click on "Get Offerings" to get the sections offered for the course
4) To register for a course click on *Register*. (note: once a student tries to register for a 7th course they will get an error since the max number of registration is six)
5) Click on "Return to Main Window" once done registering for a course
6) To view all the registered courses click on "View All Registered Courses" 
```
### Step 4: *Removing a Course from Registration* 
```
1) Click on "Remove Course Registration"
2) Select the course from the drop down menue and click on "Remove registration for course"
3) a message will be displayed on the main window about the status of removing the course 
4) click on "Return to Mane Window" once done removing registrations 
```
### Step 5: *Logout* 
```
1) Click on "Logout" 
2) To log back in follow part 4 of step 1
```
### Step 6: *Login as Admin* 
```
1) To run the Admin application enter "Anita" for username and "helloworld" for password
2) To search and view all courses follow step 2
3) To add a course to the course catalog click on "Add a Course"
4) Fill out the required spots on GUI and click on "Add Course"
5) If the course already exists in the course catalog, an error will be displayed 
6) Click on "Logout" to go back to the login window
```
## Bonus Features 

### Deploy The Project 
As shown in the demo the server was ran on a seperate machine with a different IP address than localhose. 

### JUnit Test 
JUnit test has been written for CourseOffering and User classes. The JUnit test files are in the src folder named *CourseofferingTest.java* and *UserTest.java*. 

### Login/Logout and List Of Users 
In order to see a list of users take a look at user table in the database. In this table you can see a *login* column, **'1'** indicates that the user is logged in and **'0'** is displayed when the user is logged out. 
In addtion to the log in status, the role of the user can also be seen in the table. 

### Admin GUI 
A seperate GUI has been created with a functionality of adding new courses to the course catalog. 
