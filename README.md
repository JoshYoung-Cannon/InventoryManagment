# QA January Intake First Project: Josh Young-Cannon

This is a simple Inventory Management System designed to work with a cloud based MySQL Database.

## Getting Started



### Prerequisites

Java 1.8 JDK:
Add the the location of the installed JDK to your System Environment Variables and name it JAVA_HOME
Add %JAVA_HOME%\bin to your System Path

apache-maven-3.6.2
Add the location of the apache-maven folder, (should be in your program files), to your System Envrinment Variables and name it M2_HOME
Add %M2_HOME%\bin to your System Path

Eclipse Java
Install Eclipse on your computer

MySQL Workbench
Install the MySQL Workbench to your computer, you will to also install Visual Studio C++ in order to use the Workbench

### Installing

A step by step guide to get a development environment running:

1. Create a locally hosted database:
	Open the MySQL Workbench and create a new Local Host channel
	Open this channel and create a new SQL Script, then run the queries saved within the Empty Database Setup .txt file

2. Link the IMS to the locally hosted database:
	Import this maven project into your Eclipse workspace
	Open the Config class (found in the com.qa.im.utils package) and adjust the databaseConnection variable to "jdbc:mysql://LocalHost:3306/inventory_db"

3. Interacting with the database:
	Run the project in Eclipse
	Choose the Customers table and Create a new Customer
	Then choose the Items table and Create a new Item
	Now choose the Orders table and Create a new Order
	CONGRATULATIONS! you now have a fully implimented database


## Deployment

To deploy this project to a live project:

1. Ensure the live system tables match the IMS tables:
	Open a channel to the live system and describe each table, do the same to you locally hosted database and ensure the tables fields and data types match

2. Link the IMS to your live database
	Go to the Config class and adjust the databaseConnection to "jdbc:mysql://<channelIP>:<portNumber>/<database name>"

note: you many need to adjust Enums (found in com.qa.im.enums) to match the field names of your database

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning
We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Josh Young-Cannon** - *Initial work* - [JoshYoung-Cannon](https://github.com/JoshYoung-Cannon)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

## Acknowledgments

Chris Perrins
Rhys Thompson
