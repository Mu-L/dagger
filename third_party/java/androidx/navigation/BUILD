# Copyright (C) 2025 The Dagger Authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

load("@rules_java//java:defs.bzl", "java_library")

# BUILD rules for androidx.navigation

package(default_visibility = ["//:src"])

java_library(
    name = "navigation",
    exports = [
        "@maven//:androidx_navigation_navigation_common",
        "@maven//:androidx_navigation_navigation_fragment",
        "@maven//:androidx_navigation_navigation_runtime",
    ],
)
