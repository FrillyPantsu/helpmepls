package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.table.TableLayout;


public class GlassCockpit extends Container
{
	public GlassCockpit(GameWorld gw)
	{

		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.BLACK);

		this.setLayout(new TableLayout(2, 6));

		Label[] headers = new Label[6];
		headers[0] = new Label("Game Time");
		headers[1] = new Label("Fuel");
		headers[2] = new Label("Damage");
		headers[3] = new Label("Lives");
		headers[4] = new Label("Last");
		headers[5] = new Label("Heading");

		for (int i = 0; i < headers.length; i++) {
			headers[i].setAutoSizeMode(true);
			headers[i].setMaxAutoSize((float)1.1);
			headers[i].setMinAutoSize((float)1.1);
		}

		this.add(headers[0]);
		this.add(headers[1]);
		this.add(headers[2]);
		this.add(headers[3]);
		this.add(headers[4]);
		this.add(headers[5]);
		this.add(new ComponentGameClock(gw));
		this.add(new ComponentFuel(gw));
		this.add(new ComponentDamage(gw));
		this.add(new ComponentLives(gw));
		this.add(new ComponentLast(gw));
		this.add(new ComponentHeading(gw));

	}

}
