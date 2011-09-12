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

@SuppressWarnings("serial")
public class CommonRuntimeException extends RuntimeException {
    private final String errorId;
    private final Object[] args;

    public CommonRuntimeException(String errorId, Throwable e, Object... args) {
        super(ExceptionConfig.getMessage(errorId, args), e);
        this.errorId = errorId;
        this.args = args;
    }

    public CommonRuntimeException(String errorId, Object... args) {
        super(ExceptionConfig.getMessage(errorId, args));
        this.errorId = errorId;
        this.args = args;
    }

    public String getErrorId() {
        return errorId;
    }

    public Object[] getArgs() {
        return args;
    }

}
