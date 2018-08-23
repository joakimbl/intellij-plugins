// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.angular2.lang.html.psi.impl;

import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.XmlElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.ContainerUtil;
import org.angular2.lang.expr.psi.Angular2Action;
import org.angular2.lang.html.parser.Angular2HtmlElementTypes.Angular2ElementType;
import org.angular2.lang.html.psi.Angular2HtmlElementVisitor;
import org.angular2.lang.html.psi.Angular2HtmlEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.angular2.lang.html.parser.Angular2HtmlParsing.normalizeAttributeName;

public class Angular2HtmlEventImpl extends Angular2HtmlBaseAttributeImpl implements Angular2HtmlEvent {

  public Angular2HtmlEventImpl(@NotNull Angular2ElementType type) {
    super(type);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Angular2HtmlElementVisitor) {
      ((Angular2HtmlElementVisitor)visitor).visitEvent(this);
    }
    else if (visitor instanceof XmlElementVisitor) {
      ((XmlElementVisitor)visitor).visitXmlAttribute(this);
    }
    else {
      visitor.visitElement(this);
    }
  }

  @NotNull
  @Override
  public String getEventName() {
    String name = normalizeAttributeName(getName());
    if (name.startsWith("(") && name.endsWith(")")) {
      return name.substring(1, name.length() - 1);
    }
    else if (name.startsWith("on-")) {
      return name.substring(3);
    }
    throw new IllegalStateException("Bad attribute name: " + name);
  }

  @Nullable
  @Override
  public Angular2Action getAction() {
    return ContainerUtil.getFirstItem(PsiTreeUtil.findChildrenOfType(this, Angular2Action.class));
  }

  @Override
  public String toString() {
    return "Angular2HtmlEvent <" + getEventName() + ">";
  }

  @Override
  public void setValue(String valueText) throws IncorrectOperationException {
    super.setValue(valueText);
  }
}