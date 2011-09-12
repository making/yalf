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

package am.ik.yalf.message;

import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageManager {
    protected final List<String> basenames = new CopyOnWriteArrayList<String>();

    protected String messageIdFormat = "[%s] ";

    protected boolean throwIfResourceNotFound = false;

    protected static final String DEFAULT_MESSAGE_ID_FORMAT_KEY = "message.id.format";

    protected static final String DEFAULT_MESSAGE_BASE_NAME_KEY = "message.basename";

    protected static final String DEFAULT_THROW_IF_RESOURCE_NOT_FOUND_KEY = "throw.if.resource.not.found";

    protected static ClassLoader getClassLoader() {
        ClassLoader contextClassLoader = Thread.currentThread()
                .getContextClassLoader();
        if (contextClassLoader != null) {
            return contextClassLoader;
        }
        ClassLoader thisClassLoader = MessageManager.class.getClassLoader();
        return thisClassLoader;
    }

    public MessageManager(String configFile) {
        this(configFile, DEFAULT_MESSAGE_ID_FORMAT_KEY,
                DEFAULT_MESSAGE_BASE_NAME_KEY,
                DEFAULT_THROW_IF_RESOURCE_NOT_FOUND_KEY);
    }

    public MessageManager(String configFile, String messageIdFormatKey,
            String messageBaseNameKey, String throwIfResourceNotFoundKey) {
        try {
            ClassLoader cl = getClassLoader();
            {
                String format = null;
                String throwIfNotFound = null;
                // messageIdFormat,throwIfResourceNotFoundはクラスローダで優先度の高いもの
                InputStream strm = cl.getResourceAsStream(configFile);
                if (strm != null) {
                    Properties p = new Properties();
                    p.load(strm);
                    format = p.getProperty(messageIdFormatKey);
                    throwIfNotFound = p.getProperty(throwIfResourceNotFoundKey);
                }
                if (format != null) {
                    messageIdFormat = format;
                }
                if (throwIfNotFound != null) {
                    throwIfResourceNotFound = Boolean
                            .parseBoolean(throwIfNotFound);
                }
            }

            Enumeration<URL> urls = cl.getResources(configFile);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    Properties p = new Properties();
                    InputStream strm = url.openStream();
                    p.load(strm);
                    // messageBasenameはクラスローダから読み込めるものは全て追加する
                    if (p.containsKey(messageBaseNameKey)) {
                        String[] basenameArray = p.getProperty(
                                messageBaseNameKey).split(",");
                        for (String s : basenameArray) {
                            String basename = s.trim();
                            if (!"".equals(basename)) {
                                basenames.add(basename);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new MessageRuntimeException(e);
        }
    }

    protected ResourceBundle getResourceBundle(String basename, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }

        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle(basename, locale);
        } catch (MissingResourceException e) {
            if (throwIfResourceNotFound) {
                StringBuilder sb = new StringBuilder("resource[").append(
                        basename).append("] is not found");
                throw new MessageRuntimeException(sb.toString(), e);
            }
        }
        return bundle;
    }

    protected String getStringOrNull(ResourceBundle bundle, String key) {
        if (bundle == null) {
            return null;
        }
        if (key == null) {
            if (throwIfResourceNotFound) {
                throw new MessageRuntimeException("key is null");
            }
            return null;
        }

        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            if (throwIfResourceNotFound) {
                StringBuilder sb = new StringBuilder("key[").append(key)
                        .append("] is not found");
                throw new MessageRuntimeException(sb.toString(), e);
            }
            return null;
        }
    }

    protected String getMessagePattern(String messageId, Locale locale) {
        String message = null;
        for (String basename : basenames) {
            ResourceBundle bundle = getResourceBundle(basename, locale);
            message = getStringOrNull(bundle, messageId);
            if (message != null) {
                break;
            }
        }
        return message;
    }

    public String getMessage(boolean resource, String messageIdOrPattern,
            Object... args) throws MessageRuntimeException {
        return getMessage(resource, messageIdOrPattern, null, args);
    }

    public String getMessage(boolean resource, String messageIdOrPattern,
            Locale locale, Object... args) throws MessageRuntimeException {
        String pattern = null;
        StringBuilder message = new StringBuilder();

        if (resource) {
            pattern = getMessagePattern(messageIdOrPattern, locale);
            message.append(String.format(messageIdFormat, messageIdOrPattern));
        } else {
            pattern = messageIdOrPattern;
        }

        if (pattern != null) {
            try {
                String body = MessageFormat.format(pattern, args);
                message.append(body);
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder(
                        "message pattern is illeagal. pattern=")
                        .append(pattern).append("]");
                if (resource) {
                    sb.append(" logId=");
                    sb.append(messageIdOrPattern);
                }
                throw new MessageRuntimeException(sb.toString(), e);
            }
        }
        return message.toString();
    }
}
