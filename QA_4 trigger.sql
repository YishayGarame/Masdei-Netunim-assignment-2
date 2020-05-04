

DELIMITER //
CREATE TRIGGER deleteQueue_summary
AFTER DELETE ON queue
FOR EACH ROW
BEGIN
	UPDATE queue_summary
    SET num_of_patients = num_of_patients - 1
    WHERE doctor_id = (
    SELECT doctor_id 
    from appointments 
    WHERE old.appointment_id = appointments.appointment_id
    AND 
    queue_summary.date = DATE(old.actual_time)
    );
END //

CREATE TRIGGER updateQueue_summary
AFTER INSERT ON queue
FOR EACH ROW
BEGIN
	UPDATE queue_summary
    SET num_of_patients = num_of_patients + 1
    WHERE doctor_id = (
    SELECT doctor_id 
    FROM appointments 
    WHERE new.appointment_id = appointments.appointment_id)
    AND 
    queue_summary.date = DATE(new.actual_time);
END //
DELIMITER ;


#CAST(orders.date_purchased AS DATE)

