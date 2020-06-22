package com.jacky.jikertime.architectlesson.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class UICompositeComponent implements UIComponent {
  String componentName;
  ComponentType componentType;
  List<UIComponent> children = new ArrayList<>();

  public UICompositeComponent(String componentName, ComponentType componentType) {
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
  public void print() {
    System.out.println(String.format("print %s (%s)",getComponentType(),getComponentName()));
    children.forEach((child)-> child.print());
  }

  @Override
  public void add(UIComponent uiComponent) {
    children.add(uiComponent);
  }

  @Override
  public void remove(UIComponent uiComponent) {
    children.remove(uiComponent);
  }
}
