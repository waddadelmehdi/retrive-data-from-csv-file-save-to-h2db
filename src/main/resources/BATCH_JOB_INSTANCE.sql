create table BATCH_JOB_INSTANCE
(
    JOB_INSTANCE_ID BIGINT
        primary key,
    VERSION         BIGINT,
    JOB_NAME        VARCHAR(100) not null,
    JOB_KEY         VARCHAR(32)  not null,
    constraint JOB_INST_UN
        unique (JOB_NAME, JOB_KEY)
);

