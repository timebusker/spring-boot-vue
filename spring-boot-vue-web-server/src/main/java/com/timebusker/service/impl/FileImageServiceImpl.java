package com.timebusker.service.impl;

import com.alibaba.fastjson.JSON;
import com.timebusker.common.fastdfs.FastDFSClient;
import com.timebusker.common.fastdfs.FastDFSFile;
import com.timebusker.mapper.FileImageMapper;
import com.timebusker.model.FileImage;
import com.timebusker.service.AbstractBaseService;
import com.timebusker.service.FileImageService;
import com.timebusker.utils.MD5Utils;
import com.timebusker.utils.SequenceIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

/**
 * @DESC:FileImageServiceImpl
 * @author:timebusker
 * @date:2019/4/11
 */
@Service
public class FileImageServiceImpl extends AbstractBaseService implements FileImageService {

    private static final int BUFFE_SIZE = 1024;

    @Autowired
    private FileImageMapper fileImageMapper;

    @Autowired
    private SequenceIdUtil sequenceId;


    @Override
    public FileImage save(MultipartFile file) {
        FileImage image = null;
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            InputStream inputStream = file.getInputStream();
            FastDFSFile fast = new FastDFSFile(file.getOriginalFilename(), file.getBytes(), suffix);
            fast.setAuthor("管理员");
            image = fileImageMapper.selectByPrimaryKey(fast.getMd5());
            if (image == null) {
                image = new FileImage();
                String[] res = FastDFSClient.upload(fast);
                String path = FastDFSClient.getTrackerUrl() + "/" + res[0] + "/" + res[1];
                image.setId(fast.getMd5());
                image.setName(file.getOriginalFilename());
                image.setUserId(fast.getAuthor());
                image.setUrl(path);
                image.setDescription(fast.getMd5());
                image.setCreateTime(new Date());
                fileImageMapper.insert(image);
            }
        } catch (IOException io) {
            logger.error(io.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return image;
    }

    @Override
    public List<FileImage> getAll() {
        List<FileImage> list = new ArrayList<>();
        try {
            list = fileImageMapper.selectAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public FileImage getImage(String id) {
        FileImage image = new FileImage();
        try {
            image = fileImageMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return image;
    }
}
