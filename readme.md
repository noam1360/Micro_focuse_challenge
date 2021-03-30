This project is for micro focus homework challenge
 
Directory Micro_focus_project and Micro_focues_client are the implimented Phone Book Application.
  Micro_focus_project is the server and contains the implimented testing.
  Micro_focus_client is the client to the server and contains the graphic user interface
  
Architecture document contains the architectural plan of the project and the test plan of the application.

Directory Secure_Code contains the given c code with its vunrabilities.

**How To Run The Application**

Requirements:
    • Docker
    • JDK 15
    • Spring Boot Framework
Setup:
    1. Download & Install requirements
    2. Clone this repository
    3. Start your docker application: <sudo systemctl start docker>
    4. Run this command: <docker volume create –name=postgres_data>
    5. Run this command inside your directory with cloned repository: <sudo docker-compose up>
Running:
	1. go to micro_focus_project and run:
		< ./gradlew test >
		< ./gradlew bootRun >
	2. go to micro_focus_client and run:
		< ./gradlew bootRun >
