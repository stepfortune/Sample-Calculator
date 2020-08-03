package com.hzh.calculator.util;


import java.io.File;
import java.io.IOException;

import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarFile;

public class ClassUtil {

    public static List<Class<?>> getClassesInSamePackage(Class<?> cls, boolean recursive) throws IOException {
        return getClassesFromPackage(cls.getPackage().getName(), recursive);
    }

    public static List<Class<?>> getClassesFromPackage(String pkg, boolean recursive) throws IOException {
        List<Class<?>> result = null;
        String suffixPath = pkg.replace("\\.", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> urls = classLoader.getResources(suffixPath);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            if (url.getProtocol().equals("file")) {
                String path = url.getPath();
                result = getClassesFromFile(new File(path), recursive);
            } else if (url.getProtocol().equals("jar")) {
                JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
                result = getClassFromJar(jarFile, recursive);
            }
        }
        return result == null ? new ArrayList<Class<?>>() : result;
    }

    private static List<Class<?>> getClassesFromFile(File dir, boolean recursive) {
        if (dir == null || !dir.exists()) return new ArrayList<>();
        List<Class<?>> res = new ArrayList<>();
        File[] ls = dir.listFiles();

        for (File f : dir) {
            if (recursive && f.isDirectory()) {
                res.addAll(getClassesFromFile(f, recursive));
            } else {
                String path = f.getPath();
                if (path.endsWith(".class")) {

                }
            }
        }
        return res;
    }

    private static List<Class<?>> getClassFromJar(JarFile jar, boolean recursive) {
        if (jar == null) return new ArrayList<>();
        return null;

    }


/*    public static List<Class<?>> getAllAssignedClass(Class<?> cls) throws IOException,
            ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (Class<?> c : getClasses(cls)) {
            if (cls.isAssignableFrom(c) && !cls.equals(c)) {
                classes.add(c);
            }
        }
        return classes;
    }


    public static List<Class<?>> getClasses(Class<?> cls) throws IOException,
            ClassNotFoundException {
        String pk = cls.getPackage().getName();
        String path = pk.replace('.', '/');
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(path);
        assert url != null;
        return getClasses(new File(url.getFile()), pk);
    }


    private static List<Class<?>> getClasses(File dir, String pk) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!dir.exists()) {
            return classes;
        }
        for (File f : Objects.requireNonNull(dir.listFiles())) {
            if (f.isDirectory()) {
                classes.addAll(getClasses(f, pk + "." + f.getName()));
            }
            String name = f.getName();
            if (name.endsWith(".class")) {
                classes.add(Class.forName(pk + "." + name.substring(0, name.length() - 6)));
            }
        }
        return classes;
    }*/
}
