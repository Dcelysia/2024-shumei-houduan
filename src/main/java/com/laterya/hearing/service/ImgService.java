package com.laterya.hearing.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImgService {
    String updateImg(MultipartFile file, String baseDir, int account);
    String getImg(int id);
}
