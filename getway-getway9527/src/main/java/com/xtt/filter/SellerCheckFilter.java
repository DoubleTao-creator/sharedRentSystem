package com.xtt.filter;

import com.alibaba.fastjson.JSONObject;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class SellerCheckFilter implements GlobalFilter, Ordered {

    private String[] accessUrls = new String[]{"/cgoods","/admin","/user","/goods",
            "/seller/register","/seller/login"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url=exchange.getRequest().getURI().getPath();
        for(String accessUrl:accessUrls ){
            if(url.equals(accessUrl)||url.startsWith(accessUrl)){
                return chain.filter(exchange);
            }
        }
        System.out.println("SellerCheck: 处理请求:"+url);
        HttpCookie cookie = exchange.getRequest().getCookies().
                getFirst("sellerId");
        if(cookie!=null){
            System.out.println(cookie.getName()+"---"+cookie.getValue());
            return chain.filter(exchange);
        }else {
            return err(exchange.getResponse(),"请商家登录");
        }
    }

    @Override
    public int getOrder() {
        return -10;
    }

    private Mono<Void> err(ServerHttpResponse resp, String mess) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        CommonResult commonResult= CommonResultVO.error(mess);
        JSONObject object= (JSONObject) JSONObject.toJSON(commonResult);
        byte[] bits=object.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = resp.bufferFactory().wrap(bits);
        return resp.writeWith(Mono.just(buffer));
    }
}
