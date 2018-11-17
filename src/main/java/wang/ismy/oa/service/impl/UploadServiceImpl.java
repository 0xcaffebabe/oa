package wang.ismy.oa.service.impl;

import com.aliyun.oss.OSSClient;
import org.springframework.stereotype.Service;
import wang.ismy.oa.dto.ImgFileDto;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.service.UploadService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public String uploadImg(ImgFileDto dto) {


        String endpoint = "http://oss-cn-qingdao.aliyuncs.com";

        String accessKeyId = "LTAI8M8FEYq6S0ph";
        String accessKeySecret = "cpXqlYzPPo4TaCYKfR18VROXJJ9jAF";

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);


        String fileName = dto.getFileName();

        ossClient.putObject("ismy1", "oa/" + fileName, dto.getInputStream());

        ossClient.shutdown();
        String result="http://ismy1.oss-cn-qingdao.aliyuncs.com/oa/" + fileName;

        return result;
    }
}
