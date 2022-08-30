#JacocoDemo

#JaCoCo与Gradle获取功能测试代码覆盖率使用说明：

1.一个Instrument启动器，目的是方便通过instrument指令启动带有coverage记录功能的activity。
这个文件的主要功能有两点：

在/data/data/应用包名/files目录下创建coverage.ec，这个文件就是用来记录覆盖率数据的；
启动改写的Activity，这里是InstrumentedActivity。



AndroidManifest.xml改写

```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

```
<activity
android:name="test.InstrumentedActivity"
android:label="InstrumentationActivity" />
```

```
<instrumentation
android:name="test.JacocoInstrumentation"
android:handleProfiling="true"
android:label="CoverageInstrumentation"
android:targetPackage="com.peak.jacocodemo" />
```

build.gradle改写

这是app/build.gradle的一个例子，关键部分我都加了注释。得到ec文件之后，需要将Android设备下的ec文件，
放置到$buildDir/outputs/code-coverage/connected/coverage.ec然后运行jacocoTestReport这个task，
运行成功后会在$buildDir/reports/jacoco目录下生成根据eoverage.ec转义的html等文件，html目录下的index.html可以可视化展示代码覆盖率数据。



总结：操作步骤
1.安装debug包到设备上
2.退出app应用
3.cmd命令：adb shell am instrument com.peak.jacocodemo/com.peak.jacocodemo.test.JacocoInstrumentation
4.手动测试
5.返回键回到设备主页面
6.在设备的文件管理中取回代码覆盖率文件coverage.ec（路径：data/data/包名/files） 
使用 adb pull < 手机端文件 > < 本地路径 > 把设备（手机）的文件或文件夹复制到本地
eg:./adb pull /storage/emulated/0/coverage.ec /Users/hanson/AndroidStudioProjects/JaCoCoDemo

7.复制到项目的 build\outputs\code-coverage\connected 中  没有此路径 手动创建
8.回到Android Studio，点击右侧的Gradle -> Tasks -> reporting -> jacocoTestReport
9.生成的报告（index.html）路径为：build/reports/jacoco/jacocoTestReport/html/com.peak.jacocodemo 中 （把jacocoTestReport复制到本地用浏览器打开就能看到代码覆盖率信息）



有关jacoco_tesh.sh的说明（需要自己配置）：
macaca coverage 生产报告
使用gradle的jacocoTestReport也可以生产报告,也是大多人使用的方式,本文就不做介绍了,主要介绍使用macaca coverage方法.

macaca
coverage可以生成jacoco报告,不仅可以生成Android项目,也可以生产iOS、web项目.具体使用请查看https://macacajs.github.io/zh/coverage.

安装macaca-coverage命令:

npm i macaca-cli -g  
macaca coverage -h  
npm i macaca-coverage --save-dev



macaca coverage命令:  
macaca coverage -r java -f `pwd`/new.ec -c `pwd`/app/build/intermediates/classes/debug -s `pwd`/app/src/main/java --html `pwd`/reporter  
