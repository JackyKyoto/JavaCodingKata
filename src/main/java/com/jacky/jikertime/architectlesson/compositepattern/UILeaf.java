package com.jacky.jikertime.architectlesson.compositepattern;

public class UILeaf implements UIComponent {
  String componentName;
  ComponentType componentType;

  public UILeaf(String componentName, ComponentType componentType) {
    this.componentName = componentName;
    this.componentType = componentType;
  }

  @Override
  public String getComponentName() {
    return componentName;
  }

  @Override
  public ComponentType getComponentType() {
    return componentType;
  }

  @Override
  public void add(UIComponent uiComponent) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void remove(UIComponent uiComponent) {
    throw new UnsupportedOperationException();
  }
}
