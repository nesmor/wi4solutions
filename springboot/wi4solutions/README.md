# WI4SOLUTIONS

Project to finnish calls throw goip devices.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing


Mariadb Install
-------------------
```
apt install  default-mysql-server/kali-rolling
apt install default-mysql-client/kali-rolling
mysql_secure_installation
```

Clone project source code

```
#apt install git
#mkdir /var/www/projects
#mkdir -p /var/www/projects
#cd /var/www/projects
```
Clone repository with your custom git access: (After that you will have project source code in server to install it)

```
#git clone https://github.com/nesmor/wi4solutions.git
Username for 'https://github.com/nesmor/wi4solutions.git':[github Username]
Password for [github [github Username]] 'https://github.com/nesmor/wi4solutions.git':[github password]
```

Asterisk Install
-------------------
```
#apt update && sudo apt upgrade
#apt install wget build-essential subversion
#cd /usr/src/
#wget http://downloads.asterisk.org/pub/telephony/asterisk/asterisk-16-current.tar.gz
#tar -zxf asterisk-16-current.tar.gz
#mv asterisk-16.1.0/ asterisk
#cd asterisk/
#contrib/scripts/get_mp3_source.sh
#contrib/scripts/install_prereq install
#./configure
#make
#make menuselect
#make install
#make samples
#cp /var/www/projects/wi4solutions/springboot/wi4solutions/asterisk/*conf /etc/asterisk
#cp /var/www/projects/wi4solutions/springboot/wi4solutions/asterisk/odbc.ini /etc/.
#cp /var/www/projects/wi4solutions/springboot/wi4solutions/asterisk/odbcinst.ini /etc/.
#cp /var/www/projects/wi4solutions/springboot/wi4solutions/libmyodbc* /usr/lib/x86_64-linux-gnu/odbc/.
#apt install unixodbc uniodbc-dev unixodbc-bin
```
Change config files base on custom server ip and database settings if it is necesary.
Validate odbc connection

```
echo "select 1" | isql -v asterisk-connector
```

Out will be something like:
```
+---------------------------------------+
| Connected!                            |
|                                       |
| sql-statement                         |
| help [tablename]                      |
| quit                                  |
|                                       |
+---------------------------------------+
SQL> select 1
+---------------------+
| 1                   |
+---------------------+
| 1                   |
+---------------------+
SQLRowCount returns 1
1 rows fetched
```
Install g729 and  g2723 asterisk code:

```
#cd /var/www/projects/wi4solutions/springboot/wi4solutions/asterisk/codec
#chmod +x codec_g729-ast160-gcc4-glibc-x86_64-pentium4.so
#chmod +x codec_g723-ast160-gcc4-glibc-x86_64-pentium4.so
#cp codec_g723-ast160-gcc4-glibc-x86_64-pentium4.so /usr/lib/asterisk/modules/.
#cp codec_g729-ast160-gcc4-glibc-x86_64-pentium4.so /usr/lib/asterisk/modules/.
#asterisk -rx  "module load codec_g723-ast160-gcc4-glibc-x86_64-pentium4.so"
#asterisk -rx  "module load codec_g729-ast160-gcc4-glibc-x86_64-pentium4.so"
#asterisk -rx  "core restart now"


```

Install java and maven
-----------------------------
```
#apt install openjdk-8-jdk/kali-rolling
```
Select number associated to openjdk 1.8 version. By default server has 11 installed.
```
#update-alternatives  --config java
#apt install maven
```

Create user and password for database.
---------------------------------------
Log into mariadb

```
#mysql -u root -h localhost -p
#password XXXX
mariadb> create database wi4solutions;
mariadb> GRANT ALL PRIVILEGES ON .* to 'wi4solutions'@'localhost' IDENTIFIED BY 'w14s0l_!';
mariadb> FLUSH PRIVILEGES;
```

Import database wi4solutions

```
mariadb>source /var/www/projects/wi4solutions/springboot/wi4solutions/database/wi4solutions.sql;
```
Compile and package project with maven and install as service

