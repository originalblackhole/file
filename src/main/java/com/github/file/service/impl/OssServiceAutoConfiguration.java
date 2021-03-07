package com.github.file.service.impl;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.github.file.domain.FileProperties;
import com.github.file.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({FileProperties.class})
public class OssServiceAutoConfiguration {
    @Autowired
    private FileProperties oSSFileProperties;

    public OssServiceAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            value = {"octopus.file.cloudType"},
            havingValue = "oss"
    )
    public IFileService ossFileServiceImplConfig() {
        ClientConfiguration config = new ClientConfiguration();
        OSSClient oSSClient = new OSSClient(this.oSSFileProperties.getEndpoint(), this.oSSFileProperties.getAccessKeyId(), this.oSSFileProperties.getAccessKeySecret(), config);
        OSSFileServiceImpl oSSFileServiceImpl = new OSSFileServiceImpl(oSSClient, this.oSSFileProperties);
        return oSSFileServiceImpl;
    }
}
