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

import java.util.logging.Level;
import java.util.logging.Logger;

import am.ik.yalf.logger.LoggerAdapter;

public class JulLoggerAdapter implements LoggerAdapter {

    private String sourceClass;

    public Object getLogger(Class<?> clazz) {
        sourceClass = clazz.getName();
        return Logger.getLogger(clazz.getName());
    }

    public Object getLogger(String name) {
        sourceClass = name;
        return Logger.getLogger(name);
    }

    public boolean isFatalEnabled(Object logger) {
        return ((Logger) logger).isLoggable(Level.SEVERE);
    }

    public void fatal(Object logger, String message) {
        ((Logger) logger).logp(Level.SEVERE, sourceClass, null, message);
    }

    public void fatal(Object logger, String message, Throwable throwable) {
        ((Logger) logger).logp(Level.SEVERE, sourceClass, null, message,
                throwable);
    }

    public boolean isErrorEnabled(Object logger) {
        return ((Logger) logger).isLoggable(Level.SEVERE);
    }

    public void error(Object logger, String message) {
        ((Logger) logger).logp(Level.SEVERE, sourceClass, null, message);
    }

    public void error(Object logger, String message, Throwable throwable) {
        ((Logger) logger).logp(Level.SEVERE, sourceClass, null, message,
                throwable);
    }

    public boolean isWarnEnabled(Object logger) {
        return ((Logger) logger).isLoggable(Level.WARNING);
    }

    public void warn(Object logger, String message) {
        ((Logger) logger).logp(Level.WARNING, sourceClass, null, message);
    }

    public void warn(Object logger, String message, Throwable throwable) {
        ((Logger) logger).logp(Level.WARNING, sourceClass, null, message,
                throwable);
    }

    public boolean isInfoEnabled(Object logger) {
        return ((Logger) logger).isLoggable(Level.INFO);
    }

    public void info(Object logger, String message) {
        ((Logger) logger).logp(Level.INFO, sourceClass, null, message);
    }

    public void info(Object logger, String message, Throwable throwable) {
        ((Logger) logger).logp(Level.INFO, sourceClass, null, message,
                throwable);
    }

    public boolean isDebugEnabled(Object logger) {
        return ((Logger) logger).isLoggable(Level.FINE);
    }

    public void debug(Object logger, String message) {
        ((Logger) logger).logp(Level.FINE, sourceClass, null, message);
    }

    public void debug(Object logger, String message, Throwable throwable) {
        ((Logger) logger).logp(Level.FINE, sourceClass, null, message,
                throwable);
    }

    public boolean isTraceEnabled(Object logger) {
        return ((Logger) logger).isLoggable(Level.FINER);
    }

    public void trace(Object logger, String message) {
        ((Logger) logger).logp(Level.FINER, sourceClass, null, message);
    }

    public void trace(Object logger, String message, Throwable throwable) {
        ((Logger) logger).logp(Level.FINER, sourceClass, null, message,
                throwable);
    }

}
