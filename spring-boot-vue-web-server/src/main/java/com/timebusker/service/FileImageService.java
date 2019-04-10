package com.timebusker.service;

import com.timebusker.common.fastdfs.FastDFSFile;
import com.timebusker.model.FileImage;

import java.util.List;

/**
 * @DESC:FileImageService
 * @author:timebusker
 * @date:2019/4/11
 */
public interface FileImageService {

    void save(FastDFSFile file);

    List<FileImage> getAll();
}
