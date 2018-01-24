/*
 * Copyright (C) 2004-2008 Jive Software. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.k.engine.cache.exception;

/**
 * <p>Flags an exception when we cannot determine size of the object to be cached.</p>
 *
 * @author Marcin Cieslak
 */
public class CannotCalculateSizeException extends Exception {

	private static final long serialVersionUID = 935913027389788458L;

	public CannotCalculateSizeException() {
    }

    public CannotCalculateSizeException(Object obj) {
        super("Unable to determine size of " + obj.getClass() + " instance");
    }
}
