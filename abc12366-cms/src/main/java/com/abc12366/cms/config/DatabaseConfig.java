package com.abc12366.cms.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * mybatis配置文件
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-21 11:15 AM
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig implements EnvironmentAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
    private static RelaxedPropertyResolver propertyResolver;
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        if (propertyResolver == null) {
            propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
        }
    }

    @Bean(name = "db1DataSource", initMethod = "init", destroyMethod = "close")
    @Primary
    public DruidDataSource db1DataSource() throws SQLException {
        // druid数据源
        if (StringUtils.isEmpty(propertyResolver.getProperty("db1.url"))) {
            System.out.println("Your database connection pool configuration is incorrect!" +
                    " Please check your Spring profile, current profiles are:" +
                    Arrays.toString(environment.getActiveProfiles()));
            throw new ApplicationContextException(
                    "Database connection pool is not configured correctly");
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(propertyResolver.getProperty("db1.driverClassName"));
        druidDataSource.setUrl(propertyResolver.getProperty("db1.url"));
        druidDataSource.setUsername(propertyResolver.getProperty("db1.username"));
        druidDataSource.setPassword(propertyResolver.getProperty("db1.password"));
        druidDataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("db1.initialSize")));
        druidDataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("db1.minIdle")));
        druidDataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("db1.maxActive")));
        druidDataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("db1.maxWait")));
        druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(propertyResolver.getProperty("db1" +
                ".timeBetweenEvictionRunsMillis")));
        druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(propertyResolver.getProperty("db1" +
                ".minEvictableIdleTimeMillis")));
        druidDataSource.setValidationQuery(propertyResolver.getProperty("db1.validationQuery"));
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("db1.testWhileIdle")));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("db1.testOnBorrow")));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("db1.testOnReturn")));
        druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("db1" +
                ".poolPreparedStatements")));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty
                ("db1.maxPoolPreparedStatementPerConnectionSize")));
        druidDataSource.setFilters(propertyResolver.getProperty("db1.filters"));
        druidDataSource.setUseGlobalDataSourceStat(Boolean.parseBoolean(propertyResolver.getProperty("db1" +
                ".useGlobalDataSourceStat")));
        return druidDataSource;
    }

    @Bean(name = "db2DataSource", initMethod = "init", destroyMethod = "close")
    public DataSource db2DataSource() throws SQLException {
        // druid数据源
        if (StringUtils.isEmpty(propertyResolver.getProperty("db2.url"))) {
            System.out.println("Your database connection pool configuration is incorrect!" +
                    " Please check your Spring profile, current profiles are:" +
                    Arrays.toString(environment.getActiveProfiles()));
            throw new ApplicationContextException(
                    "Database connection pool is not configured correctly");
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(propertyResolver.getProperty("db2.driverClassName"));
        druidDataSource.setUrl(propertyResolver.getProperty("db2.url"));
        druidDataSource.setUsername(propertyResolver.getProperty("db2.username"));
        druidDataSource.setPassword(propertyResolver.getProperty("db2.password"));
        druidDataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("db2.initialSize")));
        druidDataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("db2.minIdle")));
        druidDataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("db2.maxActive")));
        druidDataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("db2.maxWait")));
        druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(propertyResolver.getProperty("db2" +
                ".timeBetweenEvictionRunsMillis")));
        druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(propertyResolver.getProperty("db2" +
                ".minEvictableIdleTimeMillis")));
        druidDataSource.setValidationQuery(propertyResolver.getProperty("db2.validationQuery"));
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("db2.testWhileIdle")));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("db2.testOnBorrow")));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("db2.testOnReturn")));
        druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("db2" +
                ".poolPreparedStatements")));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty
                ("db2.maxPoolPreparedStatementPerConnectionSize")));
        druidDataSource.setFilters(propertyResolver.getProperty("db2.filters"));
        druidDataSource.setUseGlobalDataSourceStat(Boolean.parseBoolean(propertyResolver.getProperty("db2" +
                ".useGlobalDataSourceStat")));
        return druidDataSource;
    }

    @Bean("db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory db1SqlSessionFactory() throws SQLException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(db1DataSource());
        // 自动重命名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.abc12366.cms.model");
        // 设置 typeHandler
//        sqlSessionFactoryBean.setTypeHandlersPackage("com.example.project.typeHandler");
        // 添加拦截器插件 (如果有的话, 分页插件, 分表插件等)
//        sqlSessionFactory.setPlugins(new Interceptor[]{pageHelper});
        // 设置 mapper 文件
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resolver
                    .getResources("classpath:com/abc12366/cms/mapper/db1/*.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            LOGGER.error("初始化DB1SqlSessionFactory失败", e);
            throw new RuntimeException(e);
        }
    }

    @Bean("db2SqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory() throws SQLException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(db2DataSource());
        // 自动重命名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.abc12366.cms.model");
        // 设置 typeHandler
//        sqlSessionFactoryBean.setTypeHandlersPackage("com.example.project.typeHandler");
        // 添加拦截器插件 (如果有的话, 分页插件, 分表插件等)
//        sqlSessionFactory.setPlugins(new Interceptor[]{pageHelper});
        // 设置 mapper 文件
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resolver
                    .getResources("classpath:com/abc12366/cms/mapper/db2/*.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            LOGGER.error("初始化DB2SqlSessionFactory失败", e);
            throw new RuntimeException(e);
        }
    }

    @Bean("db1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate db1SqlSessionTemplate() throws SQLException {
        return new SqlSessionTemplate(db1SqlSessionFactory());
    }

    @Bean("db2SqlSessionTemplate")
    public SqlSessionTemplate db2SqlSessionTemplate() throws SQLException {
        return new SqlSessionTemplate(db2SqlSessionFactory());
    }

    @Bean("db1TxManager")
    @Primary
    public PlatformTransactionManager db1TxManager() throws SQLException {
        return new DataSourceTransactionManager(db1DataSource());
    }

    @Bean("db2TxManager")
    public PlatformTransactionManager db2TxManager() throws SQLException {
        return new DataSourceTransactionManager(db2DataSource());
    }

    @Bean(name = "gw1DataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource gw1DataSource() throws SQLException {
        // druid数据源
        if (StringUtils.isEmpty(propertyResolver.getProperty("gw1.url"))) {
            System.out.println("Your database connection pool configuration is incorrect!" +
                    " Please check your Spring profile, current profiles are:" +
                    Arrays.toString(environment.getActiveProfiles()));
            throw new ApplicationContextException(
                    "Database connection pool is not configured correctly");
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(propertyResolver.getProperty("gw1.driverClassName"));
        druidDataSource.setUrl(propertyResolver.getProperty("gw1.url"));
        druidDataSource.setUsername(propertyResolver.getProperty("gw1.username"));
        druidDataSource.setPassword(propertyResolver.getProperty("gw1.password"));
        druidDataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("gw1.initialSize")));
        druidDataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("gw1.minIdle")));
        druidDataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("gw1.maxActive")));
        druidDataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("gw1.maxWait")));
        druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(propertyResolver.getProperty("gw1" +
                ".timeBetweenEvictionRunsMillis")));
        druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(propertyResolver.getProperty("gw1" +
                ".minEvictableIdleTimeMillis")));
        druidDataSource.setValidationQuery(propertyResolver.getProperty("gw1.validationQuery"));
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("gw1.testWhileIdle")));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("gw1.testOnBorrow")));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("gw1.testOnReturn")));
        druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("gw1" +
                ".poolPreparedStatements")));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty
                ("gw1.maxPoolPreparedStatementPerConnectionSize")));
        druidDataSource.setFilters(propertyResolver.getProperty("gw1.filters"));
        druidDataSource.setUseGlobalDataSourceStat(Boolean.parseBoolean(propertyResolver.getProperty("gw1" +
                ".useGlobalDataSourceStat")));
        return druidDataSource;
    }

    @Bean(name = "gw2DataSource", initMethod = "init", destroyMethod = "close")
    public DataSource gw2DataSource() throws SQLException {
        // druid数据源
        if (StringUtils.isEmpty(propertyResolver.getProperty("gw2.url"))) {
            System.out.println("Your database connection pool configuration is incorrect!" +
                    " Please check your Spring profile, current profiles are:" +
                    Arrays.toString(environment.getActiveProfiles()));
            throw new ApplicationContextException(
                    "Database connection pool is not configured correctly");
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(propertyResolver.getProperty("gw2.driverClassName"));
        druidDataSource.setUrl(propertyResolver.getProperty("gw2.url"));
        druidDataSource.setUsername(propertyResolver.getProperty("gw2.username"));
        druidDataSource.setPassword(propertyResolver.getProperty("gw2.password"));
        druidDataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("gw2.initialSize")));
        druidDataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("gw2.minIdle")));
        druidDataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("gw2.maxActive")));
        druidDataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("gw2.maxWait")));
        druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(propertyResolver.getProperty("gw2" +
                ".timeBetweenEvictionRunsMillis")));
        druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(propertyResolver.getProperty("gw2" +
                ".minEvictableIdleTimeMillis")));
        druidDataSource.setValidationQuery(propertyResolver.getProperty("gw2.validationQuery"));
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("gw2.testWhileIdle")));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("gw2.testOnBorrow")));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("gw2.testOnReturn")));
        druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("gw2" +
                ".poolPreparedStatements")));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty
                ("gw2.maxPoolPreparedStatementPerConnectionSize")));
        druidDataSource.setFilters(propertyResolver.getProperty("gw2.filters"));
        druidDataSource.setUseGlobalDataSourceStat(Boolean.parseBoolean(propertyResolver.getProperty("gw2" +
                ".useGlobalDataSourceStat")));
        return druidDataSource;
    }

    @Bean("gw1SqlSessionFactory")
    @Primary
    public SqlSessionFactory gw1SqlSessionFactory() throws SQLException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(gw1DataSource());
        // 自动重命名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.abc12366.gateway.model");
        // 设置 typeHandler
