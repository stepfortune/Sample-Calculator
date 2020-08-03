package com.hzh.calculator.util;

import java.io.File;
import java.io.IOException;

import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {



    public static List<Class<?>> getClassesInSamePackage(Class<?> cls, boolean recursive) throws IOException, ClassNotFoundException {
        return getClassesFromPackage(cls.getPackage().getName(), recursive);
    }

    public static List<Class<?>> getClassesFromPackage(String pkg, boolean recursive) throws IOException, ClassNotFoundException {
        List<Class<?>> result = null;
        String suffixPath = pkg.replaceAll("\\.", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> urls = classLoader.getResources(suffixPath);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            if (url.getProtocol().equals("file")) {
                String path = url.getPath();
                result = getClassesFromFile(new File(path), recursive);
            } else if (url.getProtocol().equals("jar")) {
                JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
                result = getClassFromJar(jarFile, pkg, recursive);
            }
        }
        return result == null ? new ArrayList<>() : result;
    }

    private static List<Class<?>> getClassesFromFile(File dir, boolean recursive) throws ClassNotFoundException {
        if (dir == null || !dir.exists()) return new ArrayList<>();
        List<Class<?>> res = new ArrayList<>();
        File[] ls = dir.listFiles();
        if (ls != null)
            for (File f : ls) {
                if (f.isDirectory()) {
                    if (recursive)
                        res.addAll(getClassesFromFile(f, true));
                } else {
                    String path = f.getPath();
                    //class文件, 并且不是内部类
                    if (path.endsWith(".class") && !path.contains("$")) {
                        //路径分隔符替换为包名分隔符, 编译后的class文件都在classes文件夹下, 去掉文件后缀名
                        String classFullName = path.replaceAll("[\\\\|/]", ".")
                                .substring(path.indexOf("classes") + 8, path.length() - 6);
                        res.add(Class.forName(classFullName));
                    }
                }
            }
        return res;
    }

    private static List<Class<?>> getClassFromJar(JarFile jar, String pkg, boolean recursive) throws ClassNotFoundException {
        if (jar == null) return new ArrayList<>();
        List<Class<?>> result = new ArrayList<>();
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String path = jarEntry.getName();
            System.out.println(path);
            if (path.endsWith(".class")) {
                String classFullName = path.replace(".class","")
                        .replaceAll("[\\\\|/]", ".");
                if (recursive) {
                    //递归查找的话, 只要在给定包下, 并且不是内部类就可以
                    if (classFullName.startsWith(pkg) && !classFullName.contains("$"))
                        result.add(Class.forName(classFullName));
                } else {
                    //不递归查找的话, 就只能在同一包下面, 不是内部类
                    if (pkg.equals(classFullName.substring(0, classFullName.lastIndexOf("."))) && !classFullName.contains("$"))
                        result.add(Class.forName(classFullName));
                }
            }

        }
        return result;
    }
}
