package com.github.file.service;


import com.github.file.domain.FileObjectSummary;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

public interface IFileService {

    InputStream dowloadFile(String var1, String var2);

    String uploadFile(String var1, InputStream var2, String var3, String var4);

    Boolean deleteFile(String var1, String var2);

    List<FileObjectSummary> list(String var1, String var2);

    String copyFile(String var1, String var2, String var3, String var4);

    URL generatePresignedUrl(String var1, String var2, Date var3);
}
