package com.timebusker.web;

import com.alibaba.fastjson.JSON;
import com.timebusker.common.fastdfs.FastDFSClient;
import com.timebusker.common.fastdfs.FastDFSFile;
import com.timebusker.common.web.ResponseBean;
import com.timebusker.model.FileImage;
import com.timebusker.service.FileImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @DESC:FileUploadController:文件上传控制器
 * @author:timebusker
 * @date:2019/4/10
 */
@RestController
@RequestMapping("/file")
public class FileUploadController extends AbstractBaseController {

    @Autowired
    private FileImageService fileImageService;

    @PostMapping("/single-image")
    public Object UploadSingleImage(@RequestParam("image") MultipartFile image) {
        if (image.isEmpty()) {
            ResponseBean.error("请选择文件上传！");
        }
        FileImage res = fileImageService.save(image);
        System.out.println(JSON.toJSONString(res));
        return ResponseBean.ok().put("res", res);
    }

    @GetMapping("/image/{id}")
    public Object getImage(@PathVariable("id") String id) {
        FileImage image = fileImageService.getImage(id);
        return ResponseBean.ok().put("image", image);
    }

    @GetMapping("/list")
    public Object getImages() {
        List<FileImage> res = fileImageService.getAll();
        return ResponseBean.ok().put("res", res);
    }
}
