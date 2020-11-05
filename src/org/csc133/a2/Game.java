package org.csc133.a2;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent; 
import java.lang.String; 


public class Game extends Form {
	
	private GameWorld gw;
	
	// TO DO: Pretty much everything
	public Game()
	{
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	private void play()
	{
		GlassCockpit hud = new GlassCockpit();
		
		Form grid = new Form("Grid Layout 2x2", new GridLayout(2, 2));
		grid.add(new Label("1"));
		grid.add(new Label("2"));	
		grid.add(new Label("3"));	
		grid.add(new Label("4"));	
		this.addComponent(grid);
		this.show();
		
		// Outdated code from AP1
		/*Label myLabel =  new Label("Enter a Command: ");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener()
		{
				public void actionPerformed(ActionEvent evt)
				{
					String sCommand = myTextField.getText().toString();
					myTextField.clear();
					switch (sCommand.charAt(0))
					{
					case 'a':
						gw.inputAcc();
						break;
					case 'b':
						gw.inputBrake();
						break;
					case 'l':
						gw.inputStickL();
						break;
					case 'r':
						gw.inputStickR();
						break;
					case 'c':
						gw.inputColl();
						break;
					case 't':
						gw.inputTick();
						break;
					case '1':
						gw.inputSky(1);
						break;
					case '2':
						gw.inputSky(2);
						break;
					case '3':
						gw.inputSky(3);
						break;
					case '4':
						gw.inputSky(4);
						break;
					case '5':
						gw.inputSky(5);
						break;
					case '6':
						gw.inputSky(6);
						break;
					case '7':
						gw.inputSky(7);
						break;
					case '8':
						gw.inputSky(8);
						break;
					case '9':
						gw.inputSky(9);
						break;
					case 'e':
						gw.inputRef();
						break;
					case 'g':
						gw.inputBirdCol();
						break;
					case 'd':
						gw.inputDisplay();
						break;
					case 'm':
						gw.inputMap();
						break;
					case 'x':
						System.out.println("Are you sure? Input y/n for confirmation.");
						if ('y' == sCommand.charAt(0))
							System.exit(0);
						else	
						break;
					default:
						System.exit(0);
						break;
					}
				}
		});*/
		
	}
	
}
