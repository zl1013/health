package com.zzl.test;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/16 12:41
 * Version 1.0
 */

public class QiNiuTest {
    @Test
    public void Upload(){
        //构造一个带指定 Region对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "ouRrsYtAWetdWxgVA6ehlVKtCEcciCb7ooIGL_iT";
        String secretKey = "fyA-dM0128fUZdeAbiOPOhqHGZxcd8DWXRzzKBF3";
        String bucket = "zzl-health";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "E:\\零基础+就业课(2.1)新版\\18-就业课(2.1)-嘿马项目\\08_船志健康项目\\day04 预约管理-套餐管理\\day04 预约管理-套餐管理\\资源\\图片资源\\03a36073-a140-4942-9b9b-712cecb144901.jpg";
        //key为文件名,默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    @Test
    public void test(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        //...其他参数参考类注释
        String accessKey = "ouRrsYtAWetdWxgVA6ehlVKtCEcciCb7ooIGL_iT";
        String secretKey = "fyA-dM0128fUZdeAbiOPOhqHGZxcd8DWXRzzKBF3";
        String bucket = "zzl-health";
        String key = "FuM1Sa5TtL_ekLsdkYWcf5pyjKGu";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
