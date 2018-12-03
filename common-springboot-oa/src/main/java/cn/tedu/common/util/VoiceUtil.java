package cn.tedu.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;

import javazoom.jl.player.Player;

/**
 * 需要联网，调用的百度接口。
 * 调用方法名是play
 * 需要传入Sting字符串，即语音输出内容
 * spd	String	语速，取值0-15，默认为5中语速
 * pit	String	音调，取值0-15，默认为5中语调
 * per	String	发音人选择, 0为女声，1为男声，
	3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
 * @author hehong
 *
 */
public class VoiceUtil {
	private String filename;
    private Player player;
    public VoiceUtil(){}
    public VoiceUtil(String filename) {
        this.filename = filename;
    }
    public void play() {
        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
            player = new Player(buffer);
            player.play();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
  //设置APPID/AK/SK
    public static final String APP_ID = "14929735";
    public static final String API_KEY = "v5EVRqWdj5g2DvWUa394hpi4";
    public static final String SECRET_KEY = "xlWfwBdTxrscAG9bpy8IineG4eYzjfrM";
    /**
     * 调用方法
     * @param string 语音播放内容
     */
    public static void play(String string,String spd,String pit,String per){
    	AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
    	HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", spd);
        options.put("pit", pit);
        //发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
        options.put("per", per);
        // 调用接口
        TtsResponse res = client.synthesis(string, "zh", 1, options);
//        TtsResponse res = client.synthesis("醉经好房人hello", "zh", 1, null);
        byte[] data = res.getData();//语音文件字节
        JSONObject res1 = res.getResult();
        String add=System.getProperty("user.dir")+"\\output.mp3";
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data,add);
                // 调用接口
//                JSONObject res2 = client.asr(data, "pcm", 16000, null);
//                System.out.println(res2.toString(2));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }
        VoiceUtil mp3 = new VoiceUtil(add);
        mp3.play(); 
    }
}
