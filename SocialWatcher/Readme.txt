SocialWatcher Web Application
-----------------------------

Softwares used:
---------------
1. Spring MVC with Rest like URL for controllers.
2. Spring Security for user/password authentication.
3. Spring Data JPA for persistence.
4. Twitter BootStrap for the UI.
5. Jquery for Ajax for REST calls.
6. JQuery Datatable plugin.
7. Apache Tiles for web page templating.
8. Java SDK 1.6
9. Apache Tomcat 7.0
10. MySQL 5.x
11. Maven 2.0.x (Model Version 4)
12. SVN for version control
13. twitter4j V3.x
14. restFB V1.6.2

Steps to prepare war:
---------------------
1. Check out project code from http://cvs2.hq.icentris/svn/icentris/sitequest/trunk/SocialWatcher
2. Set JDK library as JDK 1.6 and Server as Apache Tomcat 7.0 in project's java build path. 
   Set compiler version to 1.6 and set java version to 1.6 in Project Facets (Eclipse IDE).	
3. Open socialwatcher.properties file and set below environment properties

	#Tomcat Server specific#
	application.host.name=(Eg:socialwatch-dev.elasticbeanstalk.com)
	application.port.number=(Eg:8080)
	application.root.context=(Eg:SocialWatcher)
	
	#MySql server specific#
	app.jdbc.driverClassName=com.mysql.jdbc.Driver
`	app.jdbc.url=(Eg:jdbc:mysql://sqprod1.cew0nhzmnljh.us-west-2.rds.amazonaws.com/socialwatchdev)
	app.jdbc.username=(Eg:socialwatchdev)
	app.jdbc.password=(Eg:ALEuJBVFbdup)
	
	#Facebook account specific#
	facebook.client.id=<Copy from face book apps>
	facebook.client.secretkey=<Copy from face book apps>
	facebook.client.apptoken=<Copy from face book apps>
	facebook.client.accesstoken=<Copy from face book apps>
	
	#Twitter account specific#
	twitter.oauth.consumer.key=<Copy from twitter>
	twitter.oauth.consumer.secret=<Copy from twitter>
	twitter.oauth.accesstoken.key=<Copy from twitter>
	twitter.oauth.accesstoken.secret=<Copy from twitter>
	
	#Mail Server specific#
	mail.service.enable=true
	mail.from.address=sw.sitequesttech@gmail.com
	mail.host=smtp.gmail.com
	mail.port=25
	mail.account.username=sw.sitequesttech@gmail.com
	mail.account.password=swsitequest
	mail.transport.protocol=smtp
	mail.smtp.auth=true
	mail.smtp.starttls.enable=true
	mail.debug=true
	mail.subject=Social Watcher: Search terms found
	
4. Make war by running pom.xml


Deploy in Server:
-----------------
1. Stop the server if already running.
2. Place the war into tomcat's webapp's folder for hot deployment. 
   OR Deploy the war using tomcat admin console once up the tomcat server if it is in stop mode.
3. Start the server


Access the application:
-----------------------
1. Hit http://<hostname>:<portnumber>/SocialWatcher in browser




