# .NET第二次作业

# 实现说明

本Solution位于\Assignment2下，由3个Project组成，Assignment2，InfoControlV1和InfoControlV2。Assignment2为主程序，InfoControlV1和V2为两个版本的组件。

InfoControlV1和InfoControlV2均使用同一个密钥进行签名（位于\InfoControlV1(2)\InfoControl.Keys），具有相同的namespace、类名和程序集名称（InfoControl），唯一的区别只是版本号不一样(1.0.0.0和2.0.0.0)；

在Assignment2中，存在一个以下格式的InfoControlConfig.xml，其中保存了需要加载的组件版本（1或者2）。在程序运行时，将会使用**运行时反射**，在GAC中获得指定版本的程序集信息，并通过反射加载组件和绑定事件。

\Assignmen2\InfoControlConfig.xml

```xml
<?xml version="1.0" encoding="utf-8" ?>
<Version>2</Version>
```

InfoControlV1和InfoControlV2均对外暴露一个事件FileChanged。当用户在其中选择了目录并双击了一个文件后，组件将会触发这个事件，并将文件的绝对路径作为参数传入。在V2版本中，还会显示这个文件的详细信息。

在主程序运行时，将会使用以下加载指定版本的组件，并绑定FileChanged事件，当事件触发时，主程序中的Windows Media Player将会播放这个文件。

\Assignment2\Form1.Designer.cs

```csharp
// 通过反射加载组件
Assembly myControlAssembly = Assembly.Load($"InfoControl, Version={InfoControlConfig.Version}.0.0.0, Culture=neutral, PublicKeyToken=ff57dd7195e544df");
this.infoControl = (UserControl)myControlAssembly.CreateInstance("InfoControl.InfoControl");

// ...
// 绑定事件
var fileChangedEventInfo = myControlAssembly.GetType("InfoControl.InfoControl").GetEvent("FileChanged");
Delegate handler = Delegate.CreateDelegate(fileChangedEventInfo.EventHandlerType, this, GetType().GetMethod(nameof(this.OnFileChanged)));
fileChangedEventInfo.AddEventHandler(this.infoControl, handler);
```

# 运行说明

## 使用VS运行

1. 需要Visual Studio 2017版本和.NET Framework 4.6.1或以上的.NET SDK
2. 使用VS打开整个解决方案，并选择Build -> Build Solution
3. 分别在`\InfoControlV1\bin\Debug`和`\InfoControlV2\bin\Debug`中运行`gacutil -i InfoControl.dll`，将两个版本的组件都安装进GAC
4. 修改InfoControlConfig.xml指定需要加载的版本，再运行主程序。
5. 在组件中选择路径后，双击某一个文件即可播放。

## 独立运行

1. 需要.NET Framework 4.6.1或以上的.NET SDK
2. 进入dist目录，首先使用gacutil -i安装位于1\和2\下的两个InfoControl.dll。可使用提供的Register.bat脚本进行注册，但是请先根据自己的gacutil的路径调整脚本中指定的路径
3. 修改InfoControlConfig.xml指定需要加载的版本，再运行Assignment2.exe
4. 在组件中选择路径后，双击某一个文件即可播放。

# References

Develop a Windows Form Control

https://docs.microsoft.com/en-us/dotnet/framework/winforms/controls/how-to-develop-a-simple-windows-forms-control

How Runtime locates Assemblies

https://docs.microsoft.com/en-us/dotnet/framework/deployment/how-the-runtime-locates-assemblies

Install an Assembly into the GAC

https://docs.microsoft.com/en-us/dotnet/framework/app-domains/how-to-install-an-assembly-into-the-gac

Create a Strong Name Key pair

https://docs.microsoft.com/en-us/dotnet/framework/app-domains/how-to-create-a-public-private-key-pair

Sign an assembly

https://docs.microsoft.com/en-us/dotnet/framework/app-domains/how-to-sign-an-assembly-with-a-strong-name

How to Reference a Strong named assembly

https://docs.microsoft.com/en-us/dotnet/framework/app-domains/how-to-reference-a-strong-named-assembly