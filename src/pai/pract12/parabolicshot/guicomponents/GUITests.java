/**
 * File containing the MovingShapesTest entity definition. 
 */

package pai.pract12.parabolicshot.guicomponents;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Class which tests the behavior of the Parabolic Shot program GUI. It was
 * created for the twelfth practice of PAI (Programación de Aplicaciones
 * Interactivas) course of ULL (Universidad de la Laguna).
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 28 abr. 2018
 */
public class GUITests {

	/**
	 * Supports functional testing of Frames. AssertJ Swing fixtures simulate a
	 * user interacting with the GUI in order to verify that such GUI behave as we
	 * expect.
	 */
	private FrameFixture	window;
	/** GUI of the program. */
	private MainWindow		gui;
	/** GUI width. */
	private final int			GUI_WIDTH		= 900;
	/** GUI height. */
	private final int			GUI_HEIGHT	= 700;

	/**
	 * Forces a test to fail if access to GUI components is not performed on the
	 * EDT.
	 */
	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	/**
	 * Prepares everything each time a test will be executed.
	 */
	@Before
	public void setUp() {
		gui = GuiActionRunner.execute(new GuiQuery<MainWindow>() {
			@Override
			protected MainWindow executeInEDT() throws Throwable {
				return new MainWindow(GUI_WIDTH, GUI_HEIGHT, false);
			}
		});
		window = new FrameFixture(gui);
		window.show();
	}

	/**
	 * Tests the GUI panels
	 */
	@Test
	public void testGUIPanels() {
		final int RGB_GRAY = 238;
		Color defaultColor = new Color(RGB_GRAY, RGB_GRAY, RGB_GRAY);

		assertThat(window.requireEnabled());
		assertThat(window.requireVisible());
		assertThat(window.requireTitle("Parabolic shot!"));

		assertThat(window.panel("Control panel").requireVisible());
		assertThat(window.panel("Control panel").background()
				.requireEqualTo(defaultColor));
		assertThat(window.panel("Control panel").requireEnabled());
		assertThat(window.panel("Control panel").button("Shoot").requireEnabled());
		assertThat(window.panel("Control panel").button("Stop").requireDisabled());
		assertThat(window.panel("Control panel").button("Clear").requireEnabled());
		assertThat(window.panel("Control panel").button("Shoot").requireVisible());
		assertThat(window.panel("Control panel").button("Stop").requireVisible());
		assertThat(window.panel("Control panel").button("Clear").requireVisible());

		assertThat(window.panel("Information panel").requireVisible());
		assertThat(window.panel("Information panel").background()
				.requireEqualTo(defaultColor));
		assertThat(window.panel("Information panel").requireEnabled());

		assertThat(window.panel("Parabolic shot panel").requireVisible());
		assertThat(window.panel("Parabolic shot panel").background()
				.requireEqualTo(defaultColor));
		assertThat(window.panel("Parabolic shot panel").requireEnabled());
	}

	/**
	 * Tests the GUI information panel.
	 */
	@Test
	public void testInformationPanel() {
		assertThat(
				window.panel("Information panel").label("Time").requireEnabled());
		assertThat(window.panel("Information panel").label("Time").text()
				.equals("   Time = 0.0 s"));
		assertThat(
				window.panel("Information panel").label("X position").requireEnabled());
		assertThat(window.panel("Information panel").label("X position").text()
				.equals("   x = 0 m"));
		assertThat(
				window.panel("Information panel").label("Y position").requireEnabled());
		assertThat(window.panel("Information panel").label("Y position").text()
				.equals("   y = 0 m"));
		assertThat(
				window.panel("Information panel").label("X velocity").requireEnabled());
		assertThat(window.panel("Information panel").label("X velocity").text()
				.equals("   vx = 0 m/s"));
		assertThat(
				window.panel("Information panel").label("Y velocity").requireEnabled());
		assertThat(window.panel("Information panel").label("Y velocity").text()
				.equals("   vy = 0 m/s"));
		assertThat(
				window.panel("Information panel").label("Velocity").requireEnabled());
		assertThat(window.panel("Information panel").label("Velocity").text()
				.equals("   v = 0 m/s"));
		assertThat(
				window.panel("Information panel").label("Max height").requireEnabled());
		assertThat(window.panel("Information panel").label("Max height").text()
				.equals("   Max. height = 0 m"));
		window.panel("Control panel").button("Shoot").click();
		assertThat(!window.panel("Information panel").label("Time").text()
				.equals("   Time = 0.0 s"));
		assertThat(!window.panel("Information panel").label("X position").text()
				.equals("   x = 0 m"));
		assertThat(!window.panel("Information panel").label("Y position").text()
				.equals("   y = 0 m"));
		assertThat(!window.panel("Information panel").label("X velocity").text()
				.equals("   vx = 0 m/s"));
		assertThat(!window.panel("Information panel").label("Y velocity").text()
				.equals("   vy = 0 m/s"));
		assertThat(!window.panel("Information panel").label("Velocity").text()
				.equals("   v = 0 m/s"));
		assertThat(!window.panel("Information panel").label("Max height").text()
				.equals("   Max. height = 0 m"));
	}

