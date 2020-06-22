package com.jacky.jikertime.architectlesson.compositepattern;

public interface UIComponent {
  public enum ComponentType{
    WinForm,Picture,Button,Frame, Lable,TextBox,PasswordBox,CheckBox,LinkLabel;
  }
  String getComponentName();
  ComponentType getComponentType();
  default void print(){
    System.out.println(String.format("print %s (%s)",getComponentType(),getComponentName()));
  };
  void add(UIComponent uiComponent);
  void remove(UIComponent uiComponent);
}
