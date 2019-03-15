package com.timebusker.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SpringBootFileUtils {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootFileUtils.class);
    private static final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    private static final String PROJECT_PATH_TMP = System.getProperty("user.dir") + "/tmp/";

    public static String setConfigFilePath(String file) {
        String target = file;
        try {
            Resource[] resources = resolver.getResources(file);
            InputStream in = resources[0].getInputStream();

            FileUtil.delete(PROJECT_PATH_TMP + file);
            File newfile = FileUtil.createFile(PROJECT_PATH_TMP, file, true);
            OutputStream out = new FileOutputStream(newfile);

            FileUtil.copyFile(in, out);
            target = PROJECT_PATH_TMP + file;
            logger.info("新目标文件路径：" + target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    static {
        File folder = new File(PROJECT_PATH_TMP);
        if (!folder.isDirectory()) {
            new Exception("需要删除的文件路径" + PROJECT_PATH_TMP + "不是一个文件目录！");
        }
        String[] list = folder.list();
        for (String name : list) {
            String filePath = PROJECT_PATH_TMP + name;
            File file = new File(filePath);
            if (file.isDirectory()) {
                continue;
            } else {
                file.delete();
            }
        }
    }
}
