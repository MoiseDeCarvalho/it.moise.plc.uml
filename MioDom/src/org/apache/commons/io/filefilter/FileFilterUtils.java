/*
 * Copyright 2002-2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.io.filefilter;

import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * Useful utilities for working with file filters. It provides access to all
 * file filter implementations in this package so you don't have to import
 * every class you use.
 * 
 * @since Commons IO 1.0
 * @version $Id: FileFilterUtils.java 201606 2005-06-24 12:35:32Z jeremias $
 * 
 * @author Henri Yandell
 * @author Stephen Colebourne
 * @author Jeremias Maerki
 * @author Masato Tezuka
 */
public class FileFilterUtils {
    
    /**
     * FileFilterUtils is not normally instantiated.
     */
    public FileFilterUtils() {
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a filter that returns true if the filename starts with the specified text.
     * 
     * @param prefix  the filename prefix
     * @return a prefix checking filter
     */
    public static IOFileFilter prefixFileFilter(String prefix) {
        return new PrefixFileFilter(prefix);
    }

    /**
     * Returns a filter that returns true if the filename ends with the specified text.
     * 
     * @param suffix  the filename suffix
     * @return a suffix checking filter
     */
    public static IOFileFilter suffixFileFilter(String suffix) {
        return new SuffixFileFilter(suffix);
    }

    /**
     * Returns a filter that returns true if the filename matches the specified text.
     * 
     * @param name  the filename
     * @return a name checking filter
     */
    public static IOFileFilter nameFileFilter(String name) {
        return new NameFileFilter(name);
    }

    /**
     * Returns a filter that checks if the file is a directory.
     * 
     * @return directory file filter
     */
    public static IOFileFilter directoryFileFilter() {
        return DirectoryFileFilter.INSTANCE;
    }
    
    //-----------------------------------------------------------------------
    /**
     * Returns a filter that ANDs the two specified filters.
     * 
     * @param filter1  the first filter
     * @param filter2  the second filter
     * @return a filter that ANDs the two specified filters
     */
    public static IOFileFilter andFileFilter(IOFileFilter filter1, IOFileFilter filter2) {
        return new AndFileFilter(filter1, filter2);
    }

    /**
     * Returns a filter that ORs the two specified filters.
     * 
     * @param filter1  the first filter
     * @param filter2  the second filter
     * @return a filter that ORs the two specified filters
     */
    public static IOFileFilter orFileFilter(IOFileFilter filter1, IOFileFilter filter2) {
        return new OrFileFilter(filter1, filter2);
    }

    /**
     * Returns a filter that NOTs the specified filter.
     * 
     * @param filter  the filter to invert
     * @return a filter that NOTs the specified filter
     */
    public static IOFileFilter notFileFilter(IOFileFilter filter) {
        return new NotFileFilter(filter);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a filter that always returns true.
     * 
     * @return a true filter
     */
    public static IOFileFilter trueFileFilter() {
        return TrueFileFilter.INSTANCE;
    }

    /**
     * Returns a filter that always returns false.
     * 
     * @return a false filter
     */
    public static IOFileFilter falseFileFilter() {
        return FalseFileFilter.INSTANCE;
    }
    
    //-----------------------------------------------------------------------
    /**
     * Returns an <code>IOFileFilter</code> that wraps the
     * <code>FileFilter</code> instance.
     * 
     * @param filter  the filter to be wrapped
     * @return a new filter that implements IOFileFilter
     */
    public static IOFileFilter asFileFilter(FileFilter filter) {
        return new DelegateFileFilter(filter);
    }

    /**
     * Returns an <code>IOFileFilter</code> that wraps the
     * <code>FilenameFilter</code> instance.
     * 
     * @param filter  the filter to be wrapped
     * @return a new filter that implements IOFileFilter
     */
    public static IOFileFilter asFileFilter(FilenameFilter filter) {
        return new DelegateFileFilter(filter);
    }

    //-----------------------------------------------------------------------

    /* Constructed on demand and then cached */
    private static IOFileFilter cvsFilter;

    /* Constructed on demand and then cached */
    private static IOFileFilter svnFilter;

    /**
     * Returns an IOFileFilter that ignores CVS directories. You may optionally
     * pass in an existing IOFileFilter in which case it is extended to exclude
     * CVS directories.
     * @param filter IOFileFilter to wrap, null if a new IOFileFilter
     * should be created
     * @return the requested (combined) filter
     * @since 1.1 (method existed but had bug in 1.0)
     */
    public static IOFileFilter makeCVSAware(IOFileFilter filter) {
        if (cvsFilter == null) {
            cvsFilter = notFileFilter(
                andFileFilter(directoryFileFilter(), nameFileFilter("CVS")));
        }
        if (filter == null) {
            return cvsFilter;
        } else {
            return andFileFilter(filter, cvsFilter);
        }
    }

    /**
     * Returns an IOFileFilter that ignores SVN directories. You may optionally
     * pass in an existing IOFileFilter in which case it is extended to exclude
     * SVN directories.
     * @param filter IOFileFilter to wrap, null if a new IOFileFilter
     * should be created
     * @return the requested (combined) filter
     * @since 1.1
     */
    public static IOFileFilter makeSVNAware(IOFileFilter filter) {
        if (svnFilter == null) {
            svnFilter = notFileFilter(
                andFileFilter(directoryFileFilter(), nameFileFilter(".svn")));
        }
        if (filter == null) {
            return svnFilter;
        } else {
            return andFileFilter(filter, svnFilter);
        }
    }

}
