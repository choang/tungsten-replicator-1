/**
 * Tungsten Scale-Out Stack
 * Copyright (C) 2007-2009 Continuent Inc.
 * Contact: tungsten@continuent.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 *
 * Initial developer(s): Ed Archibald
 * Contributor(s): 
 */

package com.continuent.tungsten.common.directory;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.continuent.tungsten.common.cluster.resource.Resource;

/**
 * @author <a href="mailto:edward.archibald@continuent.com">Ed Archibald</a>
 * @version 1.0
 */
public class ResourceTree extends Resource implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected ResourceNode    rootNode;

    /**
     * Default constructor.
     */
    public ResourceTree()
    {
        super();
    }

    /**
     * Return the root Node of the tree.
     * 
     * @return the root element.
     */
    public ResourceNode getRootNode()
    {
        return this.rootNode;
    }

    /**
     * Set the root Element for the tree.
     * 
     * @param rootNode the root element to set.
     */
    public void setRootNode(ResourceNode rootNode)
    {
        this.rootNode = rootNode;
    }

    /**
     * @return A map of all nodes represented by this resource tree
     */
    public Map<String, ResourceNode> toMap()
    {
        Map<String, ResourceNode> map = new LinkedHashMap<String, ResourceNode>();
        traverse(rootNode, map);
        return map;
    }

    /**
     * Return a representation of this tree in string form.
     */
    @Override
    public String toString()
    {
        return toMap().toString();
    }

    /**
     * @param element
     * @param map
     */
    private void traverse(ResourceNode element, Map<String, ResourceNode> map)
    {
        map.put(element.getKey(), element);
        for (ResourceNode data : element.getChildren().values())
        {
            traverse(data, map);
        }
    }
}
