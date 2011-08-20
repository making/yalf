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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import am.ik.yalf.logger.LoggerAdapter;

public class LoggerAdapterImpl implements LoggerAdapter {
    public Object getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public Object getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    public boolean isFatalEnabled(Object logger) {
        return ((Logger) logger).isErrorEnabled();
    }

    public void fatal(Object logger, String message) {
        ((Logger) logger).error(message);
    }

    public void fatal(Object logger, String message, Throwable throwable) {
        ((Logger) logger).error(message, throwable);
    }

    public boolean isErrorEnabled(Object logger) {
        return ((Logger) logger).isErrorEnabled();
    }

    public void error(Object logger, String message) {
        ((Logger) logger).error(message);
    }

    public void error(Object logger, String message, Throwable throwable) {
        ((Logger) logger).error(message, throwable);
    }

    public boolean isWarnEnabled(Object logger) {
        return ((Logger) logger).isWarnEnabled();
    }

    public void warn(Object logger, String message) {
        ((Logger) logger).warn(message);
    }

    public void warn(Object logger, String message, Throwable throwable) {
        ((Logger) logger).warn(message, throwable);
    }

    public boolean isInfoEnabled(Object logger) {
        return ((Logger) logger).isInfoEnabled();
    }

    public void info(Object logger, String message) {
        ((Logger) logger).info(message);
    }

    public void info(Object logger, String message, Throwable throwable) {
        ((Logger) logger).info(message, throwable);
    }

    public boolean isDebugEnabled(Object logger) {
        return ((Logger) logger).isDebugEnabled();
    }

    public void debug(Object logger, String message) {
        ((Logger) logger).debug(message);
    }

    public void debug(Object logger, String message, Throwable throwable) {
        ((Logger) logger).debug(message, throwable);
    }

    public boolean isTraceEnabled(Object logger) {
        return ((Logger) logger).isTraceEnabled();
    }

    public void trace(Object logger, String message) {
        ((Logger) logger).trace(message);
    }

    public void trace(Object logger, String message, Throwable throwable) {
        ((Logger) logger).trace(message, throwable);
    }
}
