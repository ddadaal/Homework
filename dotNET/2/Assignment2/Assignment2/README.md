# Steps

1. Create three projects under main solution, One for the main application and two for two version of the user control.

2. Generate Key and sign both control project with the strong name key

3. Change assembly information of both versions of the control so that they both have the same assembly name(InfoControl), namespace name (InfoControl) and class name (InfoControl) but different version number(1.0.0.0 and 2.0.0.0)

4. In the main application, reference the control by 

```csharp
Assembly myControl = Assembly.Load($"InfoControl, Version={Version}.0.0.0, Culture=neutral, PublicKeyToken=ff57dd7195e544df");
this.infoControl = (UserControl)myControl.CreateInstance("InfoControl.InfoControl");
```

where `Version` denotes the specified version of the InfoControl that would be loaded, which is specified in `InfoControlConfig.xml`.

5. Install the dll of both versions of the control by `gacutil -i InfoControl.dll` in different bin directory so that GAC contains 2 versions of the InfoControl

6. Change the version number in `InfoControlConfig.xml` to dynamically switch the version of InfoControl. (Acceptable value: 1, 2)

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