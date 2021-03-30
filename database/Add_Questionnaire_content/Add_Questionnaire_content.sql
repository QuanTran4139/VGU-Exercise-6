ALTER TABLE questionnaire 
ADD COLUMN `attendance` int NOT NULL AFTER `ClassID`, 
ADD COLUMN `gender` char NOT NULL AFTER `attendance`, 
ADD COLUMN `1_module_objective` int NOT NULL AFTER `gender`, 
ADD COLUMN `1_learning_material` int NOT NULL AFTER `1_module_objective`, 
ADD COLUMN `1_module_content` int NOT NULL AFTER `1_learning_material`, 
ADD COLUMN `1_lesson_intersting` int NOT NULL AFTER `1_module_content`, 
ADD COLUMN `2_workload_length` int NOT NULL AFTER `1_lesson_intersting`, 
ADD COLUMN `2_workload_intensity` int NOT NULL AFTER `2_workload_length`, 
ADD COLUMN `2_workload_difficulty` int NOT NULL AFTER `2_workload_intensity`, 
ADD COLUMN `3_module_presentation` int NOT NULL AFTER `2_workload_difficulty`, 
ADD COLUMN `3_learning_activity` int NOT NULL AFTER `3_module_presentation`, 
ADD COLUMN `3_learning_activity_outcome` int NOT NULL AFTER `3_learning_activity`, 
ADD COLUMN `3_assessment_methods` int NOT NULL AFTER `3_learning_activity_outcome`, 
ADD COLUMN `3_student_encouragement` int NOT NULL AFTER `3_assessment_methods`, 
ADD COLUMN `3_lecturer_feedback` int NOT NULL AFTER `3_student_encouragement`, 
ADD COLUMN `3_lectuer_language` int NOT NULL AFTER `3_lecturer_feedback`, 
ADD COLUMN `3_lectuer_listen` int NOT NULL AFTER `3_lectuer_language`, 
ADD COLUMN `3_lectuer_encourage` int NOT NULL AFTER `3_lectuer_listen`,
ADD COLUMN `3_lectuer_consultation` int NOT NULL AFTER `3_lectuer_encourage`;