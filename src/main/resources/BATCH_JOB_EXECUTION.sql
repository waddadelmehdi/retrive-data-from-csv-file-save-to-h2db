create table BATCH_JOB_EXECUTION
(
    JOB_EXECUTION_ID BIGINT
        primary key,
    VERSION          BIGINT,
    JOB_INSTANCE_ID  BIGINT       not null,
    CREATE_TIME      TIMESTAMP(9) not null,
    START_TIME       TIMESTAMP(9) default NULL,
    END_TIME         TIMESTAMP(9) default NULL,
    STATUS           VARCHAR(10),
    EXIT_CODE        VARCHAR(2500),
    EXIT_MESSAGE     VARCHAR(2500),
    LAST_UPDATED     TIMESTAMP(9),
    constraint JOB_INST_EXEC_FK
        foreign key (JOB_INSTANCE_ID) references BATCH_JOB_INSTANCE
);

