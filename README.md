# contact-list

Maven and java8 should be installed in the system.

Steps:-
1. Set correct csv path to application.properties
2. Use below command to build the application.
	mvn clean install -DskipTests
3. Go to target folder and find ContactList.jar
4. Use below command to run the application.
	java -jar ContactList.jar
5. This application will read contact from CSV file specified in application.properties and it will store contact records in H2 database at startup process. This process is configurable. If autoRead is set to false then we will need to upload CSV file from /upload endpoint.
6. Once application is up, Go to Web folder.
7. Double click on index.html
