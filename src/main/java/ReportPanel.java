import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel implements BookstoreView {
    private final BookstoreModel model;

    public ReportPanel(BookstoreModel model) {
        super();
        this.model = model;
        this.model.addView(this);
        this.setPreferredSize(new Dimension(500,800));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        this.add(buttonPanel, BorderLayout.PAGE_START);
        buttonPanel.add(new JButton("Sales vs. Expenditures"));
        buttonPanel.add(new JButton("Sales per Genre"));
        buttonPanel.add(new JButton("Sales per Author"));

        this.add(new JLabel("Reports go here", JLabel.CENTER), BorderLayout.CENTER);
    }

    @Override
    public void update(BookstoreEvent e) {

    }
}
