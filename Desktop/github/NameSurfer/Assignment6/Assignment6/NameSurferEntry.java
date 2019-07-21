/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;
import com.sun.xml.internal.fastinfoset.util.StringArray;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line)
	{
			String[] stringArray = line.split(" ");
			name = stringArray[0];
			
			rankArray = new int[NDECADES+1];
			for (int i = 1; i < stringArray.length; i++) 
			{
				rankArray[i-1] = Integer.parseInt(stringArray[i]);
			}
			
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
			public int getRank(int decade) 
			{
				// You need to turn this stub into a real implementation //
				return rankArray[decade];
			}
	
			
/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
			public String toString() 
			{
				String stringToReturn = "" +name +"[" ;
				for (int i = 0; i < rankArray.length; i++) 
				{
						stringToReturn += " " +  rankArray[i];
				}
					return stringToReturn + "]";
			}
			
			private String name;
			private int[] rankArray;
}

