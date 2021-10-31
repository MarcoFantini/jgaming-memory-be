package it.jpanik.jgaming.conf;

import it.jpanik.jgaming.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author Jacopo Korompay
 */
@Component
public class HttpHandshakeInterceptor implements HandshakeInterceptor {

  private final JwtTokenProvider jwtTokenProvider;

  @Autowired
  public HttpHandshakeInterceptor(final JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public boolean beforeHandshake(
      ServerHttpRequest request,
      ServerHttpResponse response,
      WebSocketHandler wsHandler,
      Map attributes) {
    ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
    String token = servletRequest.getServletRequest().getQueryString();
    token = token.substring(token.indexOf('=') + 1);
    return jwtTokenProvider.validateToken(token);
  }

  @Override
  public void afterHandshake(
      ServerHttpRequest serverHttpRequest,
      ServerHttpResponse serverHttpResponse,
      WebSocketHandler webSocketHandler,
      Exception e) {}
}
