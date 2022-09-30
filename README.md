Spring MyBatis Example
============

## Program with 
* Spring MVC
* MyBatis-Spring
* Log4j 2

## Database Design (Oracle)

	CONNECT scott/tiger
	
	create table music (
		no number,
		content varchar2(4000),
		constraint PK_MTV PRIMARY KEY(no)
	);

	create sequence SEQ_MUSIC
		increment by 1
		start with 1;

## How to run
sudo nano /etc/tomcat9/Catalina/localhost/mybatismusic.xml

    <?xml version="1.0" encoding="UTF-8"?>
    <Context
        docBase="/home/john/mybatismusic/src/main/webapp"
        reloadable="true">
    </Context>

### Compile
**mvn compile war:inplace**

Start Tomcat and visit http://localhost:8080
(If dependencies change, run $ **mvn clean** first)
