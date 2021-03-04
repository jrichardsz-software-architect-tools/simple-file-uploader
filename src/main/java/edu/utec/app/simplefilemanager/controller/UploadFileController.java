package edu.utec.app.simplefilemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import edu.utec.app.simplefilemanager.service.FileService;

@Controller
public class UploadFileController {

  @Autowired
  private FileService fileService;

  @GetMapping({"/", "/upload/view"})
  public String upload(Model model) {
    return "index";
  }

  @PostMapping("/upload/action")
  public String uploadFile(@RequestParam("file") MultipartFile file, Model model,
      @RequestParam String destinyFolder) {

    try {
      fileService.uploadFile(file, destinyFolder);
      model.addAttribute("message", "You successfully uploaded " + file.getOriginalFilename());
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }

    return "result";
  }
}
