package com.ccsu.jc.tvbank.api;

import com.alibaba.fastjson.JSONObject;
import com.ccsu.jc.tvbank.domain.req.MessageReq;
import com.ccsu.jc.tvbank.service.VideoService;
import com.ccsu.jc.tvbank.task.TestMain;
import com.ccsu.jc.tvbank.domain.*;
import com.ccsu.jc.tvbank.service.MessageService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/tv-bank")
public class VideoController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private VideoService videoService;


    @PostConstruct
    public void init() {
        System.out.println("我只会被调用一次");
        TestMain sf = new TestMain();
        sf.main(null);
    }


    @RequestMapping("/video")
    public String video(@RequestParam("dizhi") String dizhi,
                        @RequestParam("shipingID") String shipingID,
                        Model model) {
        // 根据视频ID查询出 此视频的所有留言
        List<MessageEntity> messagelist = messageService.messagelist(shipingID);
        model.addAttribute("messagelist", messagelist);
        model.addAttribute("dizhi", dizhi);
        model.addAttribute("shipingID", shipingID);
        return "video";
    }


    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "keyWord") String keyWord) {
        Map<String, Object> map = new HashMap<>(16);
        List<VideoEntity> videos = videoService.searchByName(keyWord);
        map.put("videos", videos);
        return new ModelAndView("searchList", map);
    }


    @GetMapping("/getByCategory")
    public ModelAndView getByCategory(@RequestParam("category") String category) {
        Map<String, Object> map = new HashMap<>(16);
        List<VideoEntity> videos = videoService.videoList(category);
        map.put("videos", videos);
        return new ModelAndView("searchList", map);
    }

    @PostMapping("/leaveMessage")
    @ResponseBody
    public String leaveMessage(@RequestBody MessageReq messageReq) {
        JSONObject returnJsonObj = new JSONObject();
        if (messageService.message(messageReq)) {
            returnJsonObj.put("code", 200);
        } else {
            returnJsonObj.put("code", -200);
        }
        return returnJsonObj.toJSONString();
    }


    /************************ 文件上传 ****************************************/

    @RequestMapping("/videoFileTop")
    public String videoFileTopView() {
        return "videoFileTop";
    }

    @PostMapping("/upLoadFile")
    @ResponseBody
    public String fileUpLoad(@RequestParam("files") MultipartFile[] files,
                             @RequestParam("biaoti") String biaoti,
                             @RequestParam("Fruit") String fruit,
                             @RequestParam("upName") String upName) {
        JSONObject returnJon = new JSONObject();

        //创建输入输出流
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            String videoBasePath = "/static/videolook/";
            String imgBasePath = "/static/videolook/videolookimg/";
            String windowVideoPath = "C:\\MyGIT\\tv-bank\\src\\main\\resources\\static\\videolook\\";
            String windowImgPath = "C:\\MyGIT\\tv-bank\\src\\main\\resources\\static\\videolook\\videolookimg\\";
            String videoPath = "";
            String imgPath = "";

            // 循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];

                //获取文件的输入流
                inputStream = file.getInputStream();
                //获取上传时的文件名
                String fileName = file.getOriginalFilename();
                //注意是路径+文件名
                File targetFile = new File((i == 0 ? windowVideoPath : windowImgPath) + fileName);

                //判断文件父目录是否存在
                if (!targetFile.getParentFile().exists()) {
                    //不存在就创建一个
                    targetFile.getParentFile().mkdir();
                }

                //获取文件的输出流
                outputStream = new FileOutputStream(targetFile);
                //最后使用资源访问器FileCopyUtils的copy方法拷贝文件
                FileCopyUtils.copy(inputStream, outputStream);

                if (i == 0) {
                    videoPath = videoBasePath + fileName;
                } else {
                    imgPath = imgBasePath + fileName;
                }
            }

            VideoEntity videoEntity = new VideoEntity();
            videoEntity.setUpName(upName);
            videoEntity.setCategoryName("");
            videoEntity.setVideoAddress(videoPath);
            videoEntity.setVideoCategory(fruit);
            videoEntity.setVideoID(UUID.randomUUID().toString());
            videoEntity.setVideoImage(imgPath);
            videoEntity.setVideoName(biaoti);
            videoEntity.setVideoState("1");

            videoService.insertVideo(videoEntity);

            //告诉页面上传成功了
            returnJon.put("code", 200);
        } catch (IOException e) {
            e.printStackTrace();
            returnJon.put("code", -200);
        } finally {
            //无论成功与否，都有关闭输入输出流
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnJon.toJSONString();
    }

}