	/**
	 * Tests the GUI control panel buttons.
	 */
	@Test
	public void testControlPanelButtons() {
		assertThat(window.panel("Control panel").textBox("Angle text field")
				.requireEnabled());
		assertThat(window.panel("Control panel").textBox("Velocity text field")
				.requireEnabled());
		assertThat(window.panel("Control panel").textBox("Height text field")
				.requireEnabled());
		assertThat(
				window.panel("Control panel").slider("Angle slider").requireEnabled());
		assertThat(window.panel("Control panel").slider("Velocity slider")
				.requireEnabled());
		assertThat(
				window.panel("Control panel").slider("Height slider").requireEnabled());
		assertThat(window.panel("Control panel")
				.checkBox("Show trajectories checkbox").requireEnabled());
		assertThat(
				window.panel("Control panel").button("Set height").requireEnabled());
		assertThat(
				window.panel("Control panel").button("Set angle").requireEnabled());
		assertThat(
				window.panel("Control panel").button("Set velocity").requireEnabled());

		final int DEFAULT_ANGLE = 45;
		final int NEW_HEIGHT = 50;
		final int NEW_VELOCITY = 100;
		window.panel("Control panel").textBox("Angle text field").setText("-12");
		window.panel("Control panel").button("Set angle").click();
		assertThat(
				gui.getControlPanel().getAngleSlider().getValue() == DEFAULT_ANGLE);

		window.panel("Control panel").textBox("Height text field").setText("50");
		window.panel("Control panel").button("Set height").click();
		assertThat(
				gui.getControlPanel().getHeightSlider().getValue() == NEW_HEIGHT);

		window.panel("Control panel").slider("Velocity slider")
				.slideTo(NEW_VELOCITY);
		assertThat(gui.getControlPanel().getVelocityTextField().getText()
				.equals("" + NEW_VELOCITY));

		window.panel("Control panel").button("Shoot").click();
		assertThat(window.panel("Control panel").button("Stop").requireEnabled());
		assertThat(window.panel("Control panel").button("Clear").requireEnabled());
		assertThat(window.panel("Control panel").button("Shoot").requireDisabled());
		assertThat(window.panel("Control panel").textBox("Angle text field")
				.requireDisabled());
		assertThat(window.panel("Control panel").textBox("Velocity text field")
				.requireDisabled());
		assertThat(window.panel("Control panel").textBox("Height text field")
				.requireDisabled());
		assertThat(
				window.panel("Control panel").slider("Angle slider").requireDisabled());
		assertThat(window.panel("Control panel").slider("Velocity slider")
				.requireDisabled());
		assertThat(window.panel("Control panel").slider("Height slider")
				.requireDisabled());
		assertThat(window.panel("Control panel")
				.checkBox("Show trajectories checkbox").requireEnabled());
		assertThat(
				window.panel("Control panel").button("Set height").requireDisabled());
		assertThat(
				window.panel("Control panel").button("Set angle").requireDisabled());
		assertThat(
				window.panel("Control panel").button("Set velocity").requireDisabled());
		window.panel("Control panel").button("Stop").click();
		assertThat(window.panel("Control panel").button("Stop").requireEnabled());
		assertThat(window.panel("Control panel").button("Clear").requireEnabled());
		assertThat(
				window.panel("Control panel").button("Stop").text().equals("Continue"));
		assertThat(window.panel("Control panel").button("Shoot").requireEnabled());
		assertThat(window.panel("Control panel").textBox("Angle text field")
				.requireEnabled());
		assertThat(window.panel("Control panel").textBox("Velocity text field")
				.requireEnabled());
		assertThat(window.panel("Control panel").textBox("Height text field")
				.requireEnabled());
		assertThat(
				window.panel("Control panel").slider("Angle slider").requireEnabled());
		assertThat(window.panel("Control panel").slider("Velocity slider")
				.requireEnabled());
		assertThat(
				window.panel("Control panel").slider("Height slider").requireEnabled());
		assertThat(window.panel("Control panel")
				.checkBox("Show trajectories checkbox").requireEnabled());
		assertThat(
				window.panel("Control panel").button("Set height").requireEnabled());
		assertThat(
				window.panel("Control panel").button("Set angle").requireEnabled());
		assertThat(
				window.panel("Control panel").button("Set velocity").requireEnabled());
		window.panel("Control panel").button("Stop").click();
		assertThat(window.panel("Control panel").button("Stop").requireEnabled());
		assertThat(window.panel("Control panel").button("Clear").requireEnabled());
		assertThat(
				window.panel("Control panel").button("Stop").text().equals("Stop"));
		assertThat(window.panel("Control panel").button("Shoot").requireDisabled());
		window.panel("Control panel").button("Clear").click();
		assertThat(window.panel("Control panel").button("Clear").requireEnabled());
		assertThat(gui.getParabolicShotPanel().getProjectiles().size() == 0);
		assertThat(gui.getParabolicShotPanel().getProjectileColors().size() == 0);
		final int EXPECTED_PROJECTILES = 3;
		for (int i = 0; i < EXPECTED_PROJECTILES; ++i) {
			window.panel("Control panel").button("Shoot").click();
		}
		assertThat(gui.getParabolicShotPanel().getProjectiles()
				.size() == EXPECTED_PROJECTILES);
		assertThat(gui.getParabolicShotPanel().getProjectileColors()
				.size() == EXPECTED_PROJECTILES);
	}

	/**
	 * Closes the needed resources each time a test is executed.
	 */
	@After
	public void tearDown() {
		window.cleanUp();
	}

}
