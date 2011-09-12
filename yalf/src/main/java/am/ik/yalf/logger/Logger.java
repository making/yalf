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

package am.ik.yalf.logger;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import am.ik.yalf.logger.impl.JulLoggerAdapter;
import am.ik.yalf.logger.impl.LoggerAdapterImpl;
import am.ik.yalf.message.MessageManager;

public class Logger {
    private final LoggerAdapter adapter = createAdapter();
    private final Object logger;
    private static final String CONFIG_FILENAME = "META-INF/yalf.properties";
    private static final ThreadLocal<Locale> locale = new ThreadLocal<Locale>();
    private static final AtomicBoolean adapterImplNotFound = new AtomicBoolean(
            false);
    protected static final MessageManager MESSAGE_MANAGER = new MessageManager(
            CONFIG_FILENAME);

    private LoggerAdapter createAdapter() {
        if (adapterImplNotFound.get()) {
            return new JulLoggerAdapter();
        } else {
            try {
                return new LoggerAdapterImpl();
            } catch (NoClassDefFoundError e) {
                adapterImplNotFound.set(true);
                LoggerAdapter adapter = new JulLoggerAdapter();
                Object logger = adapter.getLogger(Logger.class);
                if (adapter.isWarnEnabled(logger)) {
                    adapter.warn(
                            logger,
                            "am.ik.yalf.logger.impl.LoggerAdapterImpl is not found. use am.ik.yalf.logger.impl.JulLoggerAdapter instead of it.");
                }
                return adapter;
            }
        }
    }

    protected Logger(Class<?> clazz) {
        logger = adapter.getLogger(clazz);
    }

    protected Logger(String name) {
        logger = adapter.getLogger(name);
    }

    public static void setLocale(Locale locale) {
        Logger.locale.set(locale);
    }

    protected String createMessage(boolean resource, String logIdOrPattern,
            Object... args) {
        return MESSAGE_MANAGER.getMessage(resource, logIdOrPattern,
                locale.get(), args);
    }

    public static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz);
    }

    public static Logger getLogger(String name) {
        return new Logger(name);
    }

    public boolean isFatalEnabled() {
        return adapter.isFatalEnabled(logger);
    }

    public void fatal(boolean resource, String logIdOrPattern, Object... args) {
        if (isFatalEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.fatal(logger, message);
        }
    }

    public void fatal(boolean resource, String logIdOrPattern,
            Throwable throwable, Object... args) {
        if (isFatalEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.fatal(logger, message, throwable);
        }
    }

    public void fatal(String logId, Object... args) {
        fatal(true, logId, args);
    }

    public void fatal(String logId, Throwable throwable, Object... args) {
        fatal(true, logId, throwable, args);
    }

    public boolean isErrorEnabled() {
        return adapter.isErrorEnabled(logger);
    }

    public void error(boolean resource, String logIdOrPattern, Object... args) {
        if (isErrorEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.error(logger, message);
        }
    }

    public void error(boolean resource, String logIdOrPattern,
            Throwable throwable, Object... args) {
        if (isErrorEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.error(logger, message, throwable);
        }
    }

    public void error(String logId, Object... args) {
        error(true, logId, args);
    }

    public void error(String logId, Throwable throwable, Object... args) {
        error(true, logId, throwable, args);
    }

    public boolean isWarnEnabled() {
        return adapter.isWarnEnabled(logger);
    }

    public void warn(boolean resource, String logIdOrPattern, Object... args) {
        if (isWarnEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.warn(logger, message);
        }
    }

    public void warn(boolean resource, String logIdOrPattern,
            Throwable throwable, Object... args) {
        if (isWarnEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.warn(logger, message, throwable);
        }
    }

    public void warn(String logId, Object... args) {
        warn(true, logId, args);
    }

    public void warn(String logId, Throwable throwable, Object... args) {
        warn(true, logId, throwable, args);
    }

    public boolean isInfoEnabled() {
        return adapter.isInfoEnabled(logger);
    }

    public void info(boolean resource, String logIdOrPattern, Object... args) {
        if (isInfoEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.info(logger, message);
        }
    }

    public void info(boolean resource, String logIdOrPattern,
            Throwable throwable, Object... args) {
        if (isInfoEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.info(logger, message, throwable);
        }
    }

    public void info(String logId, Object... args) {
        info(true, logId, args);
    }

    public void info(String logId, Throwable throwable, Object... args) {
        info(true, logId, throwable, args);
    }

    public boolean isDebugEnabled() {
        return adapter.isDebugEnabled(logger);
    }

    public void debug(boolean resource, String logIdOrPattern, Object... args) {
        if (isDebugEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.debug(logger, message);
        }
    }

    public void debug(boolean resource, String logIdOrPattern,
            Throwable throwable, Object... args) {
        if (isDebugEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.debug(logger, message, throwable);
        }
    }

    public void debug(String logId, Object... args) {
        debug(true, logId, args);
    }

    public void debug(String logId, Throwable throwable, Object... args) {
        debug(true, logId, throwable, args);
    }

    public boolean isTraceEnabled() {
        return adapter.isTraceEnabled(logger);
    }

    public void trace(boolean resource, String logIdOrPattern, Object... args) {
        if (isTraceEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.trace(logger, message);
        }
    }

    public void trace(boolean resource, String logIdOrPattern,
            Throwable throwable, Object... args) {
        if (isTraceEnabled()) {
            String message = createMessage(resource, logIdOrPattern, args);
            adapter.trace(logger, message, throwable);
        }
    }

    public void trace(String logId, Object... args) {
        trace(true, logId, args);
    }

    public void trace(String logId, Throwable throwable, Object... args) {
        trace(true, logId, throwable, args);
    }

    public void log(String logId, Object... args) {
        if (logId != null && logId.length() > 0) {
            char messageType = logId.charAt(0);
            switch (messageType) {
            case 'T':
                trace(logId, args);
                break;
            case 'D':
                debug(logId, args);
                break;
            case 'I':
                info(logId, args);
                break;
            case 'W':
                warn(logId, args);
                break;
            case 'E':
                error(logId, args);
                break;
            case 'F':
                fatal(logId, args);
                break;
            default:
                debug(logId, args);
                break;
            }
        }
    }

    public void log(String logId, Throwable throwable, Object... args) {
        if (logId != null && logId.length() > 0) {
            char messageType = logId.charAt(0);
            switch (messageType) {
            case 'T':
                trace(logId, throwable, args);
                break;
            case 'D':
                debug(logId, throwable, args);
                break;
            case 'I':
                info(logId, throwable, args);
                break;
            case 'W':
                warn(logId, throwable, args);
                break;
            case 'E':
                error(logId, throwable, args);
                break;
            case 'F':
                fatal(logId, throwable, args);
                break;
            default:
                debug(logId, throwable, args);
                break;
            }
        }
    }
}
