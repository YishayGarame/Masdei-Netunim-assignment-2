SELECT DOCTOR_NAME , SALARY
FROM DOCTORS inner JOIN queue_summary
WHERE DATE='2020-04-20' AND num_of_patients > 4;


