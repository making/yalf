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

import am.ik.yalf.logger.LoggerAdapter;

public class LoggerAdapterImpl implements LoggerAdapter {
    public Object getLogger(Class<?> clazz) {
        return clazz.getName();
    }

    public Object getLogger(String name) {
        return name;
    }

    protected void log(String name, String message) {
        System.err.printf("[%s] %s%n", name, message);
    }

    protected void log(String name, String message, Throwable throwable) {
        System.err.printf("[%s] %s%n", name, message);
        throwable.printStackTrace();
    }

    public boolean isFatalEnabled(Object logger) {
        return true;
    }

    public void fatal(Object logger, String message) {
        log((String) logger, message);
    }

    public void fatal(Object logger, String message, Throwable throwable) {
        log((String) logger, message, throwable);
    }

    public boolean isErrorEnabled(Object logger) {
        return true;
    }

    public void error(Object logger, String message) {
        log((String) logger, message);
    }

    public void error(Object logger, String message, Throwable throwable) {
        log((String) logger, message, throwable);
    }

    public boolean isWarnEnabled(Object logger) {
        return true;
    }

    public void warn(Object logger, String message) {
        log((String) logger, message);
    }

    public void warn(Object logger, String message, Throwable throwable) {
        log((String) logger, message, throwable);
    }

    public boolean isInfoEnabled(Object logger) {
        return true;
    }

    public void info(Object logger, String message) {
        log((String) logger, message);
    }

    public void info(Object logger, String message, Throwable throwable) {
        log((String) logger, message, throwable);
    }

    public boolean isDebugEnabled(Object logger) {
        return true;
    }

    public void debug(Object logger, String message) {
        log((String) logger, message);
    }

    public void debug(Object logger, String message, Throwable throwable) {
        log((String) logger, message, throwable);
    }

    public boolean isTraceEnabled(Object logger) {
        return true;
    }

    public void trace(Object logger, String message) {
        log((String) logger, message);
    }

    public void trace(Object logger, String message, Throwable throwable) {
        log((String) logger, message, throwable);
    }
}
