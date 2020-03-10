package demo.support;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

public class P6SpyLogger implements MessageFormattingStrategy {
/**  spy.properties
    logMessageFormat=com.p6spy.engine.spy.appender.CustomLineFormat
    customLogMessageFormat=**/
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNoneEmpty(sql) ?
          "[ " + LocalDateTime.now() + " ] --- | took "
                + elapsed + "ms | " + category + " | connection " + connectionId + "\n "
                + sql +";" : "";
    }
}
