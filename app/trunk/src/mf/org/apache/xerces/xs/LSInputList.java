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

package mf.org.apache.xerces.xs;

import java.util.List;

import mf.org.w3c.dom.ls.LSInput;

/**
 *  The <code>LSInputList</code> interface provides the abstraction of an 
 * ordered collection of <code>LSInput</code>s, without defining or 
 * constraining how this collection is implemented. 
 */
public interface LSInputList extends List {
    /**
     *  The number of <code>LSInput</code>s in the list. The range of valid 
     * child object indices is 0 to <code>length-1</code> inclusive. 
     */
    public int getLength();

    /**
     *  Returns the <code>index</code>th item in the collection or 
     * <code>null</code> if <code>index</code> is greater than or equal to 
     * the number of objects in the list. The index starts at 0. 
     * @param index  index into the collection. 
     * @return  The <code>LSInput</code> at the <code>index</code>th 
     *   position in the <code>LSInputList</code>, or <code>null</code> if 
     *   the index specified is not valid. 
     */
    public LSInput item(int index);

}
