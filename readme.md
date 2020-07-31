# Spring CRUD Code Generator
A Domain Specific Language used for generating spring code.

1. [Introduction](#Introduction)
1. [Example](#Example)
2. [Setup](#Setup)

# Introduction
The project makes use of Antlr4 to parse a Domain Specific Language. 
Antlr4 was used for the flexibility which makes it easily possible to add more features in the future.

The Spring Boot code gets generated from a file containing the Domain Specific Language code and a property file.

## The required properties are:
```properties
package=com.test #Has to be separated by dots
artifact=test #Can be anything
name=test #Can be anything
version=0.0.1-SNAPSHOT #Can be anything
description=Test description #Can be anything

java.version=11 #Can be any
```

## The supported database properties:

### H2
```properties
database=h2
database.username=test
database.password=test
database.ddl=create-drop
```

### PostgreSQL
```properties
database=postgres
database.username=test
database.password=test
database.ddl=create-drop
database.url=jdbc:postgresql://localhost:5432/test
```

## The supported data types for the Domain Specific Language:

```
Text or String
Byte
Short
Int
Long
Double
List
Set
```

# Example
To generate the following example, the following code is used.

### cg.properties:
```properties
package=com.test
artifact=test
name=test
version=0.0.1-SNAPSHOT
description=Test description

java.version=11

database=postgres
database.username=test
database.password=test
database.ddl=create-drop
database.url=jdbc:postgresql://localhost:5432/test
```

### test.cg:
```yaml
model Student {
  key: Long
  firstName: Text
  lastName: Text
  year: Int
  grades: List(Grade)
  mentor: Mentor
  repo: [
    findStudentByKey
  ]
}

model Grade {
  key: Long
  score: Double
  course: Course
}

model Course {
  key: Int
  courseName: String
  students: List(Student)
  repo: [
    findCourseByCourseName
  ]
}

model Mentor {
  key: Long
  firstName: Text
  lastName: Text
  repo: [
    findMentorByKey
  ]
}

model Teacher {
  key: Long
  firstName: Text
  lastName: Text
  courses: List(Course)
  repo: [
    findTeacherByKey
  ]
}
```

## These 2 files generate the following:

![](https://i.imgur.com/6TIjKPH.png)

### pom.xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.2.RELEASE</version>
		<relativePath/>
	</parent>
	<name>test</name>
	<artifactId>test</artifactId>
	<groupId>com.test</groupId>
	<description>Test description</description>
	<version>0.0.1-SNAPSHOT</version>

	<properties>
		<java.version>11</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
    		<groupId>org.springframework.boot</groupId>
    		<artifactId>spring-boot-starter-test</artifactId>
    		<scope>test</scope>
    		<exclusions>
        		<exclusion>
            		<groupId>org.junit.vintage</groupId>
            		<artifactId>junit-vintage-engine</artifactId>
        		</exclusion>
    		</exclusions>
		</dependency>
	</dependencies>
	<build>
    	<plugins>
        	<plugin>
            	<groupId>org.springframework.boot</groupId>
            	<artifactId>spring-boot-maven-plugin</artifactId>
        	</plugin>
    	</plugins>
	</build>
</project>
```

### application.properties:
```properties
spring.datasource.username=test
spring.datasource.url=jdbc:postgresql://localhost:5432/test
spring.jpa.database=POSTGRESQL
spring.datasource.platform=postgres
spring.jpa.hibernate.ddl-auto=create-drop
spring.datasource.password=test
```

### Student.java:
```java
package com.test.model;

import javax.persistence.*;
import java.util.List;
import com.test.model.Grade;
import com.test.model.Mentor;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long key;

	private String firstName;

	private String lastName;

	private int year;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Grade> grades;

	@OneToOne(cascade = CascadeType.ALL)
	private Mentor mentor;

	public long getKey() {
		return key;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getYear() {
		return year;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public Student setKey(long key) {
		this.key = key;
		return this;
	}

	public Student setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Student setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Student setYear(int year) {
		this.year = year;
		return this;
	}

	public Student setGrades(List<Grade> grades) {
		this.grades = grades;
		return this;
	}

	public Student setMentor(Mentor mentor) {
		this.mentor = mentor;
		return this;
	}

}
```

### Grade.java:
```java
package com.test.model;

import javax.persistence.*;
import com.test.model.Course;

@Entity
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long key;

	private double score;

	@OneToOne(cascade = CascadeType.ALL)
	private Course course;

	public long getKey() {
		return key;
	}

	public double getScore() {
		return score;
	}

	public Course getCourse() {
		return course;
	}

	public Grade setKey(long key) {
		this.key = key;
		return this;
	}

	public Grade setScore(double score) {
		this.score = score;
		return this;
	}

	public Grade setCourse(Course course) {
		this.course = course;
		return this;
	}

}
```

### Course.java:
```java
package com.test.model;

import javax.persistence.*;
import com.test.model.Student;
import java.util.List;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int key;

	private String courseName;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Student> students;

	public int getKey() {
		return key;
	}

	public String getCourseName() {
		return courseName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public Course setKey(int key) {
		this.key = key;
		return this;
	}

	public Course setCourseName(String courseName) {
		this.courseName = courseName;
		return this;
	}

	public Course setStudents(List<Student> students) {
		this.students = students;
		return this;
	}

}
```

### Mentor.java:
```java
package com.test.model;

import javax.persistence.*;

@Entity
public class Mentor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long key;

	private String firstName;

	private String lastName;

	public long getKey() {
		return key;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Mentor setKey(long key) {
		this.key = key;
		return this;
	}

	public Mentor setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Mentor setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

}
```

### Teacher.java:
```java
package com.test.model;

import javax.persistence.*;
import com.test.model.Course;
import java.util.List;

@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long key;

	private String firstName;

	private String lastName;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Course> courses;

	public long getKey() {
		return key;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public Teacher setKey(long key) {
		this.key = key;
		return this;
	}

	public Teacher setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Teacher setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Teacher setCourses(List<Course> courses) {
		this.courses = courses;
		return this;
	}

}
```

### StudentRepository.java:
```java
package com.test.repositories;

import com.test.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findStudentByKey(long key);

}
```

### CourseRepository.java:
```java
package com.test.repositories;

import com.test.model.Course;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	Course findCourseByCourseName(String courseName);

}
```

### MentorRepository.java:
```java
package com.test.repositories;

import com.test.model.Course;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	Course findCourseByCourseName(String courseName);

}
```

### TeacherRepository.java:
```java
package com.test.repositories;

import com.test.model.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	Teacher findTeacherByKey(long key);

}
```

# Setup

To compile and run this project, Java 14 is necessary.

The project makes use of Maven and Antlr. 
When opening the project in your IDE of choice, 
you will need to run the following maven command: 

`mvn antlr4:antlr4`

If using IntelliJ this can be done directly in the Maven tool window.
![](https://i.imgur.com/L4smZvf.png)

This will generate the Antlr4 files.

The code that gets parsed by Antlr4 is in `files/test.cg` and the properties are in `files/cg.properties`.
For the supported properties go to [Introduction](#Introduction).

Once run the generated files will be in `files/generated`