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
#
# Description:
#  Builds and run tests related to AliasOfProcessor.

load("//java/dagger/testing/compile:macros.bzl", "compiler_test")

compiler_test(
    name = "BindValueErrorsTest",
    srcs = ["BindValueErrorsTest.java"],
    compiler_deps = [
        "//hilt-android-testing/main/java/dagger/hilt/android/testing:hilt_android_test",
        "//hilt-android-testing/main/java/dagger/hilt/android/testing:bind_value",
        "//third_party/java/android/android_sdk_linux:android_runtime",
        "//third_party/java/jakarta_inject",
    ],
    deps = [
        "//dagger-compiler/main/java/dagger/internal/codegen/xprocessing:xprocessing-testing",
        "//hilt-android-testing/main/java/dagger/hilt/android/testing/compile",
        "//hilt-compiler/main/java/dagger/hilt/android/processor/internal/bindvalue:bind_value_processor_lib",
        "//third_party/java/compile_testing",
        "//third_party/java/junit",
    ],
)
