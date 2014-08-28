package com.mrprez.gencross.ui.persoframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.comment.EditCommentFrame;
import com.mrprez.gencross.ui.comment.ViewCommentFrame;
import com.mrprez.gencross.ui.history.HistoryFrame;
import com.mrprez.gencross.ui.persoframe.menu.CancelListener;
import com.mrprez.gencross.ui.persoframe.menu.PersoMenuBar;

public class PersoFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private String title = "Gen-Cross";
	private JLabel phaseLabel = new JLabel();
	private JPanel errorPanel = new JPanel();
	private JPanel pointPanel = new JPanel();
	private JPanel leftPointPanel = new JPanel();
	private JPanel rightPointPanel = new JPanel();
	private JScrollPane treeView = new JScrollPane();
	private PropertyTree propertyTree;
	private JPanel buttonPanel = new JPanel();
	private JButton nextPhaseButton = new JButton("Next");
	private PersoMenuBar menuBar;
	private HistoryFrame historyFrame;
	private EditCommentFrame editCommentFrame = new EditCommentFrame();
	private ViewCommentFrame viewCommentFrame = new ViewCommentFrame();
	private static final String onCtrlZ = "onCtrlZ";
	private ImageIcon modifyIcon;
	
	
	public PersoFrame() throws HeadlessException, CloneNotSupportedException {
		super();
		setTitleSuffix(null);
		addWindowListener(new CloseListener());
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_DOWN_MASK), onCtrlZ);
		getRootPane().getActionMap().put(onCtrlZ,new CancelListener());
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle desktop = ge.getMaximumWindowBounds();
		setPreferredSize(new Dimension(desktop.width/2, desktop.height));
		
		this.setJMenuBar(menuBar);
		historyFrame = new HistoryFrame();
		initComponents();
		
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addComponent(phaseLabel)
				.addComponent(pointPanel)
				.addComponent(errorPanel)
				.addComponent(treeView)
				.addComponent(buttonPanel)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(phaseLabel,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(pointPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(errorPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(treeView,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(buttonPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addContainerGap()
		);
		pack();
	}
	
	public void initComponents(){
		//Menu bar
		menuBar = new PersoMenuBar(this);
		this.setJMenuBar(menuBar);
		
		//Points Panel
		pointPanel.setLayout(new GridLayout(1,2));
		leftPointPanel.setLayout(new GridBagLayout());
		rightPointPanel.setLayout(new GridBagLayout());
		pointPanel.add(leftPointPanel);
		pointPanel.add(rightPointPanel);
		leftPointPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		rightPointPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		// Next phase button
		nextPhaseButton.addActionListener(new NextPhaseListener());
		nextPhaseButton.setEnabled(false);
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(nextPhaseButton);
	}
	
	public HistoryFrame getHistoryFrame() {
		return historyFrame;
	}
	
	public EditCommentFrame getEditCommentFrame(){
		return editCommentFrame;
	}
	
	public ViewCommentFrame getViewCommentFrame(){
		return viewCommentFrame;
	}
	
	public void reinit(){
		historyFrame.reinit();
		reinitMenuBar();
		reinitTitle();
		reinitPhasePanel();
		reinitPointsPanel();
		reinitErrorPanel();
		reinitPropertyTree();
		reinitButtonPanel();
		propertyTree.setPersonnage(GenCrossUI.getInstance().getPersonnage());
		propertyTree.update();
		SwingUtilities.updateComponentTreeUI(propertyTree);
		editCommentFrame.setVisible(false);
		viewCommentFrame.setVisible(false);
	}
	
	public void setModifyIcon(ImageIcon modifyIcon) {
		this.modifyIcon = modifyIcon;
	}

	public void saveModification() throws CloneNotSupportedException{
		GenCrossUI.getInstance().saveModification();
		reinitMenuBar();
	}
	
	public void reinitMenuBar(){
		menuBar.reinit();
	}
	
	public void reinitTitle(){
		if(GenCrossUI.getInstance().getLastSave() != null){
			this.setTitle(title+" - "+GenCrossUI.getInstance().getLastSave().getName());
		}else{
			this.setTitle(title);
		}
	}
	
	public void reinitPhasePanel(){
		phaseLabel.setText("Etape: "+GenCrossUI.getCurrentPersonnage().getPhase());
		SwingUtilities.updateComponentTreeUI(phaseLabel);
	}
	
	public void reinitPointsPanel(){
		leftPointPanel.removeAll();
		rightPointPanel.removeAll();
		
		int gridy = 0;
		int column = 0;
		for(String name : GenCrossUI.getCurrentPersonnage().getPointPools().keySet()){
			JPanel currentPanel = column==0?leftPointPanel:rightPointPanel;
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridy = gridy;
			constraints.anchor = GridBagConstraints.LINE_START;
			constraints.gridx = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1.0;
			constraints.insets = new Insets(2, 5, 2, 15);
			currentPanel.add(new JLabel(name),constraints);
			constraints.weightx = 0.0;
			constraints.gridx = 1;
			currentPanel.add(new JLabel(GenCrossUI.getCurrentPersonnage().getPointPools().get(name).toString()), constraints);
			if(GenCrossUI.getInstance().isMj()){
				constraints.gridx = 2;
				JLabel editIcon = new JLabel(modifyIcon);
				editIcon.addMouseListener(new EditPointPoolListener(currentPanel, GenCrossUI.getCurrentPersonnage().getPointPools().get(name)));
				currentPanel.add(editIcon, constraints);
			}
			if(column==1){
				gridy++;
			}
			column = column==0?1:0;
		}
		SwingUtilities.updateComponentTreeUI(pointPanel);
	}
	
	public void reinitErrorPanel(){
		errorPanel.setLayout(new GridLayout(GenCrossUI.getCurrentPersonnage().getErrors().size(),1));
		errorPanel.removeAll();
		for(String error : GenCrossUI.getCurrentPersonnage().getErrors()){
			JLabel labelError = new JLabel(error);
			labelError.setToolTipText(error);
			labelError.setForeground(Color.RED);
			errorPanel.add(labelError);
		}
	}
	
	public void reinitPropertyTree(){
		propertyTree = new PropertyTree(GenCrossUI.getInstance().getPersonnage());
		treeView.setViewportView(propertyTree);
	}
	
	public void reinitButtonPanel(){
		boolean phaseFinished = GenCrossUI.getInstance().getPersonnage().phaseFinished();
		String nextPhase = GenCrossUI.getInstance().getPersonnage().nextPhase();
		nextPhaseButton.setEnabled(phaseFinished && nextPhase!=null);
	}

	

	public void setTitleSuffix(String suffix){
		if(suffix==null){
			setTitle(title);
		}else{
			setTitle(title+" - "+suffix);
		}
	}

	
	
	
	

}
