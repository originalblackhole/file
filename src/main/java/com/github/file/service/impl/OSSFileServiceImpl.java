package com.github.file.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectMetadata;
import com.github.file.domain.FileObjectSummary;
import com.github.file.domain.FileProperties;
import com.github.file.domain.Owner;
import com.github.file.service.IFileService;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OSSFileServiceImpl implements IFileService {
    private OSSClient ossClient;
    private FileProperties ossFileProperties;

    public OSSFileServiceImpl(OSSClient ossClient, FileProperties ossFileProperties) {
        this.ossClient = ossClient;
        this.ossFileProperties = ossFileProperties;
    }

    public InputStream dowloadFile(String bucketName, String key) {
        return this.ossClient.getObject(bucketName, key).getObjectContent();
    }

    public String uploadFile(String bucketName, InputStream inputStream, String key, String contentType) {
        try {
            ObjectMetadata objectMeta = new ObjectMetadata();
            if (!StringUtils.isEmpty(contentType)) {
                objectMeta.setContentType(contentType);
            }

            objectMeta.setContentLength((long)inputStream.available());
            this.ossClient.putObject(bucketName, key, inputStream, objectMeta);
            StringBuilder sb = new StringBuilder();
            String[] str = this.ossFileProperties.getEndpoint().split("//");
            sb.append(str[0]).append("//").append(bucketName).append(".").append(str[1]).append("/").append(key);
            return sb.toString();
        } catch (Exception var8) {
            var8.printStackTrace();
            return "";
        }
    }

    public Boolean deleteFile(String bucketName, String key) {
        try {
            this.ossClient.deleteObject(bucketName, key);
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }

    public List<FileObjectSummary> list(String bucketName, String prefix) {
        try {
            List<OSSObjectSummary> lists = this.ossClient.listObjects(bucketName, prefix).getObjectSummaries();
            if (lists.size() < 0) {
                return null;
            } else {
                List<FileObjectSummary> desList = new ArrayList();
                Iterator var5 = lists.iterator();

                while(var5.hasNext()) {
                    OSSObjectSummary ossObjectSummary = (OSSObjectSummary)var5.next();
                    FileObjectSummary fileObjectSummary = new FileObjectSummary();
                    Owner owner = new Owner(ossObjectSummary.getOwner().getId(), ossObjectSummary.getOwner().getDisplayName());
                    BeanUtils.copyProperties(ossObjectSummary, fileObjectSummary);
                    fileObjectSummary.setOwner(owner);
                    desList.add(fileObjectSummary);
                }

                return desList;
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public String copyFile(String srcBucketName, String desBucketName, String src, String des) {
        this.ossClient.copyObject(srcBucketName, src, desBucketName, des);
        return des;
    }

    public URL generatePresignedUrl(String bucketName, String key, Date expiration) {
        return this.ossClient.generatePresignedUrl(bucketName, key, expiration);
    }
}
