package cn.tedu.sp06.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {
    //设置过滤器的类型： pre、routing、post、error
    @Override
    public String filterType() {
        // return "pre";  两种方法都可以
        return FilterConstants.PRE_TYPE;
    }

    //位置序号
    @Override
    public int filterOrder() {
        //
        return 6;
    }
    //判断针对当前请求，是否执行过滤代码
    @Override
    public boolean shouldFilter() {
        /*
        调用商品判断权限，
        调用用户和订单，不检查权限
         */
        //获得调用的服务id
        RequestContext ctx = RequestContext.getCurrentContext();
        String serviceID = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        return "item-service".equals(serviceID);
    }

    //过滤代码
    @Override
    public Object run() throws ZuulException {
        //判断请求路径中是否携带token，有则放行，没有则阻止继续访问

        //获得request对象
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //接收 token参数
        String token = request.getParameter("token");

        // 如果没有 token，阻止继续访问
        if (StringUtils.isAllBlank(token)){
            //阻止访问
            ctx.setSendZuulResponse(false);
            //直接返回响应
            ctx.addZuulRequestHeader("Content-type","text/html;charset=utf8");
            ctx.setResponseBody("Not Login! 未登录!");
        }
        //这个返回值不起作用，返回任何数据都可以
        return null;


    }
}
