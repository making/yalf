/*
 * Copyright (C) 2011 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package am.ik.yalf.logger.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import am.ik.yalf.logger.LoggerAdapter;

public class LoggerAdapterImpl implements LoggerAdapter {
    public Object getLogger(Class<?> clazz) {
        return LogFactory.getLog(clazz);
    }

    public Object getLogger(String name) {
        return LogFactory.getLog(name);
    }

    public boolean isFatalEnabled(Object logger) {
        return ((Log) logger).isFatalEnabled();
    }

    public void fatal(Object logger, String message) {
        ((Log) logger).fatal(message);
    }

    public void fatal(Object logger, String message, Throwable throwable) {
        ((Log) logger).fatal(message, throwable);
    }

    public boolean isErrorEnabled(Object logger) {
        return ((Log) logger).isErrorEnabled();
    }

    public void error(Object logger, String message) {
        ((Log) logger).error(message);
    }

    public void error(Object logger, String message, Throwable throwable) {
        ((Log) logger).error(message, throwable);
    }

    public boolean isWarnEnabled(Object logger) {
        return ((Log) logger).isWarnEnabled();
    }

    public void warn(Object logger, String message) {
        ((Log) logger).warn(message);
    }

    public void warn(Object logger, String message, Throwable throwable) {
        ((Log) logger).warn(message, throwable);
    }

    public boolean isInfoEnabled(Object logger) {
        return ((Log) logger).isInfoEnabled();
    }

    public void info(Object logger, String message) {
        ((Log) logger).info(message);
    }

    public void info(Object logger, String message, Throwable throwable) {
        ((Log) logger).info(message, throwable);
    }

    public boolean isDebugEnabled(Object logger) {
        return ((Log) logger).isDebugEnabled();
    }

    public void debug(Object logger, String message) {
        ((Log) logger).debug(message);
    }

    public void debug(Object logger, String message, Throwable throwable) {
        ((Log) logger).debug(message, throwable);
    }

    public boolean isTraceEnabled(Object logger) {
        return ((Log) logger).isTraceEnabled();
    }

    public void trace(Object logger, String message) {
        ((Log) logger).trace(message);
    }

    public void trace(Object logger, String message, Throwable throwable) {
        ((Log) logger).trace(message, throwable);
    }
}
