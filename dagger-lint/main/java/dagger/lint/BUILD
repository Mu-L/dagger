# Copyright (C) 2020 The Dagger Authors.
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

# Description:
#   Dagger Lint Rules

load("@io_bazel_rules_kotlin//kotlin:jvm.bzl", "kt_jvm_library")
load("//:build_defs.bzl", "POM_VERSION")

package(default_visibility = ["//:src"])

kt_jvm_library(
    name = "lint-artifact-lib",
    srcs = glob(["*.kt"]),
    tags = ["maven_coordinates=com.google.dagger:dagger-lint:" + POM_VERSION],
    deps = [
        "//third_party/java/auto:service",
        "@maven//:com_android_tools_external_com_intellij_intellij_core",
        "@maven//:com_android_tools_external_com_intellij_kotlin_compiler",
        "@maven//:com_android_tools_external_org_jetbrains_uast",
        "@maven//:com_android_tools_lint_lint",
        "@maven//:com_android_tools_lint_lint_api",
    ],
)

# Current `kt_jvm_library` does not output source jars and gen_maven_artifact expects one.
# See: https://github.com/bazelbuild/rules_kotlin/issues/324
genrule(
    name = "dagger-lint-sources",
    srcs = glob(["*.kt"]),
    outs = ["liblint-artifact-lib-src.jar"],
    cmd = """
        TEMP="$$(mktemp -d)"
        for file in $(SRCS); do
            filename="$$TEMP/$${file#java/}"
            mkdir -p `dirname $$filename` && cp $$file $$filename
        done
        jar cf $@ -C $$TEMP .
    """,
)
