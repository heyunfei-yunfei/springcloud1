package cn.tedu.sp06.fallback;

import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

//FallbackProvider实现这个借口处理降级
@Component
public class ItemFB implements FallbackProvider {

    //指定针对哪个服务应用当前降级类
    //返回服务id
    /*
    item-service  只针对商品服务降级
    *- 对所有服务降级
    null- 对所有服务降级
     */
    @Override
    public String getRoute() {
        //只针对商品降级
        return "item-service";
    }
    //返回降级响应，封装在response对象中
    //根据自己应用的需求，可以返回任意的响应数据
    // -错误提示
    // -缓存数据
    // -执行业务运算返回结果
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR;  //500,internal error
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.value();  //500
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(); //internal error
            }

            //用来关闭资源
            @Override
            public void close() {
                //ByteArrayInputStream 流是内存数组的流，不占用底层系统资源
                //不需要 close（）释放资源
            }

            @Override
            public InputStream getBody() throws IOException {
                //JsonResult  ---{code:500,msg:"系统服务故障"，data：null}
                String json = JsonResult.err().code(500).msg("系统服务故障").toString();
                return new ByteArrayInputStream(json.getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders h = new HttpHeaders();
                h.add("Content-Type", "application/json;charset=UTF-8");
                return h;
            }
        };
    }
}
