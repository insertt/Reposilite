/*
 * Copyright (c) 2020 Dzikoysk
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

package org.panda_lang.nanomaven.repository;

import org.panda_lang.utilities.commons.text.ContentJoiner;

import java.io.File;

public final class RepositoryUtils {

    private RepositoryUtils() { }

    public static File toRequestedFile(Repository repository, String[] requestPath) {
        return new File(repository.getLocalPath() + File.separator + ContentJoiner.on(File.separator).join(requestPath));
    }

}
