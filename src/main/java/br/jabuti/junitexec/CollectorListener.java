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

import java.util.HashMap;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

public class CollectorListener implements TestExecutionListener {
    private final HashMap<String, String> testSet = new HashMap<>();

    public CollectorListener() {
 
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.isTest()) {
            String tc = JUnitUtil.getTestCaseName(testIdentifier.getDisplayName());
            testSet.put(tc, JUnitUtil.SUCCESS);
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            String tc = JUnitUtil.getTestCaseName(testIdentifier.getDisplayName());

            switch (testExecutionResult.getStatus()) {
                case FAILED:
                    testSet.put(tc, JUnitUtil.FAILURE);
                    break;
                case ABORTED:
                    testSet.put(tc, JUnitUtil.IGNORED);
                    break;
                case SUCCESSFUL:
                    testSet.put(tc, JUnitUtil.SUCCESS);
                    break;
            }
        }
    }

    @Override
    public void executionSkipped(TestIdentifier testIdentifier, String reason) {
        if (testIdentifier.isTest()) {
            String tc = JUnitUtil.getTestCaseName(testIdentifier.getDisplayName());
            testSet.put(tc, JUnitUtil.IGNORED);
        }
    }

    public HashMap<String, String> getTestSet() {
        return testSet;
    }
}
