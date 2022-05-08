# 第10周 《手机移动开发》实践报告
### 实践目的
1. 能够使用 GridView 开发应用程序
2. 能够使用 Menu 组件开发应用程序
3. 能够使用 TabHost 组件开发应用程序
4. 能够使用 Fragment 开发动态的 UI 界面
5. 了解 Toolbar 组件
### 实践内容1：UI进阶2
#### 模块名
week10_advanceui2
#### 完成的主要功能
1. 建立模块及主控界面，构造菜单；
2. 使用 TabHost 组件开发应用程序；
3. 使用Fragment 开发动态的 UI 界面。
#### 完成过程中遇到的问题及解决办法
1. 在早些时候点击应用按钮不能成功跳转，后来发现是startActivity(intent)漏写，导致没有成功跳转；
2. 在实现使用Fragment 开发动态的 UI 界面时，在NewsActivity中，onPointerCaptureChanged()方法重写无法通过编译；
#### 完成该实践项目的收获及感想
1. 本次实践还算顺利，唯一难题就是onPointerCaptureChanged()方法的重写，在网上寻找原因，也没有找到合理的解释。