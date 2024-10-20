create table BATCH_JOB_EXECUTION_PARAMS
(
    JOB_EXECUTION_ID BIGINT       not null,
    PARAMETER_NAME   VARCHAR(100) not null,
    PARAMETER_TYPE   VARCHAR(100) not null,
    PARAMETER_VALUE  VARCHAR(2500),
    IDENTIFYING      CHAR(1)      not null,
    constraint JOB_EXEC_PARAMS_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION
);

