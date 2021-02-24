package  com.xtt.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utils.JwtUtils;

import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public class AuthCheckFilter implements GlobalFilter,Ordered{
    private ObjectMapper objectMapper=new ObjectMapper();
    /**
     * 跳过拦截的请求
     */
    private String[] skipAuthUrls=new String[]{"/user/getEmailCode","/user/register","/user/login",
                        "/cgoods","/seller","/admin"};

    /**
     * 拦截器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url=exchange.getRequest().getURI().getPath();
        for(String skipurl:skipAuthUrls){
            if(skipurl.equals(url)||url.startsWith(skipurl)){
                return chain.filter(exchange);
            }
        }
        System.out.println("处理请求:"+url);
        String token=exchange.getRequest().getHeaders().getFirst("token");
        System.out.println("token："+token);
        if(token==null){
            return authErro(exchange.getResponse(), "请先登录！");
        }
        if(!JwtUtils.verify(token)){
            System.out.println("token身份认证");
            return authErro(exchange.getResponse(), "认证失败,请先登录！");
        }
        String userId=JwtUtils.getId(token);
        String role=JwtUtils.getRole(token);
        if(url.startsWith("/user/")){
            System.out.println("用户角色为"+role);
            //用户角色不是用户或管理员
            if((!"ROLE_USER".equals(role))&&!"ROLE_ADMIN".equals(role)){
                return authErro(exchange.getResponse(), "你没有权限访问");
            }
        }else if(url.startsWith("/goods/")){
            System.out.println("用户角色为"+role);
            if((!"ROLE_USER".equals(role))&&!"ROLE_ADMIN".equals(role)){
                return authErro(exchange.getResponse(), "你没有权限访问");
            }
        }
        return chain.filter(exchange);
    }

    /**
     * 优先级
     * @return
     */
    @Override
    public int getOrder() {
        return -100;
    }
    private Mono<Void> authErro(ServerHttpResponse resp, String mess) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        CommonResult commonResult=CommonResultVO.error(mess);
        JSONObject object= (JSONObject) JSONObject.toJSON(commonResult);
        byte[] bits=object.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = resp.bufferFactory().wrap(bits);
        return resp.writeWith(Mono.just(buffer));
    }

}