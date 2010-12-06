package arcade.core;

public interface ArcadeComponent {
	/**
	 * Get the name of this component
	 * TODO:replicates JComponent.getName();
	 * @return name of the component
	 */
	public String getComponentName();
	
	/**
	 * Get description of the current component. It's displayed as a tool tip on the tab
	 * TODO:replicates JComponent.getToolTipText();
	 * @return description of the current component
	 */
	public String getComponentDescription();
}
