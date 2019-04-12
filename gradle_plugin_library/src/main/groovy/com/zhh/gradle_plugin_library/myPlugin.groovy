package com.zhh.gradle_plugin_library

import com.android.build.api.dsl.extension.AppExtension
import com.android.build.gradle.AppPlugin;
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by zhanghehe on 2019/4/2.
 * desc:
 */
public class myPlugin implements Plugin<Project> {
    void apply(Project project) {
        System.out.println("---------------开始---------------")
        System.out.println("这就是我们自定义插件")
        def android = project.extensions.getByType(AppExtension)
        def classTransform = new MyClassTransform(project)
        android.registerTransform(classTransform)

        project.extensions.create("testCreatJavaConfig", MyPlguinTestClass)

        if (project.plugins.hasPlugin(AppPlugin)) {
            android.applicationVariants.all { variant ->
                def variantData = variant.variantData
                def scope = variantData.scope

                def config = project.extensions.getByName("testCreatJavaConfig")

                def createTaskName = scope.getTaskName("CeShi", "MyTestPlugin")
                def createTask = project.task(createTaskName)
                createTask.doLast {
                    createJavaTest(variant, config)
                }
                //设置task依赖于生成BuildConfig的task，然后在生成BuildConfig后生成我们的类
                String generateBuildConfigTaskName = variant.getVariantData().getScope().getGenerateBuildConfigTask().name
                def generateBuildConfigTask = project.tasks.getByName(generateBuildConfigTaskName)
                if (generateBuildConfigTask) {
                    createTask.dependsOn generateBuildConfigTask
                    generateBuildConfigTask.finalizedBy createTask
                }
            }
        }
        System.out.println("------------------结束了吗----------------------");
    }

    static def void createJavaTest(variant, config) {
        //要生成的内容
        def content = """package com.zhh.viewmodeldemo;

                        /**
                         * Created by zhh on 2019/3/30.
                         */

                        public class MyPlguinTestClass {
                            public static final String str = "${config.str}";
                        }
                        """;
        //获取到BuildConfig类的路径
        File outputDir = variant.getVariantData().getScope().getBuildConfigSourceOutputDir()

        def javaFile = new File(outputDir, "MyPlguinTestClass.java")

        javaFile.write(content, 'UTF-8');
    }

    class MyPlguinTestClass {
        def str = "默认值";
    }

}
