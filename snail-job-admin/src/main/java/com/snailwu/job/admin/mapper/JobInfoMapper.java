package com.snailwu.job.admin.mapper;

import com.snailwu.job.admin.core.model.JobInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.snailwu.job.admin.mapper.JobInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface JobInfoMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, groupId, cron, desc, addTime, updateTime, author, alarmEmail, executorRouteStrategy, executorHandler, executorParam, executorBlockStrategy, executorTimeout, executorFailRetryCount, triggerStatus, triggerLastTime, triggerNextTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<JobInfo> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<JobInfo> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("JobInfoResult")
    Optional<JobInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="JobInfoResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "group_id", property = "groupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "cron", property = "cron", jdbcType = JdbcType.VARCHAR),
            @Result(column = "desc", property = "desc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "add_time", property = "addTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "alarm_email", property = "alarmEmail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "executor_route_strategy", property = "executorRouteStrategy", jdbcType = JdbcType.VARCHAR),
            @Result(column = "executor_handler", property = "executorHandler", jdbcType = JdbcType.VARCHAR),
            @Result(column = "executor_param", property = "executorParam", jdbcType = JdbcType.VARCHAR),
            @Result(column = "executor_block_strategy", property = "executorBlockStrategy", jdbcType = JdbcType.VARCHAR),
        @Result(column="executor_timeout", property="executorTimeout", jdbcType=JdbcType.INTEGER),
        @Result(column="executor_fail_retry_count", property="executorFailRetryCount", jdbcType=JdbcType.INTEGER),
        @Result(column="trigger_status", property="triggerStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="trigger_last_time", property="triggerLastTime", jdbcType=JdbcType.BIGINT),
        @Result(column="trigger_next_time", property="triggerNextTime", jdbcType=JdbcType.BIGINT)
    })
    List<JobInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, jobInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, jobInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(JobInfo record) {
        return MyBatis3Utils.insert(this::insert, record, jobInfo, c ->
                c.map(id).toProperty("id")
                        .map(groupId).toProperty("groupId")
            .map(cron).toProperty("cron")
            .map(desc).toProperty("desc")
            .map(addTime).toProperty("addTime")
            .map(updateTime).toProperty("updateTime")
            .map(author).toProperty("author")
            .map(alarmEmail).toProperty("alarmEmail")
            .map(executorRouteStrategy).toProperty("executorRouteStrategy")
            .map(executorHandler).toProperty("executorHandler")
            .map(executorParam).toProperty("executorParam")
            .map(executorBlockStrategy).toProperty("executorBlockStrategy")
            .map(executorTimeout).toProperty("executorTimeout")
            .map(executorFailRetryCount).toProperty("executorFailRetryCount")
            .map(triggerStatus).toProperty("triggerStatus")
            .map(triggerLastTime).toProperty("triggerLastTime")
            .map(triggerNextTime).toProperty("triggerNextTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<JobInfo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, jobInfo, c ->
                c.map(id).toProperty("id")
                        .map(groupId).toProperty("groupId")
            .map(cron).toProperty("cron")
            .map(desc).toProperty("desc")
            .map(addTime).toProperty("addTime")
            .map(updateTime).toProperty("updateTime")
            .map(author).toProperty("author")
            .map(alarmEmail).toProperty("alarmEmail")
            .map(executorRouteStrategy).toProperty("executorRouteStrategy")
            .map(executorHandler).toProperty("executorHandler")
            .map(executorParam).toProperty("executorParam")
            .map(executorBlockStrategy).toProperty("executorBlockStrategy")
            .map(executorTimeout).toProperty("executorTimeout")
            .map(executorFailRetryCount).toProperty("executorFailRetryCount")
            .map(triggerStatus).toProperty("triggerStatus")
            .map(triggerLastTime).toProperty("triggerLastTime")
            .map(triggerNextTime).toProperty("triggerNextTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(JobInfo record) {
        return MyBatis3Utils.insert(this::insert, record, jobInfo, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(groupId).toPropertyWhenPresent("groupId", record::getGroupId)
            .map(cron).toPropertyWhenPresent("cron", record::getCron)
            .map(desc).toPropertyWhenPresent("desc", record::getDesc)
            .map(addTime).toPropertyWhenPresent("addTime", record::getAddTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(author).toPropertyWhenPresent("author", record::getAuthor)
            .map(alarmEmail).toPropertyWhenPresent("alarmEmail", record::getAlarmEmail)
            .map(executorRouteStrategy).toPropertyWhenPresent("executorRouteStrategy", record::getExecutorRouteStrategy)
            .map(executorHandler).toPropertyWhenPresent("executorHandler", record::getExecutorHandler)
            .map(executorParam).toPropertyWhenPresent("executorParam", record::getExecutorParam)
            .map(executorBlockStrategy).toPropertyWhenPresent("executorBlockStrategy", record::getExecutorBlockStrategy)
            .map(executorTimeout).toPropertyWhenPresent("executorTimeout", record::getExecutorTimeout)
            .map(executorFailRetryCount).toPropertyWhenPresent("executorFailRetryCount", record::getExecutorFailRetryCount)
            .map(triggerStatus).toPropertyWhenPresent("triggerStatus", record::getTriggerStatus)
            .map(triggerLastTime).toPropertyWhenPresent("triggerLastTime", record::getTriggerLastTime)
            .map(triggerNextTime).toPropertyWhenPresent("triggerNextTime", record::getTriggerNextTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<JobInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, jobInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<JobInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, jobInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<JobInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, jobInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<JobInfo> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, jobInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(JobInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(groupId).equalTo(record::getGroupId)
                .set(cron).equalTo(record::getCron)
                .set(desc).equalTo(record::getDesc)
                .set(addTime).equalTo(record::getAddTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(author).equalTo(record::getAuthor)
                .set(alarmEmail).equalTo(record::getAlarmEmail)
                .set(executorRouteStrategy).equalTo(record::getExecutorRouteStrategy)
                .set(executorHandler).equalTo(record::getExecutorHandler)
                .set(executorParam).equalTo(record::getExecutorParam)
                .set(executorBlockStrategy).equalTo(record::getExecutorBlockStrategy)
                .set(executorTimeout).equalTo(record::getExecutorTimeout)
                .set(executorFailRetryCount).equalTo(record::getExecutorFailRetryCount)
                .set(triggerStatus).equalTo(record::getTriggerStatus)
                .set(triggerLastTime).equalTo(record::getTriggerLastTime)
                .set(triggerNextTime).equalTo(record::getTriggerNextTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(JobInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(groupId).equalToWhenPresent(record::getGroupId)
                .set(cron).equalToWhenPresent(record::getCron)
                .set(desc).equalToWhenPresent(record::getDesc)
                .set(addTime).equalToWhenPresent(record::getAddTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(author).equalToWhenPresent(record::getAuthor)
                .set(alarmEmail).equalToWhenPresent(record::getAlarmEmail)
                .set(executorRouteStrategy).equalToWhenPresent(record::getExecutorRouteStrategy)
                .set(executorHandler).equalToWhenPresent(record::getExecutorHandler)
                .set(executorParam).equalToWhenPresent(record::getExecutorParam)
                .set(executorBlockStrategy).equalToWhenPresent(record::getExecutorBlockStrategy)
                .set(executorTimeout).equalToWhenPresent(record::getExecutorTimeout)
                .set(executorFailRetryCount).equalToWhenPresent(record::getExecutorFailRetryCount)
                .set(triggerStatus).equalToWhenPresent(record::getTriggerStatus)
                .set(triggerLastTime).equalToWhenPresent(record::getTriggerLastTime)
                .set(triggerNextTime).equalToWhenPresent(record::getTriggerNextTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(JobInfo record) {
        return update(c ->
                c.set(groupId).equalTo(record::getGroupId)
            .set(cron).equalTo(record::getCron)
            .set(desc).equalTo(record::getDesc)
            .set(addTime).equalTo(record::getAddTime)
            .set(updateTime).equalTo(record::getUpdateTime)
            .set(author).equalTo(record::getAuthor)
            .set(alarmEmail).equalTo(record::getAlarmEmail)
            .set(executorRouteStrategy).equalTo(record::getExecutorRouteStrategy)
            .set(executorHandler).equalTo(record::getExecutorHandler)
            .set(executorParam).equalTo(record::getExecutorParam)
            .set(executorBlockStrategy).equalTo(record::getExecutorBlockStrategy)
            .set(executorTimeout).equalTo(record::getExecutorTimeout)
            .set(executorFailRetryCount).equalTo(record::getExecutorFailRetryCount)
            .set(triggerStatus).equalTo(record::getTriggerStatus)
            .set(triggerLastTime).equalTo(record::getTriggerLastTime)
            .set(triggerNextTime).equalTo(record::getTriggerNextTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(JobInfo record) {
        return update(c ->
                c.set(groupId).equalToWhenPresent(record::getGroupId)
            .set(cron).equalToWhenPresent(record::getCron)
            .set(desc).equalToWhenPresent(record::getDesc)
            .set(addTime).equalToWhenPresent(record::getAddTime)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .set(author).equalToWhenPresent(record::getAuthor)
            .set(alarmEmail).equalToWhenPresent(record::getAlarmEmail)
            .set(executorRouteStrategy).equalToWhenPresent(record::getExecutorRouteStrategy)
            .set(executorHandler).equalToWhenPresent(record::getExecutorHandler)
            .set(executorParam).equalToWhenPresent(record::getExecutorParam)
            .set(executorBlockStrategy).equalToWhenPresent(record::getExecutorBlockStrategy)
            .set(executorTimeout).equalToWhenPresent(record::getExecutorTimeout)
            .set(executorFailRetryCount).equalToWhenPresent(record::getExecutorFailRetryCount)
            .set(triggerStatus).equalToWhenPresent(record::getTriggerStatus)
            .set(triggerLastTime).equalToWhenPresent(record::getTriggerLastTime)
            .set(triggerNextTime).equalToWhenPresent(record::getTriggerNextTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}