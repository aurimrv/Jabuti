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

import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.engine.TestExecutionResult;


import br.jabuti.probe.DefaultProber;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class InstrumenterListener implements TestExecutionListener {
    private final Set<String> testSet;
    private final String traceFileName;
    private boolean enable;
    private final PrintStream fWriter;

    public InstrumenterListener(String trace, Set<String> ts, PrintStream writer) {
        this.traceFileName = trace;
        this.testSet = (ts != null) ? ts : new HashSet<>();
        this.enable = false;
        this.fWriter = writer;
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.isTest()) {
            String tcName = JUnitUtil.getTestCaseName(testIdentifier.getDisplayName());
            // Collecting traces only for specific test case name
            if (testSet.contains(tcName)) {
                try {
                    DefaultProber.startTrace(tcName);
                } catch (Exception e) {
                    e.printStackTrace(fWriter);
                }
                enable = true;
                fWriter.append(JUnitUtil.traceMark);
            } else {
            	fWriter.append(JUnitUtil.noTraceMark);
            }
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest() && enable) {
            enable = false;
            try {
                DefaultProber.stopTrace();
            } catch (Exception e) {
                e.printStackTrace(fWriter);
            }
        }
    }

    @Override
    public void testPlanExecutionStarted(org.junit.platform.launcher.TestPlan testPlan) {
        fWriter.append(JUnitUtil.integratorName).append(": Instrumentor Mode\n");
        try {
            DefaultProber.init(traceFileName);
            fWriter.println("Trace file: " + traceFileName);
        } catch (Exception e) {
            e.printStackTrace(fWriter);
        }
    }

    @Override
    public void testPlanExecutionFinished(org.junit.platform.launcher.TestPlan testPlan) {
    	fWriter.append("\nTrace collection finished\n");
        try {
            DefaultProber.finished();
        } catch (Exception e) {
            e.printStackTrace(fWriter);
        }
    }
}
