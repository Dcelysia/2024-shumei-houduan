package com.laterya.hearing.service.impl;

import com.google.gson.Gson;
import com.huaweicloud.frs.client.param.AuthInfo;
import com.huaweicloud.frs.client.result.AddFaceResult;
import com.huaweicloud.frs.client.result.DeleteFaceResult;
import com.huaweicloud.frs.client.result.SearchFaceResult;
import com.huaweicloud.frs.client.service.FrsClient;
import com.huaweicloud.frs.common.FrsException;


import com.laterya.hearing.service.FaceService;
import com.laterya.hearing.response.Response;


import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class FaceServiceImpl implements FaceService {
    private Gson gson=new Gson();
    String ak = "BOL2NVFTLQH3ZCRNNBPP";
    String sk = "eWxClOTrVaQe5jElN65zimqsGWcDciP4JHvhKhn3";
    String endpoint = "https://face.cn-east-3.myhuaweicloud.com";
    String region = "cn-east-3";
    String projectId = "0d3edd372a00f2af2fccc0085559baa7";
    AuthInfo authInfo=new AuthInfo(endpoint,region,ak,sk);
    FrsClient frsClient = new FrsClient(authInfo, projectId);

    @Override
    /*
    存放人脸,保证唯一性。
     */
    public String addFace(String bytes, long id) {
        try {
            double similarity;
            int UserId;
            SearchFaceResult searchFaceResult = frsClient.getV2().getSearchService().searchFaceByBase64("myFaceSet", bytes);
            int s=searchFaceResult.getFaces().size()-1;
            /*System.out.println(searchFaceResult);*/
            while (s>=0){
                UserId= Integer.parseInt(searchFaceResult.getFaces().get(s).getExternalImageId());
                similarity=searchFaceResult.getFaces().get(s).getSimilarity();
                if(UserId==id||similarity>0.93){
                    return gson.toJson(new Response(1,true,"该用户已有过账号绑定人脸，请直接登录绑定账号"));
                }
                s--;
            }
            if(id!=0){
                AddFaceResult addFaceResult = frsClient.getV2().getFaceService().addFaceByBase64("myFaceSet", String.valueOf(id),bytes);
                return gson.toJson(addFaceResult);
            }
        } catch (FrsException e) { //While http status code is not http_ok
           // e.printStackTrace();
            return gson.toJson(new Response(1,true,"添加失败"));
        } catch (IOException e) {
           // e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }

    @Override
    public String compareFace(String bytes/*,int id*/) {
        try {
            double similarity;
            AddFaceResult addFaceResult = frsClient.getV2().getFaceService().addFaceByBase64("myFaceSet",bytes);
            String resultId=addFaceResult.getFaces().get(0).getFaceId();
            /*System.out.println(resultId);*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                return e.getMessage();
            }
            SearchFaceResult searchFaceResult=frsClient.getV2().getSearchService().searchFaceByBase64("myFaceSet",bytes);
            int s=searchFaceResult.getFaces().size()-1;
            frsClient.getV2().getFaceService().deleteFaceByFaceId("myFaceSet",resultId);
           /* if(deleteFaceResult!=null){
                System.out.println("删除成功");
            }*/
            while(s>=0){
                /*System.out.println(s);*/
                similarity=searchFaceResult.getFaces().get(s).getSimilarity();
                if(similarity>0.93&&similarity!=1){
                   return searchFaceResult.getFaces().get(s).getExternalImageId();
                }
                s--;
            }
        } catch (FrsException e) {
            //e.printStackTrace();
            return gson.toJson(new Response(1,true,"比对失败"));
        } catch (IOException e) {
            //e.printStackTrace();
            return e.getMessage();
        }
        /*try {
            String list=addFace(bytes,id);

            JSONObject jsonObject=JSONObject.parseObject(list);
            String getFace=jsonObject.getString("faces");
            getFace= getFace.replaceAll("[\\[\\]]","");
            JSONObject object=JSONObject.parseObject(getFace);
            String getFaceId=object.getString("face_id");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            double similarity;
            boolean t=false;
            String result=null;

            if(getFaceId!=null){
                SearchFaceResult searchFaceResult = frsClient.getV2().getSearchService().searchFaceByFaceId("myFaceSet", getFaceId);
                int s=searchFaceResult.getFaces().size()-1;
                while(s>0){
                    similarity=searchFaceResult.getFaces().get(s).getSimilarity();
                    int UserId= Integer.parseInt(searchFaceResult.getFaces().get(s).getExternalImageId());
                    if(similarity>0.93&&similarity!=1&&UserId==id){
                        String resultId;
                        t=true;
                        resultId=searchFaceResult.getFaces().get(s).getFaceId();
                        result= searchFaceResult.getFaces().get(s).toJSONString();
                        try {
                            frsClient.getV2().getFaceService().deleteFaceByFaceId("myFaceSet",resultId);
                        } catch (FrsException e) { //While http status code is not http_ok
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    s--;
                }
                if (t){
                    return result;
                }
            }
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
            return e.msg;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    public String deleteFace(long id) {
        try {
            DeleteFaceResult result=frsClient.getV2().getFaceService().deleteFaceByExternalImageId("myFaceSet", String.valueOf(id));
            if(result!=null){
                return gson.toJson(new Response(0,false,"删除成功"));
            }
        } catch (FrsException e) {
            //e.printStackTrace();
            return gson.toJson(new Response(1,true,"删除失败"));
        } catch (IOException e) {
            //e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }
}
