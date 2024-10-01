package com.laterya.hearing.service;

public interface FaceService {
    String addFace(String bytes, long id);
    String compareFace(String bytes);
    String deleteFace(long id);
}
