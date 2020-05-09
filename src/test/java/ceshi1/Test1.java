package ceshi1;

import com.fourfaith.rongxin.controller.WxMessagesPush;
import com.fourfaith.rongxin.model.MessageVo;
import com.fourfaith.rongxin.model.Openid;
import com.fourfaith.rongxin.service.MessagesService;
import com.fourfaith.rongxin.controller.AccessTokenTimer;
import com.fourfaith.rongxin.utils.AlexUtil;
import com.fourfaith.rongxin.wechat.AzureToken;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml", "classpath:spring-redis.xml"})
public class Test1 {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void fun1() {
        SysUser admin = sysUserService.findByUserName("admin");
        System.out.println(admin);
    }

    String senurl = "http://api.feige.ee/SmsService/Send";

    @Test
    public void fun2() {
        SysUser admin = sysUserService.findByUserName("admin");
        System.out.println(admin);
    }

    @Test
    public void fun3() {
        List<MessageVo> getvo = messagesService.getvo(AlexUtil.getTableName(new Date()));
        List<Openid> getopenids = messagesService.getopenids();
        System.out.println(getopenids);
        System.out.println(getvo);
    }

    @Test
    public void fun5() {
        // stringRedisTemplate的操作
        // String读写
        redisTemplate.delete("myStr");
        redisTemplate.opsForValue().set("myStr", "skyLine");
        System.out.println(redisTemplate.opsForValue().get("myStr"));
        System.out.println("---------------");

        // List读写
        redisTemplate.delete("myList");
        redisTemplate.opsForList().rightPush("myList", "T");
        redisTemplate.opsForList().rightPush("myList", "L");
        redisTemplate.opsForList().leftPush("myList", "A");
        List<String> listCache = redisTemplate.opsForList().range("myList", 0, -1);
        for (String s : listCache) {
            System.out.println(s);
        }
        System.out.println("---------------");


    }


    @Test
    public void fun4() throws IOException {
        List<Openid> openids = new ArrayList<>();
        List<MessageVo> messageVo = new ArrayList<>();
        openids = messagesService.getopenids();
        messageVo = messagesService.getvo(AlexUtil.getTableName(new Date()));
        AzureToken token = new AccessTokenTimer().getWxAccessToken();
        if (messageVo != null && messageVo.size() > 0){
            for(MessageVo v : messageVo){
                for (Openid x : openids){
                    WxMessagesPush.pushmessage(v, token, x);
                    v.setUsername(x.getWxuser());
                    v.setPhone(x.getPhone());
                    v.setTm(new Date());
                    messagesService.addwxpushlog(v);
                }
            }
        }
    }

}
