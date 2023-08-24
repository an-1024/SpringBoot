package com.anzh.config.datasourceconfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.anzh.utils.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@Slf4j
public class DataSourcesAutoConfig {
    
    private final DataSourceProperties properties;
    
    // 公钥Key
    private static final String PUBLIC_KEY = "MGcwDQYJKoZIhvcNAQEBBQADVgAwUwJMALWkYTLAO82qCfge6uZUZWEc0NbarkpUvG3Ku3Pn9NfiOcBlQNXTtrf2qM0I00duV4puaesLFIxYVYztHdtLDMWBWCX8s5pz08ceJwIDAQAB";
    
    @Autowired
    public DataSourcesAutoConfig (DataSourceProperties properties) {
        this.properties = properties;
    }
    
    @Bean
    public DataSource dataSource() {
        try (DruidDataSource dataSource = new DruidDataSource()) {
        //可以在此处调用相关接口获取数据库的配置信息进行 DataSource 的配置
            dataSource.setUrl(properties.getUrl());
            dataSource.setUsername(properties.getUsername());
            //密码处解密
            RSAUtil.decryptByPublicKey(properties.getPassword(), PUBLIC_KEY);
            dataSource.setPassword(properties.getPassword());
            dataSource.setDriverClassName(properties.getDriverClassName());
            return dataSource;
        }catch (Exception e) {
            log.info("解密数据异常，异常信息为：{}", e.getMessage());
        } 
        return null;
    }
}
