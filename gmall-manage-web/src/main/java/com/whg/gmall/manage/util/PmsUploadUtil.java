package com.whg.gmall.manage.util;

import com.whg.gmall.manage.GmallManageWebApplication;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.whg.gmall.service.Constant.FILE_SERVICE_IP;

/**
 * Created by Administrator on 2020/10/7.
 */
public class PmsUploadUtil {
    public static String uploadFile(MultipartFile multipartFile) {
        try {
//        String path = PmsUploadUtil.class.getResource("/tracker.conf").getPath();
//        ClientGlobal.init(path);
            ClientGlobal.initByProperties("fdfs-client.properties");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer, storeStorage);
            byte[] bytes = multipartFile.getBytes();//获取上传的二进制对象
            String orginalFilename = multipartFile.getOriginalFilename();
            String fileExtName = orginalFilename.substring(orginalFilename.lastIndexOf(".") + 1);
            String[] uploadFiles = storageClient.upload_file(bytes,fileExtName,null);
//            String[] uploadFiles = storageClient.upload_file(orginalFilename,fileExtName,null);
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < uploadFiles.length; i++) {
                result.append("/" + uploadFiles[i]);
            }
            return FILE_SERVICE_IP + result.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }

}
