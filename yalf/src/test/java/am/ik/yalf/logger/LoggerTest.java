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

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoggerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLog01() {
        Logger logger = Logger.getLogger(Logger.class);
        LoggerTestUtil.testLog(logger);
    }

    @Test
    public void testLog02() {
        Logger logger = Logger.getLogger(Logger.class);
        logger.log("TRA001");
        logger.log("DEB001");
        logger.log("INF001");
        logger.log("WAR001");
        logger.log("ERR001");
        logger.log("FAT001");
    }

    @Test
    public void testCreateMessage01() {
        Logger logger = Logger.getLogger(Logger.class);
        assertEquals("hoge", logger.createMessage(false, "hoge"));
        assertEquals("a b", logger.createMessage(false, "{0} {1}", "a", "b"));
    }

    @Test
    public void testCreateMessage02() throws Exception {
        Logger logger = Logger.getLogger(Logger.class);
        Logger.setLocale(Locale.ENGLISH);
        Field f = Logger.class.getDeclaredField("logIdFormat");
        f.setAccessible(true);
        String logIdFormat = (String) f.get(Logger.class);
        String logId = String.format(logIdFormat, "ERR001");
        assertEquals(logId + "error1", logger.createMessage(true, "ERR001"));
        Logger.setLocale(Locale.getDefault());
        assertEquals(logId + "エラーメッセージ1", logger.createMessage(true, "ERR001"));
    }
}
