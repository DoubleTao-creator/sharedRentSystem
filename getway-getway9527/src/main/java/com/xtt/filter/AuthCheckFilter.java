package  com.xtt.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.CommonResult;
import entity.CommonResultVO;
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

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public class AuthCheckFilter implements GlobalFilter,Ordered{
    private ObjectMapper objectMapper=new ObjectMapper();
    private String[] skipAuthUrls=new String[]{"/user/register","/user/login",""};
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url=exchange.getRequest().getURI().getPath();
        System.out.println("处理请求:"+url);
        String token=exchange.getRequest().getHeaders().getFirst("token");
        System.out.println("token："+token);
        if(Arrays.asList(skipAuthUrls).contains(url)){
            return chain.filter(exchange);
        }
        if(token==null){
            return authErro(exchange.getResponse(), "请先登录！");
        }
        if(!JwtUtils.verify(token)){
            System.out.println("token身份认证");
            return authErro(exchange.getResponse(), "认证失败");
        }
        String userId=JwtUtils.getId(token);
        String role=JwtUtils.getRole(token);
        if(url.startsWith("/user/")){
            System.out.println("用户角色为"+role);
            if(role!="ROLE_USER"){
                return authErro(exchange.getResponse(), "你没有权限访问");
            }
        }
        return chain.filter(exchange);
    }

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