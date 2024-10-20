create table BATCH_STEP_EXECUTION
(
    STEP_EXECUTION_ID  BIGINT
        primary key,
    VERSION            BIGINT       not null,
    STEP_NAME          VARCHAR(100) not null,
    JOB_EXECUTION_ID   BIGINT       not null,
    CREATE_TIME        TIMESTAMP(9) not null,
    START_TIME         TIMESTAMP(9) default NULL,
    END_TIME           TIMESTAMP(9) default NULL,
    STATUS             VARCHAR(10),
    COMMIT_COUNT       BIGINT,
    READ_COUNT         BIGINT,
    FILTER_COUNT       BIGINT,
    WRITE_COUNT        BIGINT,
    READ_SKIP_COUNT    BIGINT,
    WRITE_SKIP_COUNT   BIGINT,
    PROCESS_SKIP_COUNT BIGINT,
    ROLLBACK_COUNT     BIGINT,
    EXIT_CODE          VARCHAR(2500),
    EXIT_MESSAGE       VARCHAR(2500),
    LAST_UPDATED       TIMESTAMP(9),
    constraint JOB_EXEC_STEP_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION
);

