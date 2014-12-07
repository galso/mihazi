package mihazi;

import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.Rotation;

public class LineChart extends JFrame{
	
	public LineChart(String applicationTitle, String chartTitle, Map<Integer, Integer> map) {
        super(applicationTitle);
        // This will create the dataset 
        XYDataset dataset = createDataset(map);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);

    }
	
	private  XYDataset createDataset(Map<Integer, Integer> map) {
		final XYSeries result = new XYSeries("Reward/episodes");
		for(Integer i : map.keySet()){
			result.add(i, map.get(i));
		}
        
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(result);
        
        return dataset;
        
    }
	
	private JFreeChart createChart(XYDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createXYLineChart(
        	title,         			 // chart title
        	"Episode",
        	"Reward",
            dataset,                // data
            PlotOrientation.VERTICAL,
            true,                   // include legend
            true,					// tooltips
            false);

        final XYPlot plot = chart.getXYPlot();
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        plot.setRenderer(renderer);
        
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        return chart;
        
    }

}