//        sqlSessionFactoryBean.setTypeHandlersPackage("com.example.project.typeHandler");
        // 添加拦截器插件 (如果有的话, 分页插件, 分表插件等)
//        sqlSessionFactory.setPlugins(new Interceptor[]{pageHelper});
        // 设置 mapper 文件
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resolver
                    .getResources("classpath:com/abc12366/gateway/mapper/db1/*.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            LOGGER.error("初始化GW1SqlSessionFactory失败", e);
            throw new RuntimeException(e);
        }
    }

    @Bean("gw2SqlSessionFactory")
    public SqlSessionFactory gw2SqlSessionFactory() throws SQLException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(gw2DataSource());
        // 自动重命名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.abc12366.gateway.model");
        // 设置 typeHandler
//        sqlSessionFactoryBean.setTypeHandlersPackage("com.example.project.typeHandler");
        // 添加拦截器插件 (如果有的话, 分页插件, 分表插件等)
//        sqlSessionFactory.setPlugins(new Interceptor[]{pageHelper});
        // 设置 mapper 文件
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resolver
                    .getResources("classpath:com/abc12366/gateway/mapper/db2/*.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            LOGGER.error("初始化DB2SqlSessionFactory失败", e);
            throw new RuntimeException(e);
        }
    }

    @Bean("gw1SqlSessionTemplate")
    public SqlSessionTemplate gw1SqlSessionTemplate() throws SQLException {
        return new SqlSessionTemplate(gw1SqlSessionFactory());
    }

    @Bean("gw2SqlSessionTemplate")
    public SqlSessionTemplate gw2SqlSessionTemplate() throws SQLException {
        return new SqlSessionTemplate(gw2SqlSessionFactory());
    }

    @Bean("gw1TxManager")
    public PlatformTransactionManager gw1TxManager() throws SQLException {
        return new DataSourceTransactionManager(gw1DataSource());
    }

    @Bean("gw2TxManager")
    public PlatformTransactionManager gw2TxManager() throws SQLException {
        return new DataSourceTransactionManager(gw2DataSource());
    }
}
