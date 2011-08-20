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

public interface LoggerAdapter {

    public Object getLogger(Class<?> clazz);

    public Object getLogger(String name);

    boolean isFatalEnabled(Object logger);

    void fatal(Object logger, String message);

    void fatal(Object logger, String message, Throwable throwable);

    boolean isErrorEnabled(Object logger);

    void error(Object logger, String message);

    void error(Object logger, String message, Throwable throwable);

    boolean isWarnEnabled(Object logger);

    void warn(Object logger, String message);

    void warn(Object logger, String message, Throwable throwable);

    boolean isInfoEnabled(Object logger);

    void info(Object logger, String message);

    void info(Object logger, String message, Throwable throwable);

    boolean isDebugEnabled(Object logger);

    void debug(Object logger, String message);

    void debug(Object logger, String message, Throwable throwable);

    boolean isTraceEnabled(Object logger);

    void trace(Object logger, String message);

    void trace(Object logger, String message, Throwable throwable);
}
