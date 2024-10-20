ALTER TABLE BATCH_STEP_EXECUTION ADD CREATE_TIME TIMESTAMP NOT NULL DEFAULT '1970-01-01 00:00:00';
ALTER TABLE BATCH_STEP_EXECUTION ALTER COLUMN START_TIME DROP NOT NULL;

ALTER TABLE BATCH_JOB_EXECUTION_PARAMS DROP COLUMN DATE_VAL;
ALTER TABLE BATCH_JOB_EXECUTION_PARAMS DROP COLUMN LONG_VAL;
ALTER TABLE BATCH_JOB_EXECUTION_PARAMS DROP COLUMN DOUBLE_VAL;

ALTER TABLE BATCH_JOB_EXECUTION_PARAMS MODIFY COLUMN TYPE_CD PARAMETER_TYPE VARCHAR(100);
ALTER TABLE BATCH_JOB_EXECUTION_PARAMS MODIFY COLUMN KEY_NAME PARAMETER_NAME VARCHAR(100);
ALTER TABLE BATCH_JOB_EXECUTION_PARAMS MODIFY COLUMN STRING_VAL PARAMETER_VALUE VARCHAR(2500);
