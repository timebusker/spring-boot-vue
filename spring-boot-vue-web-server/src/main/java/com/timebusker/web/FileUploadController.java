package com.timebusker.web;

import com.alibaba.fastjson.JSON;
import com.timebusker.common.fastdfs.FastDFSClient;
import com.timebusker.common.fastdfs.FastDFSFile;
import com.timebusker.common.web.ResponseBean;
import com.timebusker.service.FileImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @DESC:FileUploadController:文件上传控制器
 * @author:timebusker
 * @date:2019/4/10
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileImageService fileImageService;

    /**
     * file
     *
     * @param image
     * @return
     */
    @PostMapping("/single-image")
    public Object UploadSingleImage(@RequestParam("file") MultipartFile image) {
        if (image.isEmpty()) {
            ResponseBean.error("请选择文件上传！");
        }
        try {
            String suffix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".") + 1);
            FastDFSFile fast = new FastDFSFile(image.getOriginalFilename(), image.getBytes(), suffix);
            String[] res = FastDFSClient.upload(fast);
            String path = FastDFSClient.getTrackerUrl() + "/" + res[0] + "/" + res[1];
            fast.setUrl(path);
            fileImageService.save(fast);
            System.out.println(JSON.toJSONString(res));
            return ResponseBean.ok().put("url", path);
        } catch (IOException io) {
            logger.error(io.getMessage());
        }
        return ResponseBean.error("未知错误！");
    }
}
