
-- 创建用户表
CREATE TABLE tb_user(
  user_id INT  PRIMARY KEY AUTO_INCREMENT NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  user_info INT NOT NULL,
  create_time DATETIME DEFAULT NOW(),
  last_edit_time DATETIME DEFAULT NOW()
) ENGINE=InnoDB CHARSET =utf8mb4;

-- 创建用户信息表
CREATE TABLE tb_user_info(
  user_info_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  full_name VARCHAR(255) NOT NULL,
  department INT NOT NULL,
  user_profile VARCHAR(2048) NOT NULL,
  leader INT,
  id_number VARCHAR(255) NOT NULL,
  graduate_school VARCHAR(255),
  position VARCHAR(255) NOT NULL,
  current_salary DECIMAL
) ENGINE=InnoDB CHARSET =utf8mb4;

-- 创建部门表
CREATE TABLE tb_department(
  department_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  department_name VARCHAR(255) NOT NULL,
  department_desc VARCHAR(1024)
) ENGINE=InnoDB CHARSET =utf8mb4;

# 创建公告表
CREATE TABLE tb_notice(
  notice_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  notice_title VARCHAR(512) NOT NULL,
  notice_content TEXT NOT NULL,
  notice_sender INT NOT NULL,
  only_staff BOOL NOT NULL,
  create_time DATETIME DEFAULT NOW(),
  last_edit_time DATETIME DEFAULT NOW()
) ENGINE=InnoDB CHARSET =utf8mb4;

# 创建事件表
CREATE TABLE tb_event(
  event_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  event_name VARCHAR(255) NOT NULL,
  event_desc VARCHAR(1024) ,
  event_start_time DATETIME NOT NULL ,
  event_end_time DATETIME NOT NULL ,
  event_user INT NOT NULL ,
  event_create_time DATETIME DEFAULT NOW()

)ENGINE=InnoDB CHARSET =utf8mb4;

创建考勤表
CREATE TABLE tb_checking(
  checking_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  checking_date DATETIME DEFAULT NOW(),
  checking_type BOOL  NOT NULL ,
  checking_user INT NOT NULL ,
  checking_create_time DATETIME DEFAULT NOW()

)ENGINE=InnoDB CHARSET =utf8mb4;