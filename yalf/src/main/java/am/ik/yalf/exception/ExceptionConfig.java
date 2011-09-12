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

package am.ik.yalf.exception;

import java.util.Locale;

import am.ik.yalf.message.MessageManager;

public class ExceptionConfig {
    private static final String CONFIG_FILE = "META-INF/yalf.properties";
    protected static final MessageManager MESSAGE_MANAGER = new MessageManager(
            CONFIG_FILE);
    private static final ThreadLocal<Locale> locale = new ThreadLocal<Locale>();

    public static Locale getLocale() {
        return locale.get();
    }

    public static void setLocale(Locale locale) {
        ExceptionConfig.locale.set(locale);
    }

    public static String getMessage(String errorId, Object... args) {
        return MESSAGE_MANAGER.getMessage(false, errorId, getLocale(), args);
    }
}
