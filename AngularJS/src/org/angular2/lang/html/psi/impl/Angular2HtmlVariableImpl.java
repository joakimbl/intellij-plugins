// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.angular2.lang.html.psi.impl;

import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.XmlElementVisitor;
import org.angular2.lang.html.parser.Angular2HtmlElementTypes.Angular2ElementType;
import org.angular2.lang.html.psi.Angular2HtmlElementVisitor;
import org.angular2.lang.html.psi.Angular2HtmlVariable;
import org.jetbrains.annotations.NotNull;

import static org.angular2.lang.html.parser.Angular2HtmlParsing.normalizeAttributeName;

public class Angular2HtmlVariableImpl extends Angular2HtmlBoundAttributeImpl implements Angular2HtmlVariable {

  public Angular2HtmlVariableImpl(@NotNull Angular2ElementType type) {
    super(type);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Angular2HtmlElementVisitor) {
      ((Angular2HtmlElementVisitor)visitor).visitVariable(this);
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
  public String getVariableName() {
    String name = normalizeAttributeName(getName());
    if (name.startsWith("let-")) {
      return name.substring(4);
    }
    throw new IllegalStateException("Bad attribute name: " + name);
  }

  @Override
  public String toString() {
    return "Angular2HtmlVariable <" + getVariableName() + ">";
  }
}
