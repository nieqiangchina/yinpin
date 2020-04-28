package yinpin;


import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;


public class Synthesis {
    public static final String APP_ID = "19612579";
    public static final String API_KEY = "eqPDwGfgfXKcpg3OfjOOg0YD";
    public static final String SECRET_KEY = "j4WGunfAYd5a8wiEvDIyZRGN60TQASLe";
    public static void main(String[] args) throws IOException {

        File file = new File("E:\\workspace\\pcm");
        file.createNewFile();
        list(file);

//        调用接口   这里放置对应的后缀的.pcm音频文件以及本机pcm下载路径
//        String path = "E:\\workspace\\yinpin\\16k_test.pcm";
//        JSONObject res = client.asr(path, "pcm", 16000, null);
//        System.out.println(path+"的解析结果是:"+res.toString(2));
    }

    //获取指定目录下pcm文件名称
    public static void list(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isFile() && file2.getName().endsWith(".pcm")) {
                call(file2.getPath());
            }
        }
    }

    //调用百度API
    public static void call(String path){
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        // System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

//        调用接口   这里放置对应的后缀的.pcm音频文件以及本机pcm下载路径
//        String path = "E:\\workspace\\yinpin\\16k_test.pcm";
        JSONObject res = client.asr(path, "pcm", 16000, null);

        //获取value,插入sql
        Yuyin yy = new Yuyin();
        yy.setPath(path);
        yy.setResult(res.get("result").toString());
        yy.setErr_msg(res.get("err_msg").toString());
        yy.setSn(res.get("sn").toString());
        yy.setCorpus_no(res.get("corpus_no").toString());
        yy.setErr_no(res.get("err_no").toString());

//        System.out.println("path名称是："+yy.getPath());
//        System.out.println("result名称是："+yy.getResult());
//        System.out.println("##############################");
        OperationPaper operationPaper = new OperationPaper();
        operationPaper.addYuyin(yy);
    }
}
