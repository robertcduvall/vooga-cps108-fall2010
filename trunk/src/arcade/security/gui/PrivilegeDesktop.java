package arcade.security.gui;

import java.awt.Color;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import arcade.security.etc.SecurityFrameData;

/**
 * 
 * @author Meng Li
 *
 */
@SuppressWarnings("serial")
public class PrivilegeDesktop extends JDesktopPane{
	
	private Collection<SecurityFrameData> appData;
	private JFrame parentFrame;
	private Collection<UserConfigurationFrame> frames=new LinkedList<UserConfigurationFrame>();
	
 			public PrivilegeDesktop(Collection<SecurityFrameData> appData,JFrame parentFrame){
 				this.appData = appData;
				this.parentFrame=parentFrame;
				createPane();			
				this.setBackground(Color.WHITE);
			}
			

			
			public void createPane(){
				loadApps(appData);
			}
			
			private void loadApps(Collection<SecurityFrameData> appData) {
				for(SecurityFrameData app: appData){
					UserConfigurationFrame temp=createInternalFrame(app);
					temp.setParentFrame(parentFrame);
					this.add(temp);					
					frames.add(temp);
				}				
				cascadeFrame();			
			}
		
			public void cascadeFrame(){
				int pos=100;
				for(UserConfigurationFrame internalFrame:frames){  //or here I can use Desktop.getAllFrames() to get all the internalFrames
					internalFrame.setBounds(pos, pos, 600, 400);
					pos=pos-40;
				}				
			}
			
			private UserConfigurationFrame createInternalFrame(SecurityFrameData app)
		     {			
		    try{
		    	Object internalFrame=Class.forName(app.getClazz()).newInstance();  		    	 	
		    	UserConfigurationFrame appframe=(UserConfigurationFrame)internalFrame;   // LifecycleInternalFrame appframe=(LifecycleInternalFrame)internalFrame;		             
		             return appframe;    
		    }
		    catch(ClassNotFoundException e1){
		    	e1.printStackTrace();
		    }
		    catch(InstantiationException e2){
		    	e2.printStackTrace();
		    }
		    catch(IllegalAccessException e3){
		    	e3.printStackTrace();
		    }
			return null;					    
			}
	
	
}
