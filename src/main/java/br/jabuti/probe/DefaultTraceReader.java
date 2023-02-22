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



package br.jabuti.probe;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;

import br.jabuti.util.Debug;

/** This class reads a file stored by the {@link DefaultProber} class.
 */

public class DefaultTraceReader implements Serializable, TraceReader {
    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = -782971586725069021L;
	protected Hashtable paths;
    protected File fp;
    protected BufferedReader br;
    protected String tcName;
	
    public DefaultTraceReader(File f) throws IOException, FileNotFoundException {
        fp = f.getAbsoluteFile();
        br = new BufferedReader(new FileReader(fp));
    }
	
    protected DefaultTraceReader() {
        br = null;
    }
	
    protected int readPaths() {
        paths = null;
        if (br == null) {
            return 0;
        }
        paths = new Hashtable();
        int k = 0;

		Object v[] = null;
        do {
        	try 
        	{
		       	String tipo = br.readLine();
		       	if (tipo.equals(DefaultProber.delimiter)) {
       	           break;
    		   	}
		       	System.out.println("Tipo " + tipo);
		       	tcName = br.readLine(); // le o nome do caso de teste
		       	if ( tipo.equals(this.getClass().toString() ) )
		       	{	
        			v = readOnePath();
                }
               	if ( v != null) paths.put(v[0], v[1]);
            } catch (Exception e) { 
                Debug.D("FINAL TRACE: (" + k + ") " + e + ""); 
                paths = null;
                return 0;
            }
        } while ( v != null );
		
        Enumeration en = paths.keys();

        while (en.hasMoreElements()) {
            ProbedNode pdn = (ProbedNode) en.nextElement();
            ArrayList arl = (ArrayList) paths.get(pdn);
            ArrayList v2 = new ArrayList();

            getSinglePath(v2, arl); 
            paths.put(pdn, v2.toArray(new String[v2.size()][]));
        }
        return paths.size();
    }
    
    protected Object[] readOnePath() throws Exception
    {
       	String thrd = br.readLine();
        String obj = br.readLine();
        String claz = br.readLine();
        String meth = br.readLine();

        ProbedNode pdn = new ProbedNode(thrd, obj, claz,
                       Integer.parseInt(meth), "");

        ArrayList arl = new ArrayList();
		
        String nodeNumber = br.readLine();

        while (!nodeNumber.equals("-1")) {
                   arl.add(nodeNumber);
                   nodeNumber = br.readLine();
        }
        Object[] v = new Object[]  {pdn, arl};
        return v;
    }
    
    protected void getSinglePath(ArrayList v, ArrayList arl) 
    {
    	String[] art = (String[]) arl.toArray(new String[0]);
    	Arrays.sort(art, new NodeNameComparator());
    	for (int j = 0; j < art.length; )
    	{
	        String s = art[j];
	        int tp = s.indexOf(":");
	        String s2 = s.substring(tp+1);
	        s = s.substring(0,tp);
	        int nest = Integer.parseInt(s);
	        ArrayList p = new ArrayList();
	        
	        for ( ; j < art.length; j++)
	        {
	            s = art[j];
				tp = s.indexOf(":");
		        s2 = s.substring(tp+1);
		        s = s.substring(0,tp);
		        int nt = Integer.parseInt(s);
		        if ( nt == nest )
		        {
		        	p.add(s2);
		        }
		        else
		        {
		        	break;
		        }
	        }
	        v.add(p.toArray(new String[0]));
    	}
	}
	
    protected void xgetSinglePath(ArrayList v, ArrayList arl) {
    	if (arl.size() == 0 )
    		return;
    		
        String s = (String) arl.get(0);
        int tp = s.indexOf(":");
        String s2 = s.substring(tp+1);
        s = s.substring(0,tp);
        
        int nest = Integer.parseInt(s);
		
        ArrayList p = new ArrayList();
        p.add(s2);
        arl.remove(0);

        while (arl.size() > 0) 
        {
            s = (String) arl.get(0);
			tp = s.indexOf(":");
	        s2 = s.substring(tp+1);
	        s = s.substring(0,tp);
	        
	        int nt = Integer.parseInt(s);
	        if ( nt == nest )
	        { // no pertence aa mesma chamada do metodo
	        	p.add(s2);
	        	arl.remove(0);
	        }
	        else
	        if ( nt > nest )
	        {  // no pertence a chamada mais interna
	        	getSinglePath(v, arl);
	        }
	        else
	        {  // no pertence a chamada anterior
	        	break;
	        }
        } 
        v.add(p.toArray(new String[0]));
    }

    public Hashtable getPaths() {
        readPaths();
        return paths;
    }
	
    public void reset() throws IOException, FileNotFoundException {
        br.close();
        br = new BufferedReader(new FileReader(fp));
    }

    public String toString() {
        String str = "";
        Enumeration en = paths.keys();

        while (en.hasMoreElements()) {
            ProbedNode met = (ProbedNode) en.nextElement();

            str += "==========================================\n";
            str += met + "\n";
            String nodes[][] = (String[][]) paths.get(met);

            str += "Paths: " + nodes.length + "\n";
            for (int i = 0; i < nodes.length; i++) {
                str += "Path len: " + nodes[i].length + "\n";
                for (int j = 0; j < nodes[i].length; j++) {
                    String n = nodes[i][j];

                    str += " " + n;
                }
                str += "\n";
            }
            str += "\n";
        }
        return str;
    }

	public String getName() {
		return tcName;
	}
    
}

class NodeNameComparator implements Comparator
{
	public boolean equals(Object o1)
	{
		return false;
	}
	
	public int compare(Object o1, Object o2)
	{
        String s = o1.toString();
        int tp = s.indexOf(":");
        s = s.substring(0,tp);
        int n1 = Integer.parseInt(s);

        s = o2.toString();
        tp = s.indexOf(":");
        s = s.substring(0,tp);
        int n2 = Integer.parseInt(s);
        
        return n1 - n2;
	}
}
