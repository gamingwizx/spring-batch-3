CREATE TABLE `input_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `job_titles` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `full_or_part_time` varchar(10) NOT NULL,
  `salary_or_hourly` varchar(10) NOT NULL,
  `typical_hours` int DEFAULT NULL,
  `annual_salary` decimal(10,2) DEFAULT NULL,
  `hourly_rate` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
)

CREATE TABLE `output_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `work_hours` int NOT NULL,
  `annual_salary` decimal(10,2) NOT NULL,
  `hourly_rate` decimal(10,2) NOT NULL,
  `monthly_salary` decimal(10,2) GENERATED ALWAYS AS ((`annual_salary` / 12)) STORED,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
)