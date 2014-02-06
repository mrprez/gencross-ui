package com.mrprez.gencross.ui.persoframe;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.Property;

public class PropertyTree extends JTree {
	private static final long serialVersionUID = 1L;
	private Personnage personnage;
	private DefaultMutableTreeNode top;


	public PropertyTree(Personnage personnage){
		super(new DefaultMutableTreeNode("Propriétés"));
		ToolTipManager.sharedInstance().registerComponent(this);
		this.personnage = personnage;
		top = (DefaultMutableTreeNode) this.getModel().getRoot();
		setBorder(BorderFactory.createLoweredBevelBorder());
		Iterator<Property> it = this.personnage.iterator();
		while(it.hasNext()){
			top.add(buildNode(it.next()));
		}
		setRootVisible(false);
		expandPath(new TreePath(top.getPath()));
		addMouseListener(new EditPropertyListener(this));
		addMouseListener(new PopupListener(new TreePopupMenu(this)));
	}
	
	public DefaultMutableTreeNode buildNode(Property property){
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(property);
		if(property.getSubProperties()!=null){
			Iterator<Property> it = property.iterator();
			while(it.hasNext()){
				node.add(buildNode(it.next()));
			}
			if(!property.getSubProperties().isFixe()){
				DefaultMutableTreeNode addNode = new DefaultMutableTreeNode("<html><i>Ajouter</i></html>");
				node.add(addNode);
			}
		}
		return node;
	}
	
	public void update(){
		Enumeration<?> children = top.children();
		while(children.hasMoreElements()){
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
			updateNode(child, personnage.getProperty(((Property)child.getUserObject()).getAbsoluteName()));
		}
	}
	
	private void updateNode(DefaultMutableTreeNode node, Property property){
		node.setUserObject(property);
		if(property.getSubProperties()!=null){
			Set<String> subPropertiesName = new HashSet<String>(property.getSubProperties().getProperties().keySet());
			MutableTreeNode addTreeNode = null;
			for(int i=0; i<node.getChildCount(); i++){
				DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
				if(child.getUserObject() instanceof Property){
					Property oldProperty = (Property) child.getUserObject();
					if(subPropertiesName.contains(oldProperty.getFullName())){
						updateNode(child, property.getSubProperty(oldProperty.getFullName()));
						subPropertiesName.remove(oldProperty.getFullName());
					}else{
						node.remove(child);
						i--;
					}
				}else{
					addTreeNode = child;
				}
			}
			for(String newPropertyName : subPropertiesName){
				node.add(buildNode(property.getSubProperty(newPropertyName)));
			}
			if(addTreeNode!=null){
				node.remove(addTreeNode);
			}
			if(!property.getSubProperties().isFixe()){
				node.add(new DefaultMutableTreeNode("<html><i>Ajouter</i></html>"));
			}
		} else {
			node.removeAllChildren();
		}
		
	}
	
	public static Set<DefaultMutableTreeNode> getExceedingNodes(DefaultMutableTreeNode node){
		if(!(node.getUserObject() instanceof Property)){
			return new HashSet<DefaultMutableTreeNode>();
		}
		Property property = (Property) node.getUserObject();
		Set<DefaultMutableTreeNode> result = new HashSet<DefaultMutableTreeNode>();
		Enumeration<?> children = node.children();
		while(children.hasMoreElements()){
			result.add((DefaultMutableTreeNode) children.nextElement());
		}
		if(property.getSubProperties()==null){
			return result;
		}
		Iterator<DefaultMutableTreeNode> it = result.iterator();
		while(it.hasNext()){
			DefaultMutableTreeNode child = it.next();
			if(property.getSubProperties().getProperties().containsValue(child.getUserObject())){
				it.remove();
			}
		}
		return result;
	}
	
	public static Set<Property> getExceedingProperties(DefaultMutableTreeNode node){
		if(!(node.getUserObject() instanceof Property)){
			return new HashSet<Property>();
		}
		Property property = (Property) node.getUserObject();
		if(property.getSubProperties()==null){
			return new HashSet<Property>();
		}
		if(property.getSubProperties().getProperties().isEmpty()){
			return new HashSet<Property>();
		}
		Enumeration<?> children = node.children();
		Set<Property> properties = new HashSet<Property>(property.getSubProperties().getProperties().values());
		while(children.hasMoreElements()){
			properties.remove(((DefaultMutableTreeNode)children.nextElement()).getUserObject());
		}
		return properties;
	}
	
	@Override
	public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		if(value instanceof DefaultMutableTreeNode){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
			if(node.getUserObject() instanceof Property){
				Property property = (Property)node.getUserObject();
				if(property.getValue()!=null){
					if(property.isEditable()){
						return "<html>"+property.getRenderer().displayName(property)+" : "+property.getRenderer().displayValue(property)+"</html>";
					}
					return "<html>"+property.getRenderer().displayName(property)+" : <font color=#999999>"+property.getRenderer().displayValue(property)+"</font></html>";
				}else{
					return property.getRenderer().displayName(property);
				}
			}
		}
		return super.convertValueToText(value, selected, expanded, leaf, row, hasFocus);
	}
	
	
	public void expandAll(DefaultMutableTreeNode node){
		this.expandPath(new TreePath(node.getPath()));
		for(int i=0; i<node.getChildCount();i++){
			expandAll((DefaultMutableTreeNode)node.getChildAt(i));
		}
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}
	
	public PersoFrame getPersoFrame(){
		Container container = this.getParent();
		while(!(container instanceof PersoFrame)){
			container = container.getParent();
		}
		return (PersoFrame) container;
	}

	@Override
	public String getToolTipText(MouseEvent event) {
		TreePath treePath = this.getClosestPathForLocation(event.getX(), event.getY());
		if(treePath==null){
			return super.getToolTipText();
		}
		Object object = ((DefaultMutableTreeNode)treePath.getLastPathComponent()).getUserObject();
		if(object==null){
			return super.getToolTipText();
		}
		if(object instanceof Property){
			Property property = (Property)object;
			if(property.getComment()!=null){
				return property.getComment().length()<200?property.getComment():(property.getComment().substring(0, 200)+"...");
			}
		}
		return super.getToolTipText();
	}
	
	

}
