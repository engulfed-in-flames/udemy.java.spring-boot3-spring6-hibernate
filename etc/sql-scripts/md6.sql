-- Create DB and tables

CREATE SCHEMA "hb-01-one-to-one-uni";

SET search_path TO "hb-01-one-to-one-uni"; -- use `hb-01-one-to-one-uni`;

CREATE TABLE instructor_detail (
  id serial PRIMARY KEY,
  youtube_channel varchar(128),
  hobby varchar(45)
);

CREATE TABLE instructor (
  id serial PRIMARY KEY,
  first_name varchar(45),
  last_name varchar(45),
  email varchar(45),
  instructor_detail_id int UNIQUE, -- UNIQUE constarint for one-to-one relationship
  CONSTRAINT instructor_detail_id_fk
  FOREIGN KEY (instructor_detail_id) 
  REFERENCES instructor_detail (id) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
);

-- Create table 'course'
CREATE TABLE course (
  id serial PRIMARY KEY, 
  title varchar(128) DEFAULT NULL UNIQUE, 
  instructor_id int DEFAULT NULL,
  CONSTRAINT instructor_fk FOREIGN KEY (instructor_id) REFERENCES instructor (id)
);

-- Create table 'student'
create table student (
  id serial primary key, 
  first_name varchar(45) not null, 
  last_name varchar(45) not null,
  email varchar(45)
);

-- Create join table course_student
create table course_student (
  course_id int not null, 
  student_id int not null, 
  primary key(course_id, student_id), 
  constraint course_fk foreign key (course_id) references course (id),
  constraint student_fk foreign key (student_id) references student (id)
);