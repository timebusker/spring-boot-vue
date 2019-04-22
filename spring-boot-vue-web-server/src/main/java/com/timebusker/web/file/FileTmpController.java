package com.timebusker.web.file;

import com.timebusker.common.web.ResultVo;
import com.timebusker.model.file.FileTmp;
import com.timebusker.service.file.FileTmpService;
import com.timebusker.web.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @DESC:FileTmpController
 * @author:timebusker
 * @date:2019/4/17
 */

@RestController
@RequestMapping("/file/tmp")
public class FileTmpController extends AbstractBaseController {

    @Autowired
    private FileTmpService fileTmpService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultVo upload(MultipartFile file) {
        FileTmp tmp = fileTmpService.save(file);
        return ResultVo.ok().put("file", tmp);
    }
}
