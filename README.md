# contact-list

Maven and java8 should be installed in the system.

Steps:-
1. Use below command to build the application.
	mvn clean install -DskipTests
2. Go to target folder and find ContactList.jar
3. Use below command to run the application.
	java -jar ContactList.jar
4. This application will read contact from CSV file specified in application.properties and it will store contact records in H2 database at startup process. This process is configurable. If autoRead is set to false then we will need to upload CSV file from /upload endpoint.
5. Once application is up, Go to Web folder.
6. Double click on index.html