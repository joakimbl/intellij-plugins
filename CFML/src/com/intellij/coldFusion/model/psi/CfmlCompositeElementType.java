/*
 * Copyright 2000-2013 JetBrains s.r.o.
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
package com.intellij.coldFusion.model.psi;

import com.intellij.coldFusion.model.CfmlLanguage;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.tree.ICompositeElementType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: vnikolaenko
 * Date: 23.04.2009
 * Time: 19:52:20
 * To change this template use File | Settings | File Templates.
 */
public class CfmlCompositeElementType extends IElementType implements ICompositeElementType {
  public CfmlCompositeElementType(@NotNull @NonNls final String debugName) {
    super(debugName, CfmlLanguage.INSTANCE);
  }

  public PsiElement createPsiElement(ASTNode node) {
    return new CfmlCompositeElement(node);
  }

  @NotNull
  public ASTNode createCompositeNode() {
    return new CompositeElement(this);
  }
}
