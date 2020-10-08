package com.whg.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

    @Test
    public void contexLoads() throws IOException, MyException {
        String path = GmallManageWebApplication.class.getResource("/tracker.conf").getPath();
        ClientGlobal.init(path);
//        ClientGlobal.initByProperties("fdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
        StorageClient storageClient = new StorageClient(trackerServer, storeStorage);
        String orginalFilename = "C:\\Users\\Administrator\\Pictures\\Camera Roll\\nba1.jpg";
        String[] jpgs = storageClient.upload_file(orginalFilename, "jpg", null);
        for (int i = 0; i < jpgs.length; i++) {
            String jpg = jpgs[i];
            System.out.println("jpg=" + jpg);
        }
    }

}
