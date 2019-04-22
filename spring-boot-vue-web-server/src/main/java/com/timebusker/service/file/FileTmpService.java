package com.timebusker.service.file;

import com.timebusker.model.file.FileTmp;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @DESC:FileTmpService
 * @author:timebusker
 * @date:2019/4/17
 */
public interface FileTmpService {

    FileTmp save(MultipartFile file);

    FileTmp queryFileTmp(String id);

    List<FileTmp> queryList(List<String> ids);
}
