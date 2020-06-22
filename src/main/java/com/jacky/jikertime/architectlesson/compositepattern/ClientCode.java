package com.jacky.jikertime.architectlesson.compositepattern;


import static com.jacky.jikertime.architectlesson.compositepattern.UIComponent.ComponentType.Button;
import static com.jacky.jikertime.architectlesson.compositepattern.UIComponent.ComponentType.CheckBox;
import static com.jacky.jikertime.architectlesson.compositepattern.UIComponent.ComponentType.Frame;
import static com.jacky.jikertime.architectlesson.compositepattern.UIComponent.ComponentType.Lable;
import static com.jacky.jikertime.architectlesson.compositepattern.UIComponent.ComponentType.LinkLabel;
import static com.jacky.jikertime.architectlesson.compositepattern.UIComponent.ComponentType.PasswordBox;
import static com.jacky.jikertime.architectlesson.compositepattern.UIComponent.ComponentType.Picture;
import static com.jacky.jikertime.architectlesson.compositepattern.UIComponent.ComponentType.TextBox;
import static com.jacky.jikertime.architectlesson.compositepattern.UIComponent.ComponentType.WinForm;

public class ClientCode {
  public static void main(String[] args) {
    UICompositeComponent rootWinForm = new UICompositeComponent("WINDOW窗口", WinForm);
    UILeaf picture = new UILeaf("LOGO图片",Picture);
    rootWinForm.add(picture);
    UILeaf button1 = new UILeaf("登陆",Button);
    rootWinForm.add(button1);
    UILeaf button2 = new UILeaf("注册",Button);
    rootWinForm.add(button2);
    UICompositeComponent frame1 = new UICompositeComponent("FRAME1",Frame);
    rootWinForm.add(frame1);
    UILeaf lable1 = new UILeaf("用户名", Lable);
    frame1.add(lable1);
    UILeaf textbox1 = new UILeaf("文本框",TextBox);
    frame1.add(textbox1);
    UILeaf lable2 = new UILeaf("密码",Lable);
    frame1.add(lable2);
    UILeaf pwdBox = new UILeaf("密码框",PasswordBox);
    frame1.add(pwdBox);
    UILeaf checkbox1 = new UILeaf("复选框",CheckBox);
    frame1.add(checkbox1);
    UILeaf textBox2 = new UILeaf("记住用户名",TextBox);
    frame1.add(textBox2);
    UILeaf linkLable = new UILeaf("忘记密码",LinkLabel);
    frame1.add(linkLable);
    rootWinForm.print();
  }
}
