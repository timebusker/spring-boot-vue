package com.timebusker.service.file.impl;

import com.timebusker.common.fastdfs.FastDFSClient;
import com.timebusker.common.fastdfs.FastDFSFile;
import com.timebusker.mapper.file.FileTmpMapper;
import com.timebusker.model.file.FileTmp;
import com.timebusker.service.AbstractBaseService;
import com.timebusker.service.file.FileTmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @DESC:FileTmpServiceImpl
 * @author:timebusker
 * @date:2019/4/17
 */

@Service
public class FileTmpServiceImpl extends AbstractBaseService implements FileTmpService {

    @Autowired
    private FileTmpMapper fileTmpMapper;

    @Override
    public FileTmp save(MultipartFile file) {
        // 正常操作：先校验文件MD5值，判断文件是否存在再上传，上传后服务端再次校验;
        FileTmp tmp = null;
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            FastDFSFile fast = new FastDFSFile(file.getOriginalFilename(), file.getBytes(), suffix);
            tmp = fileTmpMapper.selectByPrimaryKey(fast.getMd5());
            if (tmp == null) {
                tmp = new FileTmp();
                String[] res = FastDFSClient.upload(fast);
                String path = FastDFSClient.getTrackerUrl() + "/" + res[0] + "/" + res[1];
                tmp.setId(fast.getMd5());
                tmp.setUrl(path);
                tmp.setCreateTime(new Date());
                fileTmpMapper.insert(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmp;
    }

    @Override
    public FileTmp queryFileTmp(String id) {
        return null;
    }

    @Override
    public List<FileTmp> queryList(List<String> ids) {
        return null;
    }
}
