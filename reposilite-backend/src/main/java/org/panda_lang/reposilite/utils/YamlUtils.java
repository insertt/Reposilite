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

package org.panda_lang.reposilite.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.panda_lang.utilities.commons.FileUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

public final class YamlUtils {

    private static final Representer REPRESENTER = new Representer() {{
        this.getPropertyUtils().setSkipMissingProperties(true);
        this.setDefaultScalarStyle(DumperOptions.ScalarStyle.DOUBLE_QUOTED);
    }};

    private static final Yaml YAML = new Yaml(REPRESENTER);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private YamlUtils() { }

    public static <T> T forceLoad(File file, Class<T> type, Function<Map<String, Object>, Map<String, Object>> patches) {
        try {
            return load(file, type, patches);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load requested file", e);
        }
    }

    public static <T> T load(File file, Class<T> type, Function<Map<String, Object>, Map<String, Object>> patches) throws IOException {
        Map<String, Object> content = YAML.load(FileUtils.getContentOfFile(file));
        content = patches.apply(content);
        return OBJECT_MAPPER.convertValue(content, type);
    }

    public static <T> T load(File file, Class<T> type) throws IOException {
        return YAML.loadAs(FileUtils.getContentOfFile(file), type);
    }

    public static void save(File file, Object value) throws IOException {
        FileUtils.overrideFile(file, YAML.dump(value));
    }

}
