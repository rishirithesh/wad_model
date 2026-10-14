DatabaseServlet - run steps
1. mysql -u root -p < setup.sql        (creates test.Employees with a sample row)
2. Edit USER/PASS in WEB-INF/classes/DatabaseServlet.java
3. Put mysql-connector-java jar in <tomcat>/lib
4. Compile:  javac -cp <tomcat>/lib/servlet-api.jar -d WEB-INF/classes WEB-INF/classes/DatabaseServlet.java
5. Copy the DatabaseServlet folder into <tomcat>/webapps, run bin/startup.sh (or startup.bat)
6. Open http://localhost:8080/DatabaseServlet
