package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class CreateChartHelper extends ApplicationFrame {
	private static Number[] mPopulationMale;
	private static Number[] mPopulationFemale;
	private static String mFeature;
	private static String mCountry;

	public CreateChartHelper(String title) {
		super(title);
		final XYDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		convertToPdf(chart, 500, 500, mCountry + ".pdf");
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}

	public static void create(String country, String feature, Number[] populationMale,
			Number[] populationFemale) {
		mPopulationMale = populationMale;
		mPopulationFemale = populationFemale;
		mFeature = feature;
		mCountry = country;
		final CreateChartHelper demo = new CreateChartHelper(mFeature);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static XYDataset createDataset() {
		int start = 2015;
		int end = 2100;
		if (mFeature.equals("ESTIMATES")) {
			start = 1950;
			end = 2015;
		}
		final XYSeries series1 = new XYSeries("Male");
		final XYSeries series2 = new XYSeries("Female");
		for (int i = start; i < end + 1; i++) {
			series1.add(i, mPopulationMale[i - start]);
			series2.add(i, mPopulationFemale[i - start]);
		}
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		return dataset;

	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the data for the chart.
	 * 
	 * @return a chart.
	 */
	private static JFreeChart createChart(final XYDataset dataset) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createXYLineChart("Population", // chart
																				// title
				"year", // x axis label
				"value", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

		// final StandardLegend legend = (StandardLegend) chart.getLegend();
		// legend.setDisplaySeriesShapes(true);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesShapesVisible(1, false);
		plot.setRenderer(renderer);

		// change the auto tick unit selection to integer units only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;

	}
	 public void convertToPdf(JFreeChart chart,
		       int width, int height, String filename) {
		       Document document = new Document();
		       try {
		          PdfWriter writer;
		          writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
		          document.open();
		          PdfContentByte cb = writer.getDirectContent();
		          PdfTemplate tp = cb.createTemplate(width, height);
		          Graphics2D g2d = tp.createGraphics(width, height, new DefaultFontMapper());
		          Rectangle2D r2d = new Rectangle2D.Double(0, 0, width, height);
		          chart.draw(g2d, r2d);
		          g2d.dispose();
		          cb.addTemplate(tp, 0, 0);
		       }
		       catch(Exception e) {
		          e.printStackTrace();
		       }
		       document.close();
		    }
}
