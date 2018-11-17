package wang.ismy.oa.dto;


import lombok.Data;

import java.io.InputStream;
import java.io.OutputStream;

@Data
public class ImgFileDto {

    private String fileName;

    private InputStream inputStream;
}
