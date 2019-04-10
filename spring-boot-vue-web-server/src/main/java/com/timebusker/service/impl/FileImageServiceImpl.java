package com.timebusker.service.impl;

import com.timebusker.common.fastdfs.FastDFSFile;
import com.timebusker.mapper.FileImageMapper;
import com.timebusker.model.FileImage;
import com.timebusker.service.FileImageService;
import com.timebusker.utils.SequenceIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @DESC:FileImageServiceImpl
 * @author:timebusker
 * @date:2019/4/11
 */
@Service
public class FileImageServiceImpl implements FileImageService {

    @Autowired
    private FileImageMapper fileImageMapper;

    @Autowired
    private SequenceIdUtil sequenceId;

    @Override
    public void save(FastDFSFile file) {
        FileImage image = new FileImage();
        image.setId(sequenceId.nextId());
        image.setName(file.getName());
        image.setUserId("admin");
        image.setUrl(file.getUrl());
        image.setDescription(file.getMd5());
        image.setCreateTime(new Date());
        fileImageMapper.insert(image);
    }

    @Override
    public List<FileImage> getAll() {
        return null;
    }
}
