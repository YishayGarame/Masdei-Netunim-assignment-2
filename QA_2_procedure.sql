DROP PROCEDURE IF exists exec_time_enter;
DELIMITER $$
create PROCEDURE exec_time_enter (IN newTime DATETIME, IN IDofPat int, IN IDofDoc int)
BEGIN
UPDATE queue
SET actual_time = newTime 
WHERE appointment_id =
(SELECT appointment_id 
FROM appointments 
WHERE patient_id = IDofPat AND doctor_id = IDofDoc
ORDER BY appointment_time 
LIMIT 1);

END $$


