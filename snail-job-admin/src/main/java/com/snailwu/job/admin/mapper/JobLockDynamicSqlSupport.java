package com.snailwu.job.admin.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class JobLockDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final JobLock jobLock = new JobLock();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> lockName = jobLock.lockName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class JobLock extends SqlTable {
        public final SqlColumn<String> lockName = column("lock_name", JDBCType.VARCHAR);

        public JobLock() {
            super("job_lock");
        }
    }
}