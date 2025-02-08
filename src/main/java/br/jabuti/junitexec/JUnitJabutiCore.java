/*  Copyright 2003  Auri Marcelo Rizzo Vicenzi, Marcio Eduardo Delamaro, 			    Jose Carlos Maldonado

    This file is part of Jabuti.

    Jabuti is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as 
    published by the Free Software Foundation, either version 3 of the      
    License, or (at your option) any later version.

    Jabuti is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Jabuti.  If not, see <http://www.gnu.org/licenses/>.
*/


package br.jabuti.junitexec;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.engine.discovery.DiscoverySelectors;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import br.jabuti.util.ToolConstants;

public class JUnitJabutiCore {

    public static HashMap<String, String> runCollecting(String classpath, String ts)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, MalformedURLException {

        Launcher launcher = LauncherFactory.create();

        Class<?> clazz = ToolConstants.getClassFromClasspath("br.jabuti.junitexec.CollectorListener", false, classpath);
        Constructor<?> cons = clazz.getConstructor();
        CollectorListener collectorListener = (CollectorListener) cons.newInstance();

        launcher.registerTestExecutionListeners(collectorListener);

        Class<?> testClass = ToolConstants.getClassFromClasspath(ts, false, classpath);

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(testClass))
                .build();

        launcher.execute(request);

        return collectorListener.getTestSet();
    }

    public static void runInstrumenting(String classpath, String ts, String trace, Set<String> testSet, PrintStream ps)
            throws ClassNotFoundException, MalformedURLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Launcher launcher = LauncherFactory.create();

        Class<?> clazz = ToolConstants.getClassFromClasspath("br.jabuti.junitexec.InstrumenterListener", false, classpath);
        Constructor<?> cons = clazz.getConstructor(String.class, Set.class, PrintStream.class);
        InstrumenterListener instrumenterListener = (InstrumenterListener) cons.newInstance(trace, testSet, ps);

        launcher.registerTestExecutionListeners(instrumenterListener);

        Class<?> testClass = ToolConstants.getClassFromClasspath(ts, false, classpath);

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(testClass))
                .build();

        PrintStream current = System.out;
        if (ps != null) {
            System.setOut(ps);
        }
        launcher.execute(request);
        System.setOut(current);
    }

    public static void main(String[] args) throws Exception {
        String tcClass = null, trace = null, classpath = null;
        Set<String> testSet = new HashSet<>();
        HashMap<String, String> hm;

        if (args.length < 2) {
            usage();
        } else {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-trace":
                        trace = args[++i];
                        break;
                    case "-tcClass":
                        tcClass = args[++i];
                        break;
                    case "-cp":
                        classpath = args[++i];
                        break;
                    default:
                        testSet.add(args[i]);
                        break;
                }
            }
            
            System.out.println("Collecting mode");
            hm = runCollecting(classpath, tcClass);
            for (String n : hm.keySet()) {
                System.out.println("TC Name: " + n + " STATUS: " + hm.get(n));
            }
            
            if (trace != null) {
            	System.out.println("Instrumenting mode");
                if (hm != null && !(hm.isEmpty())) {
                    testSet = hm.keySet();
                }
                runInstrumenting(classpath, tcClass, trace, testSet, System.out);
            }
        }
    }

    public static void usage() {
        System.out.println("JUnitJabutiCore [-trace <file_name.trc>] -cp <app_instr.jar:test_set_classpath> -tcClass <test_class> [<test_case_name1> <test_case_name2>...]");
    }
}
