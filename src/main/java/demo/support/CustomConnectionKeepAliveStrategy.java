package demo.support;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.util.Arrays;

/**
 * DefaultConnectionKeepAliveStrategy
 */
public class CustomConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {
    @Override
    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return Arrays.asList(httpResponse.getHeaders(HTTP.CONN_KEEP_ALIVE))
                .stream().filter(
                        header -> StringUtils.equalsIgnoreCase(header.getName(),"timeout")
                ).findFirst()
                .map(header -> NumberUtils.toLong(header.getValue(),30l))
                .orElse(30000l);
    }
}
