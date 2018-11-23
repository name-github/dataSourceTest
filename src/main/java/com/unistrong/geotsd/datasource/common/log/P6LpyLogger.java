package com.unistrong.geotsd.datasource.common.log;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by zc.shen on 2018/5/3.
 */
public class P6LpyLogger
        implements MessageFormattingStrategy
{
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        if (!sql.trim().equals("")) {
            return "----------# " + this.format.format(new Date()) + " | took " + elapsed + "ms | " + category + " | connection " + connectionId + "\n " + sql + ";";
        }

        return "";
    }
}
