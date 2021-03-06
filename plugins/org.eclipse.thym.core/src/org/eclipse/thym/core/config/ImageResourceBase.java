/*******************************************************************************
 * Copyright (c) 2013, 2014 Red Hat, Inc. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * 	Contributors:
 * 		 Red Hat Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.thym.core.config;


import static org.eclipse.thym.core.config.WidgetModelConstants.IMAGERESOURCE_ATTR_DENSITY;
import static org.eclipse.thym.core.config.WidgetModelConstants.IMAGERESOURCE_ATTR_HEIGHT;
import static org.eclipse.thym.core.config.WidgetModelConstants.IMAGERESOURCE_ATTR_PLATFORM;
import static org.eclipse.thym.core.config.WidgetModelConstants.IMAGERESOURCE_ATTR_SRC;
import static org.eclipse.thym.core.config.WidgetModelConstants.IMAGERESOURCE_ATTR_WIDTH;
import static org.eclipse.thym.core.config.WidgetModelConstants.NS_PHONEGAP_1_0;

import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * Base for image configuration elements such as icons and splashes.
 * 
 * @author Gorkem Ercan
 *
 */
public abstract class ImageResourceBase extends AbstractConfigObject {
	
	private Property<String> src = new Property<String>(IMAGERESOURCE_ATTR_SRC);
	private Property<Integer> width = new Property<Integer>(IMAGERESOURCE_ATTR_WIDTH);
	private Property<Integer> height = new Property<Integer>(IMAGERESOURCE_ATTR_HEIGHT);
	private Property<String> platform = new Property<String>(IMAGERESOURCE_ATTR_PLATFORM);
	private Property<String> density = new Property<String>(IMAGERESOURCE_ATTR_DENSITY);
	
	ImageResourceBase(Node node){
		this.itemNode = (Element)node;
		src.setValue(getNodeAttribute(node,  null,IMAGERESOURCE_ATTR_SRC));
		String s = getNodeAttribute(node, null, IMAGERESOURCE_ATTR_WIDTH);
		if ( s != null ){
			width.setValue(new Integer(s));
		}
		s = getNodeAttribute(node, null, IMAGERESOURCE_ATTR_HEIGHT);
		if ( s!= null ){
			height.setValue(new Integer(s));
		}
		platform.setValue(getNodeAttribute(node, NS_PHONEGAP_1_0, IMAGERESOURCE_ATTR_PLATFORM));
		density.setValue(getNodeAttribute(node, NS_PHONEGAP_1_0, IMAGERESOURCE_ATTR_DENSITY));
	}

	public String getSrc() {
		return src.getValue();
	}

	public int getWidth() {
		if(width.getValue() == null )
			return -1;
		return width.getValue().intValue();
	}

	public int getHeight() {
		if(height.getValue() == null )
			return -1;
		return height.getValue().intValue();
	}

	public String getPlatform() {
		return platform.getValue();
	}

	public String getDensity() {
		return density.getValue();
	}
	
	public void setSrc(String src) {
		this.src.setValue( src );
		setAttributeValue(itemNode, null, IMAGERESOURCE_ATTR_SRC, src);
	}

	public void setWidth(int width) {
		this.width.setValue(Integer.valueOf(width));
		setAttributeValue(itemNode, null, IMAGERESOURCE_ATTR_WIDTH, Integer.toString(width));
	}

	public void setHeight(int height) {
		this.height.setValue(Integer.valueOf(height));
		setAttributeValue(itemNode, null, IMAGERESOURCE_ATTR_HEIGHT, Integer.toString(height));
	}

	public void setPlatform(String platform) {
		this.platform.setValue(platform);
		setAttributeValue(itemNode, NS_PHONEGAP_1_0, WidgetModelConstants.IMAGERESOURCE_ATTR_PLATFORM, platform);
	}

	public void setDensity(String density) {
		this.density.setValue(density);
		setAttributeValue(itemNode, NS_PHONEGAP_1_0, WidgetModelConstants.IMAGERESOURCE_ATTR_DENSITY, density);
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj == null )
			return false;
		if (obj == this) 
			return true;
		ImageResourceBase that = (ImageResourceBase) obj;
		return equalField(that.getSrc(), this.getSrc());
	}
	
	@Override
	public int hashCode() {
		if(getSrc() != null )
			return getSrc().hashCode();
		return super.hashCode();
	}

}
