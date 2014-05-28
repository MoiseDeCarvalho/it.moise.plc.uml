/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package org.apache.xerces.util;

import java.io.IOException;

import javax.xml.parsers.SAXParserFactory;

import org.apache.xerces.dom.DOMInputImpl;
import org.apache.xerces.jaxp.SAXParserFactoryImpl;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLInputSource;

import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.EntityResolver2;

/**
 * <p>The catalog resolver handles the resolution of external
 * identifiers and URI references through XML catalogs. This
 * component supports XML catalogs defined by the
 * <a href="http://www.oasis-open.org/committees/entity/spec.html">
 * OASIS XML Catalogs Specification</a>. It encapsulates the 
 * <a href="http://xml.apache.org/commons/">XML Commons</a> resolver. 
 * An instance of this class may be registered on the parser 
 * as a SAX entity resolver, as a DOM LSResourceResolver or 
 * as an XNI entity resolver by setting the property
 * (http://apache.org/xml/properties/internal/entity-resolver).</p>
 * 
 * <p>It is intended that this class may be used standalone to perform 
 * catalog resolution outside of a parsing context. It may be shared
 * between several parsers and the application.</p>
 *
 * @author Michael Glavassevich, IBM
 *
 * @version $Id: XMLCatalogResolver.java 699892 2008-09-28 21:08:27Z mrglavas $
 */
public class XMLCatalogResolver 
    implements XMLEntityResolver, LSResourceResolver {
    
   
	
    /** An array of catalog URIs. **/
    private String [] fCatalogsList = null;

    /** 
     * Indicates whether the list of catalogs has
     * changed since it was processed.
     */
    private boolean fCatalogsChanged = true;
    
    /** Application specified prefer public setting. **/
    private boolean fPreferPublic = true;
    
    /** 
     * Indicates whether the application desires that 
     * the parser or some other component performing catalog
     * resolution should use the literal system identifier
     * instead of the expanded system identifier.
     */
    private boolean fUseLiteralSystemId = true;
    
    /**
     * <p>Constructs a catalog resolver with a default configuration.</p>
     */
    public XMLCatalogResolver () {
    
    }
    
    /**
     * <p>Constructs a catalog resolver with the given
     * list of entry files.</p>
     * 
     * @param catalogs an ordered array list of absolute URIs
     */
    public XMLCatalogResolver (String [] catalogs) {
    
    }
    
    /**
     * <p>Constructs a catalog resolver with the given
     * list of entry files and the preference for whether
     * system or public matches are preferred.</p>
     * 
     * @param catalogs an ordered array list of absolute URIs
     * @param preferPublic the prefer public setting
     */
    public XMLCatalogResolver (String [] catalogs, boolean preferPublic) {
       
    }

	@Override
	public LSInput resolveResource(String arg0, String arg1, String arg2,
			String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XMLInputSource resolveEntity(XMLResourceIdentifier resourceIdentifier)
			throws XNIException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
    