```
#cd /var/www/projects/wi4solutions/springboot/wi4solutions
#mvn  -Dmaven.test.skip=true -Dspring.profiles.active=prod,webpack,no-liquibase package
# cd /var/www/projects/wi4solutions/springboot/wi4solutions/command
#cp  wi4solutions.service  /etc/systemd/system/wi4solutions.service
#systemctl enable wi4solutions.service


```

If want to see any log status, read file syslog to check any success or error message
```
#tail -200f /var/log/syslog

```
Can see application open port on syslog output:

```
Jan 13 13:45:07 vpn-client systemd[1]: Started Wi4solutions System.
Jan 13 13:45:12 vpn-client java[19569]:         ██╗ ██╗   ██╗ ████████╗ ███████╗   ██████╗ ████████╗ ████████╗ ███████╗
Jan 13 13:45:12 vpn-client java[19569]:         ██║ ██║   ██║ ╚══██╔══╝ ██╔═══██╗ ██╔════╝ ╚══██╔══╝ ██╔═════╝ ██╔═══██╗
Jan 13 13:45:12 vpn-client java[19569]:         ██║ ████████║    ██║    ███████╔╝ ╚█████╗     ██║    ██████╗   ███████╔╝
Jan 13 13:45:12 vpn-client java[19569]:   ██╗   ██║ ██╔═══██║    ██║    ██╔════╝   ╚═══██╗    ██║    ██╔═══╝   ██╔══██║
Jan 13 13:45:12 vpn-client java[19569]:   ╚██████╔╝ ██║   ██║ ████████╗ ██║       ██████╔╝    ██║    ████████╗ ██║  ╚██╗
Jan 13 13:45:12 vpn-client java[19569]:    ╚═════╝  ╚═╝   ╚═╝ ╚═══════╝ ╚═╝       ╚═════╝     ╚═╝    ╚═══════╝ ╚═╝   ╚═╝
Jan 13 13:45:12 vpn-client java[19569]: :: JHipster 🤓  :: Running Spring Boot 2.0.6.RELEASE ::
Jan 13 13:45:12 vpn-client java[19569]: :: https://www.jhipster.tech ::
Jan 13 13:45:12 vpn-client java[19569]: 2019-01-13 13:45:12.439  INFO 19569 --- [           main] com.wi4solutions.Wi4SolutionsApp         : Starting Wi4SolutionsApp on vpn-client with PID 19569 (/var/www/projects/wi4solutions/springboot/wi4solutions/target/wi-4-solutions-0.0.1-SNAPSHOT.war started by root in /)
Jan 13 13:45:12 vpn-client java[19569]: 2019-01-13 13:45:12.453  INFO 19569 --- [           main] com.wi4solutions.Wi4SolutionsApp         : The following profiles are active: prod,webpack,no-liquibase
Jan 13 13:45:29 vpn-client java[19569]: 2019-01-13 13:45:29.359  INFO 19569 --- [           main] com.wi4solutions.config.WebConfigurer    : Web application configuration, using profiles: prod
Jan 13 13:45:29 vpn-client java[19569]: 2019-01-13 13:45:29.377  INFO 19569 --- [           main] com.wi4solutions.config.WebConfigurer    : Web application fully configured
Jan 13 13:45:38 vpn-client java[19569]: 2019-01-13 13:45:38.020  INFO 19569 --- [           main] com.wi4solutions.Wi4SolutionsApp         : Started Wi4SolutionsApp in 28.415 seconds (JVM running for 30.155)
Jan 13 13:45:38 vpn-client java[19569]: 2019-01-13 13:45:38.035  INFO 19569 --- [           main] com.wi4solutions.Wi4SolutionsApp         :
Jan 13 13:45:38 vpn-client java[19569]: ----------------------------------------------------------
Jan 13 13:45:38 vpn-client java[19569]: #011Application 'wi4solutions' is running! Access URLs:
Jan 13 13:45:38 vpn-client java[19569]: #011Local: #011#011http://localhost:8080/
Jan 13 13:45:38 vpn-client java[19569]: #011External: #011http://127.0.1.1:8080/
Jan 13 13:45:38 vpn-client java[19569]: #011Profile(s): #011[prod, webpack, no-liquibase]
Jan 13 13:45:38 vpn-client java[19569]: ----------------------------------------------------------

```

Our application will be available on

http://[Server Public Ip]:8080/

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
