

SELECT patients.patient_name, current.patient_id, timedif
FROM
(select appointments.appointment_id, appointments.patient_id, 
appointments.appointment_time,  queue.actual_time,
timediff(queue.actual_time, appointments.appointment_time) AS timedif
from appointments
join queue
where appointments.appointment_id = queue.appointment_id)
AS current
join patients on patients.patient_id = current.patient_id
WHERE timedif > 0
ORDER BY  timedif desc
limit 10;



