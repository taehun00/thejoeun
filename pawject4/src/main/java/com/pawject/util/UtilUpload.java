package com.pawject.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UtilUpload {

	@Value("${file.upload-dir}")
	private String resourcePath;

    public String fileUpload(MultipartFile file, String subDir) throws IOException {
        UUID uid = UUID.randomUUID();
        String save = uid + "_" + file.getOriginalFilename();

        File targetDir = new File(resourcePath, subDir);
        if (!targetDir.exists()) targetDir.mkdirs();

        File target = new File(targetDir, save);
        FileCopyUtils.copy(file.getBytes(), target);

        return subDir + "/" + save;  // DB엔 "foodimg/xxx.png" 식으로 저장
    }
  
}
