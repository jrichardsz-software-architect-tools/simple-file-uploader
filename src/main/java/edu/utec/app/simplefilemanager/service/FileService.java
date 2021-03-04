package edu.utec.app.simplefilemanager.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${allowed.content.types}")
  public String allowedContentTypesRaw;

  private List<String> allowedContentTypes;

  public void uploadFile(MultipartFile file, String uploadDir) throws Exception {

    if (!allowedContentTypes.contains(file.getContentType())) {
      throw new Exception("File content type is not allowed: " + file.getContentType());
    }

    File folder = new File(uploadDir);
    if (!folder.exists()) {
      boolean bool = folder.mkdir();
      if (!bool) {
        throw new Exception("Sorry couldn’t create specified directory:" + uploadDir);
      }
    }

    try {
      Path copyLocation =
          Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
      Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
      logger.error(file.getOriginalFilename() + " cannot be stored.", e);
      throw new Exception(file.getOriginalFilename() + " cannot be stored." + e.getMessage());
    }
  }

  @PostConstruct
  public void init() {
    if (allowedContentTypes == null) {
      allowedContentTypesRaw = allowedContentTypesRaw.replaceAll("\\s+", "");
      if (allowedContentTypesRaw == null || allowedContentTypesRaw.isEmpty()) {
        logger.error("ALLOWED_CONTENT_TYPES is missing. No one file will be uploaded.");
        allowedContentTypes = new ArrayList<String>();
      } else {
        allowedContentTypes = Arrays.asList(allowedContentTypesRaw.split(","));
      }
    }
  }

}
