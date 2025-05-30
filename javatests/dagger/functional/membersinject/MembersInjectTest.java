/*
 * Copyright (C) 2015 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dagger.functional.membersinject;

import static com.google.common.truth.Truth.assertThat;

import dagger.BindsInstance;
import dagger.Component;
import dagger.MembersInjector;
import dagger.functional.membersinject.subpackage.a.AGrandchild;
import dagger.functional.membersinject.subpackage.a.AParent;
import dagger.functional.membersinject.subpackage.b.BChild;
import dagger.internal.Provider;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MembersInjectTest {
  @Test public void testMembersInject_arrays() {
    MembersInjectComponent component = DaggerMembersInjectComponent.builder().build();

    ChildOfStringArray childOfStringArray = new ChildOfStringArray();
    component.inject(childOfStringArray);
  }

  @Test public void testMembersInject_nestedArrays() {
    MembersInjectComponent component = DaggerMembersInjectComponent.builder().build();

    ChildOfArrayOfParentOfStringArray childOfArrayOfParentOfStringArray =
        new ChildOfArrayOfParentOfStringArray();
    component.inject(childOfArrayOfParentOfStringArray);
  }

  @Test public void testMembersInject_primitives() {
    MembersInjectComponent component = DaggerMembersInjectComponent.builder().build();

    ChildOfPrimitiveIntArray childOfPrimitiveIntArray = new ChildOfPrimitiveIntArray();
    component.inject(childOfPrimitiveIntArray);
  }

  @Test
  public void testMembersInject_overrides() {
    MembersInjectionVisibilityComponent component =
        DaggerMembersInjectionVisibilityComponent.create();
    AParent aParent = new AParent();
    component.inject(aParent);
    assertThat(aParent.aParentField()).isNotNull();
    assertThat(aParent.aParentMethod()).isNotNull();

    BChild aChild = new BChild();
    component.inject(aChild);
    assertThat(aChild.aParentField()).isNotNull();
    assertThat(aChild.aParentMethod()).isNull();
    assertThat(aChild.aChildField()).isNotNull();
    assertThat(aChild.aChildMethod()).isNotNull();

    AGrandchild aGrandchild = new AGrandchild();
    component.inject(aGrandchild);
    assertThat(aGrandchild.aParentField()).isNotNull();
    assertThat(aGrandchild.aParentMethod()).isNotNull();
    assertThat(aGrandchild.aChildField()).isNotNull();
    assertThat(aGrandchild.aChildMethod()).isNull();
    assertThat(aGrandchild.aGrandchildField()).isNotNull();
    assertThat(aGrandchild.aGrandchildMethod()).isNotNull();
  }

  @Test
  public void testNonRequestedMembersInjector() {
    NonRequestedChild child = new NonRequestedChild();
    Provider<String> provider =
        new Provider<String>() {
          @Override
          public String get() {
            return "field!";
          }
        };
    MembersInjector<NonRequestedChild> injector =
        NonRequestedChild_MembersInjector.create(provider);
    injector.injectMembers(child);
    assertThat(child.t).isEqualTo("field!");
  }

  public static final class A extends B {
    // No injected members
  }

  public static class B extends C {
    // No injected members
  }

  public static class C {
    @Inject String value;
  }

  @Component
  interface NonLocalMembersComponent {
    MembersInjector<A> getAMembersInjector();

    @Component.Factory
    interface Factory {
      NonLocalMembersComponent create(@BindsInstance String value);
    }
  }

  @Test
  public void testNonLocalMembersInjection() {
    MembersInjector<A> membersInjector = DaggerMembersInjectTest_NonLocalMembersComponent.factory()
        .create("test").getAMembersInjector();
    A testA = new A();
    membersInjector.injectMembers(testA);
    assertThat(testA.value).isEqualTo("test");
  }
}
