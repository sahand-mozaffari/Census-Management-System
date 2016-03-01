/* ========================================================================
 * JCommon : a free general purpose class library for the Java(tm) platform
 * ========================================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 * 
 * Project Info:  http://www.jfree.org/jcommon/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 * 
 * -------------------------
 * ByteAttributeHandler.java
 * -------------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: ByteAttributeHandler.java,v 1.1 2004/07/15 14:57:00 mungady Exp $
 *
 * Changes 
 * -------
 * 24.09.2003 : Initial version
 *  
 */

package org.jfree.xml.attributehandlers;

/**
 * A class that handles the conversion of {@link Byte} attributes to and from an appropriate
 * {@link String} representation.
 */
public class ByteAttributeHandler implements AttributeHandler {

    /**
     * Creates a new attribute handler.
     */
    public ByteAttributeHandler() {
        super();
    }

    /**
     * Converts the attribute to a string.
     * 
     * @param o  the attribute ({@link Byte} expected).
     * 
     * @return A string representing the {@link Byte} value.
     */
    public String toAttributeValue(final Object o) {
        final Byte in = (Byte) o;
        return in.toString();
    }

    /**
     * Converts a string to a {@link Byte}.
     * 
     * @param s  the string.
     * 
     * @return a {@link Byte}.
     */
    public Object toPropertyValue(final String s) {
        return new Byte(s);
    }
    
}
