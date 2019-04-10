import com.alibaba.fastjson.JSONObject;
import com.xigua.Application;
import com.xigua.server.UrlServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class XiguaTest {
    @Test
    public void contextLoads() {
    }

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    @Autowired
    private UrlServer server;
    @Test
    public void test1(){
        server.queryAll();
    }

    @Test
    public void testCreateShorturl() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("url", "www.zhihu.com");
        MvcResult result = mockMvc.perform(post("/getShortURL?url=www.zhihu.com").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is(302))// 模拟向testRest发送get请求
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());

    }
    @Test
    public void testgoUrl() throws Exception {
        Map<String, Object> map = new HashMap<>();
       // map.put("url", "www.zhihu.com");
        MvcResult result = mockMvc.perform(post("/url/daff46"))
                .andExpect(status().is(302))// 模拟向testRest发送get请求
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());

    }

}
