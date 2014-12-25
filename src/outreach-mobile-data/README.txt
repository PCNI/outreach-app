Command to generate changelog in liquibase

liquibase --driver=com.mysql.jdbc.Driver --classpath=mysql-connector-java-5.1.18.jar --changeLogFile=db.changelog-0.1.xml --url="jdbc:mysql://localhost:3306/arbschema" --username=arbuser --password=arbuser generateChangeLog